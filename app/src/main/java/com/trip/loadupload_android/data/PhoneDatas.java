package com.trip.loadupload_android.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/7/5.
 */

public class PhoneDatas {

    @SerializedName("gw_mac")
    public String gwMac;
    public String imei;
    public String imsi;
    public String iccid;
    public String mcc;
    public String mnc;
    @SerializedName("lac_code")
    public String lacCode;
    @SerializedName("cell_id")
    public String cellId;
    public String rssi;
    public double lat;
    public double lon;

}
