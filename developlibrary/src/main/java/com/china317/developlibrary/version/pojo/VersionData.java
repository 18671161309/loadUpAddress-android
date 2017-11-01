package com.china317.developlibrary.version.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/2/13.
 */

public class VersionData {

    public String appName;
    public String version;
    public String minVersion;
    public String os;
    @SerializedName("downloadUrl")
    public String url;
    @SerializedName("updateTime")
    public String time;
    public String description;

}
