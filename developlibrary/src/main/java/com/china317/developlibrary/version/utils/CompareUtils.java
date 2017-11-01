package com.china317.developlibrary.version.utils;

/**
 * Created by Administrator on 2016/7/28.
 */
public class CompareUtils {


    public final static int MUST_UPDATE = 1;
    public final static int CAN_UPDATE = 2;
    public final static int NO_UPDATE = 3;


    public static int compareString(String minVersion, String maxVersion, String cruVersion) {


        String items[] = minVersion.split("\\.");

        String items1[] = maxVersion.split("\\.");

        String items2[] = cruVersion.split("\\.");


        for (int i = 0; i < items.length; i++) {
            if (Integer.valueOf(items[i]) != Integer.valueOf(items2[i])) {
                if (Integer.valueOf(items[i]) > Integer.valueOf(items2[i])) {
                    return MUST_UPDATE;
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < items.length; i++) {
            if (Integer.valueOf(items2[i]) != Integer.valueOf(items1[i])) {
                if (Integer.valueOf(items2[i]) < Integer.valueOf(items1[i])) {
                    return CAN_UPDATE;
                } else {
                    break;
                }

            }
        }
        return NO_UPDATE;


    }
}
