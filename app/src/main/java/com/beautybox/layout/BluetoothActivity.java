package com.beautybox.layout;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.beautybox.BeautyBoxActivity;
import com.beautybox.R;
import com.beautybox.bluetooth.BluetoothListener;
import com.beautybox.bluetooth.MyBluetoothManager;

import java.util.Set;

public class BluetoothActivity extends BeautyBoxActivity implements BluetoothListener {
    private static final String TAG = BluetoothActivity.class.getName();
    private static final int INTENT_BT_SETTING = 0;
    private MyBluetoothManager instance;
    private BluetoothListAdapter bluetoothListAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_notification);
        instance = MyBluetoothManager.getInstance(getContext());
        instance.setOnBluetoothListener(this);
        instance.onCreate();

        if (!instance.isBluetoothEnable()) {
            dialogBuilder = new AlertDialog.Builder(getContext());
            dialog = dialogBuilder.create();
            dialogBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intentOpenBluetoothSettings = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivityForResult(intentOpenBluetoothSettings,INTENT_BT_SETTING);
                }
            });
            dialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(), "블루투스를 키고 서비스를 실행해주세요.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            dialogBuilder.setTitle("블루투스 설정");
            dialogBuilder.setMessage("\n블루투스를 실행하시겠습니까?\n");
            dialogBuilder.show();
            return;
        } else {
            setRecyclerView();
            findBluetooth();
        }

    }

    public void findBluetooth() {
        pairedDevices = instance.getPairedDevices();
        if (pairedDevices != null) {
            //deviceStr UI 작업
            bluetoothListAdapter.setData(pairedDevices);

        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("알림")
                    .setMessage("등록된 기기가 없습니다.\n새로운 기기를 등록하시겠습니까?")
                    .setNegativeButton("취소", null)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intentOpenBluetoothSettings = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                            startActivity(intentOpenBluetoothSettings);
                        }
                    }).show();

            Toast.makeText(getContext(), "Any devices are not searched.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setRecyclerView() {
        RecyclerView btRecyclerView = (RecyclerView) findViewById(R.id.rv_device_list);
        btRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bluetoothListAdapter = new BluetoothListAdapter(this, null);
        btRecyclerView.setAdapter(bluetoothListAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==INTENT_BT_SETTING){
                Log.d("test","activityresult");
                setRecyclerView();
                findBluetooth();
        }
    }

    private Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance.onDestroy();
    }

    @Override
    public void onConnected() {
        instance.sendConnected();
    }

    @Override
    public void onDisconnected() {
        instance.sendDisconnected();
    }

    @Override
    public void onReceived(String data) {
        instance.sendReceiveData(data);
    }

    @Override
    public void onError(Exception e) {
        instance.sendError(e);
    }
}
