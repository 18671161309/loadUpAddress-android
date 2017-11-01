package com.china317.developlibrary.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {
    public static void showMessageShort(Context context, int resId) {
        Toast.makeText(context.getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showMessageShort(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showMessageLong(Context context, int resId) {
        Toast.makeText(context.getApplicationContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showMessageLong(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
