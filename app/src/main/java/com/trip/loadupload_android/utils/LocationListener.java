package com.trip.loadupload_android.utils;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;


public class LocationListener implements AMapLocationListener {

    private static LocationListener ourInstance;
    private PostLocationListener mListener;
    private Context mContext;

    public static LocationListener getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LocationListener(context);
        }
        return ourInstance;
    }

    private LocationListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

        mListener.postLocation(aMapLocation);
    }

    public void setPostLocationListener(PostLocationListener listener) {
        mListener = listener;
    }


    public interface PostLocationListener {
        public void postLocation(AMapLocation positionMessage);
    }


}
