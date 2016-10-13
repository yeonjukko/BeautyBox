package com.beautybox.bluetooth;

/**
 * Created by yeonjukko on 2016. 10. 12..
 */

public interface BluetoothListener {

    void onConnected();

    void onDisconnected();

    void onReceived(String data);

    void onError(Exception e);

}
