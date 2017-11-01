package com.china317.developlibrary.version.checkIsDownLoad;


import com.china317.developlibrary.version.pojo.VersionData;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface UpdateAction {

    void noUpdateVersion();

    void UpdateVersion(VersionData data);


}
