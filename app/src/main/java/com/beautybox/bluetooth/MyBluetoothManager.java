package com.beautybox.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yeonjukko on 2016. 10. 12..
 */

public class MyBluetoothManager {
    private static final String FLAG_COSMETIC = "cosmetics";
    public static final String ACTION_RECEIVED_DATA = MyBluetoothManager.class.getName() + ".Received";
    public static final String ACTION_CONNECT_DEVICE = MyBluetoothManager.class.getName() + ".Connected";
    public static final String ACTION_DISCONNECT_DEVICE = MyBluetoothManager.class.getName() + ".Disconnected";
    public static final String ACTION_ERROR = MyBluetoothManager.class.getName() + ".Error";
    public static final String ACTION_NOT_REG_DEVICE = MyBluetoothManager.class.getName() + ".NotRegDevice";

    public static final String FLAG_DATA = "data";
    public static final String FLAG_ERROR = "error";

    private static final UUID COMMON_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private BluetoothListener bluetoothListener;
    private BluetoothReceiver bluetoothReceiver;

    private AsyncTask receiveTask;

    private static MyBluetoothManager instance;
    private String TAG = MyBluetoothManager.class.getName();
    private Context context;

    /* init method */
    private MyBluetoothManager(Context context) {
        this.context = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothReceiver = new BluetoothReceiver();
        if (bluetoothAdapter == null) {
            ToastMangaer("This device doesn't support Bluetooth service");
        }
    }

    public static MyBluetoothManager getInstance(Context context) {
        if (instance == null) {
            instance = new MyBluetoothManager(context);
        }
        return instance;
    }

    public void setOnBluetoothListener(BluetoothListener bluetoothListener) {
        this.bluetoothListener = bluetoothListener;
    }

    /* Lifecycle method */
    public void onCreate() {
        regReceiver();
    }

    public void onDestroy() {
        unRegReceiver();
    }

    /* BluetoothDevice & BluetoothAdapter method */
    public boolean isBluetoothEnable() {
        return bluetoothAdapter.isEnabled();
    }

    public void setConnectDevice(BluetoothDevice device) {
        this.bluetoothDevice = device;
        ToastMangaer("Bluetooth Device is connected!");
        Log.d(TAG, "기기 등록 완료 :" + bluetoothDevice.toString());
    }

    public BluetoothDevice getConnectDevice() {
        return bluetoothDevice;
    }

    public Set<BluetoothDevice> getPairedDevices() {
        return bluetoothAdapter.getBondedDevices();
    }

    /* Connection method */
    public boolean isConnected() {
        return bluetoothSocket != null && bluetoothSocket.isConnected();
    }

    public void connectBluetoothDevice() {
        try {

            if (!instance.isBluetoothEnable()) {
                Toast.makeText(context, "블루투스를 켜주세요.", Toast.LENGTH_SHORT).show();
                sendError(new MyBluetoothManager.BluetoothOffException());
                return;
            }
            if (!instance.isConnected()) {
                instance.connect();
                Log.d("test", "connect");

            } else {
                sendConnected();
            }
        } catch (MyBluetoothManager.NotSavedDeviceException e) {
            sendNotRegDevice();
        }
    }

    public void connect() {
        //Log.d("test","connect");
        if (bluetoothDevice == null) {
            throw new IllegalStateException("연결할 블루투스 기기를 설정해주세요!");
        }
        if (receiveTask != null) {
            receiveTask.cancel(true);
        }

        if (bluetoothSocket != null) {
            try {
                bluetoothSocket.close();
            } catch (IOException ignored) {
            }
            bluetoothSocket = null;
        }
        new ConnectTask().execute();
        Log.d(TAG, "블루투스 기기와 연결중...");


    }

    private void socketClose() {
        if (bluetoothSocket != null) {
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bluetoothSocket = null;

        if (bluetoothListener != null) {
            bluetoothListener.onDisconnected();
        }
    }

    /* Receiver method */
    private class BluetoothReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED) || intent.getAction().equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (bluetoothDevice.equals(getConnectDevice())) {
                    socketClose();
                }
            }
        }
    }

    private void regReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        context.registerReceiver(bluetoothReceiver, filter);
    }

    private void unRegReceiver() {
        context.unregisterReceiver(bluetoothReceiver);
    }

    /* Send Broadcast*/
    public void sendDisconnected() {
        Intent intent = new Intent(ACTION_DISCONNECT_DEVICE);
        context.sendBroadcast(intent);
    }

    public void sendError(Exception e) {
        Intent intent = new Intent(ACTION_ERROR);
        intent.putExtra(FLAG_ERROR, e);
        context.sendBroadcast(intent);
    }

    public void sendConnected() {
        context.sendBroadcast(new Intent(ACTION_CONNECT_DEVICE));
    }

    public void sendNotRegDevice() {
        context.sendBroadcast(new Intent(ACTION_NOT_REG_DEVICE));
    }

    public void sendReceiveData(String data) {
        Intent intent = new Intent(ACTION_RECEIVED_DATA);
        Log.d(TAG, "sendReceived" + data.toString());
        Bundle result = getParseCosmetic(data);
        if (result != null) {
            intent.putExtras(result);
            context.sendBroadcast(intent);
        } else {
            Log.d(TAG, "파싱 실패(Data:" + data + ")");
        }
    }

    /* AsyncTask */
    private class ConnectTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(COMMON_UUID);
                bluetoothSocket.connect();
                sendConnected();
                return true;
            } catch (IOException e) {
                if (bluetoothListener != null) {
                    bluetoothListener.onError(e);
                }
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isConnected) {
            super.onPostExecute(isConnected);
            if (isConnected) {
                if (bluetoothListener != null) {
                    bluetoothListener.onConnected();
                }
                Log.d(TAG, "블루투스 연결 완료..");
                receiveTask = new ReceivedDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else {
                Log.d(TAG, "블루투스 연결 실패..");
            }
        }
    }

    private class ReceivedDataTask extends AsyncTask<Void, String, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "블루투스 기기로부터 데이터 받기 시작..");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bluetoothSocket.getInputStream()));
                String tmp;

                while ((tmp = bufferedReader.readLine()) != null) {
                    if (bluetoothListener != null) {
                        bluetoothListener.onReceived(tmp);
                    }
                    Log.d(TAG, "received" + tmp);
                }
            } catch (IOException e) {
                if (bluetoothListener != null) {
                    bluetoothListener.onError(e);
                }
                socketClose();
                Log.d(TAG, "블루투스 기기로부터 데이터 받기 에러..");

            }
            return null;
        }
    }

    /* ETC method */
    private void ToastMangaer(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        return;
    }

    public static Bundle getParseCosmetic(String data) {
        try {
            //int start = data.indexOf("R");
            //String tmp = data.substring(start + 1, start + 12);
            String tmp = data;
            Bundle result = new Bundle();
            result.putString(FLAG_COSMETIC, data);
            return result;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* Bluetooth Exceptions*/
    public class NotSavedDeviceException extends IllegalStateException {
        public NotSavedDeviceException() {
            super();
        }

        public NotSavedDeviceException(String err) {
            super(err);
        }
    }

    public static class BluetoothOffException extends IllegalStateException {
        public BluetoothOffException() {
            super("블루투스를 켜주세요.");
        }

        public BluetoothOffException(String err) {
            super(err);
        }
    }


}
