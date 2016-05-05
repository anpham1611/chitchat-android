package com.apmv.chitchat.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by an.pham1611 on 3/23/16.
 */
public class NetUtils {

    public static boolean isConnectInternet = false;

    /**
     * @param context
     */
    public static void registerCheckStateInternetConnection(Context context) {
        String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
        IntentFilter filter = new IntentFilter(ACTION);
        context.registerReceiver(mConnectivityCheckReceiver, filter);
    }

    /**
     * @param context
     */
    public static void unregisterCheckStateInternetConnection(Context context) {
        context.unregisterReceiver(mConnectivityCheckReceiver);
    }

    /**
     * @param context
     */
    private static void checkConnectInternet(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conManager.getActiveNetworkInfo();
        if ((i == null) || (!i.isConnected()) || (!i.isAvailable())) {
            isConnectInternet = false;
        } else {
            isConnectInternet = true;
        }
    }

    /**
     * mConnectivityCheckReceiver
     */
    private static BroadcastReceiver mConnectivityCheckReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkConnectInternet(context);
        }
    };

}
