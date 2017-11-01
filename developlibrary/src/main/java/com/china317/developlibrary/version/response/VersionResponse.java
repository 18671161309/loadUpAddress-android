package com.china317.developlibrary.version.response;

import com.china317.developlibrary.version.pojo.VersionData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/3/9.
 */

public class VersionResponse {

    @SerializedName("status")
    public String mStatus;

    @SerializedName("result")
    public Results mResult;

    public class Results{
         @SerializedName("statusCode")
         public String mStatusCode;
         @SerializedName("statusMsg")
         public String mStatusMsg;

         public VersionData data;
    }

}
