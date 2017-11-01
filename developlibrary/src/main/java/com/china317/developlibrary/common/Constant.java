package com.china317.developlibrary.common;

/**
 * Created by Administrator on 2016/11/4.
 */

public class Constant {
    public static String API_SERVER="http://120.26.129.53:8080/commonplatform/rest";
    public final static String PROTOCOL_VERSION_UPDATE = API_SERVER+"/version/getCurrentVersion";
    public final static String PROTOCOL_SEND_REPORT_DEVICE = API_SERVER+"/notification/reportDevice";
    public final static String PROTOCOL_SEND_REPORT_LOGOUTDEVICEE = API_SERVER+"/notification/logoutDevice";

    public static String EVN = "prod";
    public static String OS = "android";
    public final static String TYPE = "application/json; charset=utf-8";

    public final static String ACCOUNT_SID = "1a0f44b440a7622612aa3c20a9d30ef3252d8eae";
    public final static String AUTO_TOKEN = "6c4c46bf43814921d478ee8ae3262fd8b5c78e99";
    public final static String APP_CODE = "3e2ac679bc561568569525e1489486cd7b74c2e4";
}
