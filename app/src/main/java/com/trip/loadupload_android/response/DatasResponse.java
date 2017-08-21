package com.trip.loadupload_android.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/7/5.
 */

public class DatasResponse {

    public String android;
    @SerializedName("gw_mac")
    public String gwMac;
    public int interval;
    public int code;
    public String msg;

}
