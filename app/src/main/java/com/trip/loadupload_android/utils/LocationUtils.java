package com.trip.loadupload_android.utils;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.trip.loadupload_android.constant.Constant;


public class LocationUtils {
    private static LocationUtils ourInstance;
    private Context mContext;
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = LocationListener.getInstance(null);

    public static LocationUtils getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LocationUtils(context);
        }
        return ourInstance;
    }

    private LocationUtils(Context context) {
        this.mContext = context;
    }

    public AMapLocationClient getmLocationClient() {
        if (mLocationClient == null) {
            //初始化定位
            mLocationClient = new AMapLocationClient(mContext);
        }
        if (mLocationOption == null) {
            //初始化AMapLocationClientOption对象
            mLocationOption = new AMapLocationClientOption();
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(Constant.INTERVAL);
        //启用缓存策略，位置未改变时返回之前的结果
        mLocationOption.setLocationCacheEnable(true);
        //设置是否返回地址描述信息（默认返回地址信息）
        mLocationOption.setNeedAddress(false);
        //设置是否强制刷新WIFI，默认为true，强制刷新。每次定位主动刷新WIFI模块会提升WIFI定位精度，但相应的会多付出一些电量消耗。
        mLocationOption.setWifiActiveScan(false);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //单个定位客户端生命周期内（调用AMapLocationClient.onDestroy()方法结束生命周期）设置一次即可。
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        return mLocationClient;
    }

    public void startLocation() {
        //启动定位
        if (!getmLocationClient().isStarted()) {
            getmLocationClient().startLocation();
        }
    }

    public void stopLocation() {
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        //  销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}
