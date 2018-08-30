package com.phicomm.qindownload.listener;

import java.net.HttpURLConnection;

/**
 * @author qinxiang.zhu on 2018/8/17.
 * Copyright (C) 2018 Phicomm.
 */
public class DownloadListener implements IDownloadListener {
    @Override
    public void onCancel() {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onPreDownload(HttpURLConnection connection) {

    }

    @Override
    public void onProgress(long currentLocation) {

    }

    @Override
    public void onChildComplete(long finishLocation) {

    }

    @Override
    public void onStart(long startLocation) {

    }

    @Override
    public void onChildResume(long resumeLocation) {

    }

    @Override
    public void onResume(long resumeLocation) {

    }

    @Override
    public void onStop(long stopLocation) {

    }

    @Override
    public void onComplete() {

    }
}
