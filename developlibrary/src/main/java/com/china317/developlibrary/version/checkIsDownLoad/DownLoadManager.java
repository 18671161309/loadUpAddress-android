package com.china317.developlibrary.version.checkIsDownLoad;

import java.io.File;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface DownLoadManager {

    void successVersion(File m);
    void failVersion();

}
