package com.phicomm.qindownload;

import android.content.Context;

import java.io.File;

/**
 * @author qinxiang.zhu on 2018/8/17.
 * Copyright (C) 2018 Phicomm.
 */
public class DownloadEntity {
    //文件总长度
    long fileSize;
    //下载链接
    String downloadUrl;
    //线程Id
    int threadId;
    //起始下载位置
    long startLocation;
    //结束下载的文章
    long endLocation;
    //下载文件
    File tempFile;
    Context context;
    public DownloadEntity(Context context, long fileSize, String downloadUrl,
                          File file, int threadId, long startLocation, long endLocation) {
        this.fileSize = fileSize;
        this.downloadUrl = downloadUrl;
        this.tempFile = file;
        this.threadId = threadId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.context = context;
    }
}
