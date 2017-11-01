package com.china317.developlibrary.okHttp.utils;

import android.util.Log;


public class L
{
    private static boolean debug = false;

    public static void e(String msg)
    {
        if (debug)
        {
            Log.e("OkHttp", msg);
        }
    }

}

