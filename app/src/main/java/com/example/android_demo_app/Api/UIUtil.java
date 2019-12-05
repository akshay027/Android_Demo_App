package com.example.android_demo_app.Api;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class UIUtil {
    private static ProgressDialog mProgressDialog;
    private static Object mObject = new Object();


    public static void startProgressDialog(Context context, String message) {
        synchronized (mObject) {
            try {
                if (mProgressDialog == null) {
                    mProgressDialog = ProgressDialog.show(context, "", message);
                    mProgressDialog.setIndeterminate(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopProgressDialog(Context context) {
        synchronized (mObject) {
            try {
                if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isInternetAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
       // Log.e("play store versionCode", "==========" + isConnected);
        return isConnected;

    }

}
