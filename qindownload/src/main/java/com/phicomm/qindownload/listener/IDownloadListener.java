package com.phicomm.qindownload.listener;

import java.net.HttpURLConnection;

/**
 * @author qinxiang.zhu on 2018/8/17.
 * Copyright (C) 2018 Phicomm.
 */
public interface IDownloadListener {

    void onCancel();

    void onFail();

    void onPreDownload(HttpURLConnection connection);

    void onProgress(long currentLocation);

    void onChildComplete(long finishLocation);

    void onStart(long startLocation);

    void onChildResume(long resumeLocation);

    void onResume(long resumeLocation);

    void onStop(long stopLocation);

    void onComplete();


}
