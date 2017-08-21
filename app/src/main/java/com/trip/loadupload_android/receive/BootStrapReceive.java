package com.trip.loadupload_android.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.trip.loadupload_android.MainActivity;
import com.trip.loadupload_android.constant.Constant;
import com.trip.loadupload_android.service.BootStrapService;
import com.trip.loadupload_android.utils.PrefsUtils;

/**
 * Created by Administrator on 2017/7/5.
 */

public class BootStrapReceive extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent) {
        Log.e("Intent", intent.getAction());
        if (intent.getAction().equals(Constant.ACTION)) {
            PrefsUtils.getInstance().writePref(Constant.FIXED_PARAMETER, false);
            Intent newIntent = new Intent(context, MainActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);
        }
    }

}
