package com.china317.developlibrary.version.checkIsDownLoad;

import android.app.ProgressDialog;
import android.content.Context;

import com.china317.developlibrary.okHttp.OkHttpUtils;
import com.china317.developlibrary.okHttp.callback.FileCallBack;
import com.china317.developlibrary.utils.StorageUtils;

import java.io.File;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/13.
 */

public class DownLoadAction {

    private ProgressDialog mProgressDialog;

    public static DownLoadAction getInstance() {
        return DownLoadFactory.INSTANCE;
    }


    public void goToDownload(Context mContext, String downloadUrl, final DownLoadManager downLoad) {

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();

        File dir = StorageUtils.getCacheDirectory(mContext);
        String apkName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1, downloadUrl.length());

        OkHttpUtils
                .get()
                .url(downloadUrl)
                .build()
                .execute(new FileCallBack(dir.toString(), apkName) {

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        mProgressDialog.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        downLoad.failVersion();
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        downLoad.successVersion(response);
                        mProgressDialog.dismiss();
                    }
                });
    }


    private static class DownLoadFactory {
        public static final DownLoadAction INSTANCE = new DownLoadAction();
    }

}
