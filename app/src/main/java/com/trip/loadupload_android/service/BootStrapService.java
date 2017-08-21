package com.trip.loadupload_android.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.trip.developlobrary.okHttp.OkHttpUtils;
import com.trip.developlobrary.okHttp.callback.GenericsCallback;
import com.trip.developlobrary.okHttp.callback.JsonGenericsSerializator;
import com.trip.developlobrary.utils.JsonHelper;
import com.trip.loadupload_android.constant.Api;
import com.trip.loadupload_android.constant.Constant;
import com.trip.loadupload_android.data.PhoneDatas;
import com.trip.loadupload_android.response.DatasResponse;
import com.trip.loadupload_android.utils.GetPhoneUtils;
import com.trip.loadupload_android.utils.LocationListener;
import com.trip.loadupload_android.utils.LocationUtils;
import com.trip.loadupload_android.utils.PrefsUtils;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by Administrator on 2017/7/5.
 */

public class BootStrapService extends IntentService implements
        LocationListener.PostLocationListener {

    private static final String TAG = "BootStrapService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name the worker thread, important only for debugging.
     */
    public BootStrapService() {
        super("BootStrapService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        startService();
    }


    private void startService() {
        LocationUtils.getInstance(this).startLocation();
        LocationListener.getInstance(this).setPostLocationListener(this);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void postLocation(AMapLocation positionMessage) {

        PhoneDatas datas = new PhoneDatas();
        if (!PrefsUtils.getInstance().readPref(Constant.FIXED_PARAMETER, false)) {

            datas.imei = GetPhoneUtils.getInstance(this).getIMEI();
            datas.imsi = GetPhoneUtils.getInstance(this).getIMSI();
            datas.iccid = GetPhoneUtils.getInstance(this).getICCID();
            datas.mcc = GetPhoneUtils.getInstance(this).getMCC();
            datas.mnc = GetPhoneUtils.getInstance(this).getMNC();
        }

        datas.gwMac = GetPhoneUtils.getInstance(this).getMac().toLowerCase();
        datas.lacCode = GetPhoneUtils.getInstance(this).getLAC();
        datas.cellId = GetPhoneUtils.getInstance(this).getCELLID();
        datas.rssi = String.valueOf(GetPhoneUtils.getInstance(this).getGsmDB());
        datas.lat = positionMessage.getLatitude();
        datas.lon = positionMessage.getLongitude();

        String content = JsonHelper.getInstance().toJson(datas);
        Log.e(TAG, content);
        OkHttpUtils.postString()
                .url(Api.BaseUrl)
                .content(JsonHelper.getInstance().toJson(content))
                .mediaType(MediaType.parse(Constant.TYPE))
                .build()
                .execute(new GenericsCallback<DatasResponse>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(DatasResponse response, int id) {
                        if (response.code == 200) {
                            PrefsUtils.getInstance().writePref(Constant.FIXED_PARAMETER, true);
                        } else if (response.code == 201) {
                            PrefsUtils.getInstance().writePref(Constant.FIXED_PARAMETER, false);
                        }
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
