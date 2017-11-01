package com.china317.developlibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DisplayUtil {

    public final static String TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_WITH_TIME = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_WITHOUT_TIME = "yyyy-MM-dd";

    public static int DipToPx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int pxToDp(Context context, float PxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (PxValue * scale);
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        return displayMetrics.widthPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static int getVirtualBarHeigh(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }


    public static String getDisplayTime(long savedTime) {
        return getDisplayTime(String.valueOf(savedTime));
    }

    public static String getDisplayTime(String savedTime) {
        // yyyy年-MM月-dd日-HH:mm
        String formatString = "yyyy年-MM月-dd日-HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);

        String currentTimeString = dateFormat.format(new Date());
        String savedTimeString = dateFormat.format(new Date(Long
                .valueOf(savedTime)));

        String[] curStrings = currentTimeString.split("-");
        String[] saveStrings = savedTimeString.split("-");

        if (!curStrings[0].equals(saveStrings[0])) {
            return saveStrings[0] + saveStrings[1];
        } else if (!curStrings[1].equals(saveStrings[1])) {
            return saveStrings[1] + saveStrings[2];
        } else if (!curStrings[2].equals(saveStrings[2])) {
            return saveStrings[1] + saveStrings[2];
        } else {
            return saveStrings[3];
        }
    }


    public static String getDisplayTime(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        String currentTimeString = dateFormat.format(date);

        return currentTimeString;
    }

    public static Date getDateFromString(String value, String format) {
        Date date;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        simpleDateFormat.setTimeZone(TimeZone.getDefault());

        if (value == null || value.length() == 0) {
            return new Date();
        }

        try {
            date = simpleDateFormat.parse(value);
        } catch (ParseException e) {
            date = new Date();
        }

        return date;
    }

    public static String getFullTimeDesc(long gpsTime, String format) {
        Date date = new Date(gpsTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        dateFormat.setTimeZone(TimeZone.getDefault());

        return dateFormat.format(date);
    }

    public static String getFullTimeDesc(long gpsTime) {
        Date date = new Date(gpsTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                TIME_FORMAT_STRING);

        dateFormat.setTimeZone(TimeZone.getDefault());

        return dateFormat.format(date);
    }


    public static long getCurrentTime() {
        return new Date().getTime();
    }
}
