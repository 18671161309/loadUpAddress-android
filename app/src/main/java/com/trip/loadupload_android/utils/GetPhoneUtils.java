package com.trip.loadupload_android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


/**
 * Created by Administrator on 2017/7/4.
 */

public class GetPhoneUtils {


    private TelephonyManager telephonyManager;
    private static Context cxt;
    private PhoneStatListener mListener;
    private GsmCellLocation location;


    private int gsmDB;

    private GetPhoneUtils(Context context) {
        telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        location = (GsmCellLocation) telephonyManager.getCellLocation();
        mListener = new PhoneStatListener();
    }

    public static GetPhoneUtils getInstance(Context context) {
        if (cxt == null) {
            cxt = context;
        }
        return GetPhoneUtils.Factory.DEFAULT_HELPER;
    }

    static class Factory {
        public final static GetPhoneUtils DEFAULT_HELPER = new GetPhoneUtils(cxt);
    }

    public static String getMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取手机IMEI号
     */
    public String getIMEI() {
        String imei = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(imei)) {
            return "";
        }
        return imei;
    }

    /**
     * 获取手机IMSI号
     */
    public String getIMSI() {
        String imsi = telephonyManager.getSubscriberId();
        if (TextUtils.isEmpty(imsi)) {
            return "";
        }
        return imsi;
    }

    /**
     * 获取手机ICCID号
     */
    public String getICCID() {
        String iccid = telephonyManager.getSimSerialNumber();
        if (TextUtils.isEmpty(iccid)) {
            return "";
        }
        return iccid;
    }

    /**
     * 获取手机MCC号
     */
    public String getMCC() {
        String networkOperator = telephonyManager.getNetworkOperator();
        if (TextUtils.isEmpty(networkOperator)) {
            return "";
        }
        return networkOperator.substring(0, 3);
    }


    /**
     * 获取MNC
     */
    public String getMNC() {
        String operator = telephonyManager.getNetworkOperator();
        if (TextUtils.isEmpty(operator)) {
            return "";
        }
        return operator.substring(3);
    }

    /**
     * 获取LAC
     */
    public String getLAC() {
        try {
            String lac = String.valueOf(location.getLac());
            return lac;
        } catch (Exception e) {
            return "";
        }


    }

    /**
     * 获取cellId
     */
    public String getCELLID() {
        try {
            String cellId = String.valueOf(location.getCid());
            return cellId;
        } catch (Exception e) {
            return "";
        }


    }


    /**
     * wifi
     *
     * @param
     * @return
     */
    public String obtainWifiInfo() {
        // Wifi的连接速度及信号强度：
        WifiManager wifiManager = (WifiManager) cxt.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        if (info.getBSSID() != null) {
            // 链接信号强度
            return String.valueOf(info.getRssi());
        }
        return "0";
    }


    public void startListener() {

        String simSer = telephonyManager.getSimSerialNumber();
        if (simSer == null || simSer.equals("")) {
            gsmDB = 0;
        } else {
            telephonyManager.listen(mListener, PhoneStatListener.LISTEN_SIGNAL_STRENGTHS);
        }
    }


    private class PhoneStatListener extends PhoneStateListener {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);

            int asu = signalStrength.getGsmSignalStrength();
            gsmDB = -113 + 2 * asu;


        }
    }

    public int getGsmDB() {
        if (gsmDB < -90) {
            gsmDB = 0;
        }
        return gsmDB;
    }


}


