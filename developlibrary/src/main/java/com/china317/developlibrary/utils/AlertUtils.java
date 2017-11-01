package com.china317.developlibrary.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Administrator on 2016/11/7.
 */

public class AlertUtils {


    public static void showAlert(Context context, String message,
                                 String buttonText, DialogInterface.OnClickListener onClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonText, onClickListener);
        builder.show();


    }

    public static void showAlert(Context context, String title, String message,
                                  String buttonText, DialogInterface.OnClickListener onClickListenerSure,
                                  String buttonTextCancel, DialogInterface.OnClickListener onClickListenerCancel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonText, onClickListenerSure)
                .setNegativeButton(buttonTextCancel, onClickListenerCancel);

        builder.show();


    }

    public static void showAlert(Context context, String title, String message,
                                  String buttonText, DialogInterface.OnClickListener onClickListenerSure
    ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonText, onClickListenerSure);

        builder.show();


    }

}
