package com.beautybox.manager;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * Created by yeonjukko on 2016. 10. 6..
 */

public class ConnectManager {
    public static final String CMD_PRODUCT_1_ON = "PROD1";
    public static final String CMD_PRODUCT_1_OFF = "PROD1";
    public static final String CMD_PRODUCT_2_ON = "PROD2";
    public static final String CMD_PRODUCT_2_OFF = "PROD2";
    public static final String CMD_PRODUCT_3_ON = "PROD3";
    public static final String CMD_PRODUCT_3_OFF = "PROD3";
    public static final String CMD_PRODUCT_4_ON = "PROD4";
    public static final String CMD_PRODUCT_4_OFF = "PROD4";
    public static final String CMD_PRODUCT_5_ON = "PROD5";
    public static final String CMD_PRODUCT_5_OFF = "PROD5";
    public static final String CMD_PRODUCT_6_ON = "PROD6";
    public static final String CMD_PRODUCT_6_OFF = "PROD6";
    public static final String CMD_PRODUCT_7_ON = "PROD7";
    public static final String CMD_PRODUCT_7_OFF = "PROD7";
    public static final String CMD_PRODUCT_8_ON = "PROD8";
    public static final String CMD_PRODUCT_8_OFF = "PROD8";
    public static final String CMD_PRODUCT_9_ON = "PROD9";
    public static final String CMD_PRODUCT_9_OFF = "PROD9";

    private static final String CMD_READ_STATUS = "RDATA";
    private static final String TAG = ConnectManager.class.getSimpleName();

    private Context context;
    private static ConnectManager instance;

    private BluetoothAdapter bluetoothAdapter;

    private ConnectManager(Context context){
        this.context = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    public static ConnectManager getInstance(Context context) {
        if (instance == null) {
            instance = new ConnectManager(context);
        }
        return instance;
    }


}
