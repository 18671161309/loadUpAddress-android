package com.china317.developlibrary.version;

import android.content.Context;
import android.content.DialogInterface;

import com.china317.developlibrary.common.Constant;
import com.china317.developlibrary.okHttp.OkHttpUtils;
import com.china317.developlibrary.okHttp.callback.GenericsCallback;
import com.china317.developlibrary.okHttp.callback.JsonGenericsSerializator;
import com.china317.developlibrary.utils.AlertUtils;
import com.china317.developlibrary.utils.JsonHelper;
import com.china317.developlibrary.version.checkIsDownLoad.UpdateAction;
import com.china317.developlibrary.version.pojo.VersionData;
import com.china317.developlibrary.version.request.VersionRequest;
import com.china317.developlibrary.version.response.VersionResponse;
import com.china317.developlibrary.version.utils.AppUtils;
import com.china317.developlibrary.version.utils.CompareUtils;

import okhttp3.Call;
import okhttp3.MediaType;

import static com.china317.developlibrary.common.Constant.API_SERVER;

/**
 * Created by Administrator on 2017/2/13.
 */

public class UpdateVersionManager {

    private static Context mContext;
    private UpdateAction mAction;


    public static void init(Context context) {
        mContext = context;
    }

    public static UpdateVersionManager getInstance() {
        return UpdateFactory.INSTANCE;
    }

    private static class UpdateFactory {
        public static final UpdateVersionManager INSTANCE = new UpdateVersionManager();
    }


    public void requestVersionInfo() {
        OkHttpUtils
                .postString()
                .url(Constant.PROTOCOL_VERSION_UPDATE)
                .mediaType(MediaType.parse(Constant.TYPE))
                .content(JsonHelper.getInstance().toJson(new VersionRequest(Constant.EVN, Constant.OS)))
                .addHeader("account_sid", Constant.ACCOUNT_SID)
                .addHeader("auth_token", Constant.AUTO_TOKEN)
                .addHeader("app_code", Constant.APP_CODE)
                .build()
                .execute(new GenericsCallback<VersionResponse>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        AlertUtils.showAlert(mContext, "提示", "再次请求", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestVersionInfo();
                            }
                        });
                    }

                    @Override
                    public void onResponse(VersionResponse response, int id) {
                        checkVersion(response.mResult.data);
                    }
                });
    }


    public void setListenerVersionUpdate(UpdateAction action) {
        mAction = action;
    }


    public void checkVersion(VersionData data) {
        String versionCode = AppUtils.getVersionName(mContext);

        if (CompareUtils.compareString(data.minVersion, data.version, versionCode)
                == CompareUtils.MUST_UPDATE) {
            AlertUtils.showAlert(mContext, "版本更新", data.description,
                    "立即更新", (d, w) -> {
                        mAction.UpdateVersion(data);
                    });
        } else if (CompareUtils.compareString(data.minVersion, data.version, versionCode)
                == CompareUtils.CAN_UPDATE) {
            AlertUtils.showAlert(mContext, "版本更新", data.description,
                    "立即更新", (dialog, which) -> {
                        mAction.UpdateVersion(data);
                    }, "稍后再说", (dialog, which) -> {
                        mAction.noUpdateVersion();
                    });
        } else {
            mAction.noUpdateVersion();
        }

    }


}
