package com.trip.loadupload_android.app;

import android.app.Application;
import android.os.Environment;

import com.china317.developlibrary.utils.JsonHelper;
import com.trip.loadupload_android.constant.Api;
import com.trip.loadupload_android.data.FileSave;
import com.trip.loadupload_android.utils.FileUtils;
import com.trip.loadupload_android.utils.GetPhoneUtils;
import com.trip.loadupload_android.utils.PrefsUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/7/6.
 */

public class MyApplication extends Application {


    private String filepath = Environment.getExternalStorageDirectory().getPath()
            + "/foo/wifiabc/conf/wifiabc.properties";
    private File locationfile;


    @Override
    public void onCreate() {
        super.onCreate();
        GetPhoneUtils.getInstance(this).startListener();
        PrefsUtils.init(this);
        initFile();
    }


    private void initFile() {
        if (FileUtils.ExistSDCard()) {
            if (FileUtils.createOrExistsFile(filepath)) {
                locationfile = new File(filepath);
            }
            FileUtils.writeFileFromString(locationfile,
                    JsonHelper.getInstance().toJson(new FileSave(Api.BaseUrl, "60")), false);
        }
    }


}
