package com.china317.developlibrary.version.request;

/**
 * Created by Administrator on 2017/3/9.
 */

public class VersionRequest {


    public String env;
    public String os;

    public VersionRequest(String env, String os){
        this.env = env;
        this.os = os;
    }
}
