package com.phicomm.qindownload;

import android.util.Log;

import com.phicomm.qindownload.listener.IDownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.net.sip.SipErrorCode.TIME_OUT;

/**
 * @author qinxiang.zhu on 2018/8/17.
 * Copyright (C) 2018 Phicomm.
 */
public class DownloadUtils {

    public static final int THREAD_NUM = 5;

    private volatile boolean isCancel, isStop, isDownloading;

    private IDownloadListener mListener;

    private String configFPath;

    private long mCurrentLocation;

    private int mCancelNum, mStopNum, mCompleteThreadNum;

    private synchronized void writeConfig(String fileName, String location) {

    }



    private class DownloadTask implements Runnable {

        private static final String TAG = "DownloadTask";
        private DownloadEntity dEntity;
        private String configPath;

        public DownloadTask(DownloadEntity dEntity) {
            this.dEntity = dEntity;
            this.configPath = dEntity.context.getFilesDir().getPath() + "/temp/" + dEntity.tempFile.getName() + ".properties";
        }

        @Override
        public void run() {
            try {
                Log.d(TAG, "线程_" + dEntity.threadId + "_正在下载【"
                        + "开始位置 : " + dEntity.startLocation + "， 结束位置： " + dEntity
                        .endLocation + "】 ");
                URL url = new URL(dEntity.downloadUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//在头里面请求下载开始位置和结束位置
                conn.setRequestProperty("Range", "bytes=" + dEntity.startLocation + "-" + dEntity.endLocation);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setConnectTimeout(TIME_OUT);
                conn.setRequestProperty("User-Agent", "Mozilla/4" +
                        ".0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR" +
                "1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3." +
                "0.4506.2152; .NET CLR 3.5.30729)");
                conn.setRequestProperty("Accept", "image/gif, im" +
                        "age/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flas" +
                        "h, application/xaml+xml, application/vnd.ms-xpsdocument, applica" +
                        "tion/x-ms-xbap, application/x-ms-application, application/vnd.ms" +
                                "-excel, application/vnd.ms-powerpoint, application/msword, */*");
                conn.setReadTimeout(2000); //设置读取流的等待时间,
//                必须设置该参数
                InputStream is = conn.getInputStream();
                //创建可设置位置的文件
                RandomAccessFile file = new RandomAccessFile(dEntity.tempFile, "rwd");
//设置每条线程写入文件的位置
                file.seek(dEntity.startLocation);
                byte[] buffer = new byte[1024];
                int len;
//当前子线程的下载位置
                long currentLocation = dEntity.startLocation;
                while ((len = is.read(buffer)) != -1) {
                    if (isCancel) {
                        Log.d(TAG, "++++++++++ thread_" + dEntity.
                                threadId + "_cancel ++++++++++");
                        break;
                    } if (isStop) {
                        break;
                    } //把下载数据数据写入文件
                    file.write(buffer, 0, len);
                    synchronized (DownloadUtils.this) {
                        mCurrentLocation += len;
                        mListener.onProgress(mCurrentLocation);
                    } currentLocation += len;
                }
                file.close();
                is.close();
                if (isCancel) {
                    synchronized (DownloadUtils.this) {
                        mCancelNum++;
                        if (mCancelNum == THREAD_NUM) {
                            File configFile = new File(configFPath);
                            if (configFile.exists()) {
                                configFile.delete();
                            }
                            if (dEntity.tempFile.exists()) {
                                dEntity.tempFile.delete();
                            }
                            Log.d(TAG, "++++++++++++++++ onCancel +++++++++++++++++");
                            isDownloading = false;
                            mListener.onCancel();
                            System.gc();
                        }
                    }
                    return;
                } //停止状态不需要删除记录文件
                if (isStop) {
                    synchronized (DownloadUtils.this) {
                        mStopNum++;
                        String location = String.valueOf(currentLocation);
                        Log.i(TAG, "thread_" + dEntity.threadId +
                                "_stop, stop location ==> " + currentLocation);
                        writeConfig(dEntity.tempFile.getName() +
                                "_record_" + dEntity.threadId, location);
                        if (mStopNum == THREAD_NUM) {
                            Log.d(TAG, "++++++++++++++++ onStop +++++++++++++++++");
                            isDownloading = false;
                            mListener.onStop(mCurrentLocation);
                            System.gc();
                        }
                    }
                    return;
                }
                Log.i(TAG, "线程【" + dEntity.threadId + "】 下载完毕"
                        );
                writeConfig(dEntity.tempFile.getName() + "_state_" + dEntity.threadId, 1 + "");
                mListener.onChildComplete(dEntity.endLocation);
                mCompleteThreadNum++;
                if (mCompleteThreadNum == THREAD_NUM) {
                    File configFile = new File(configFPath);
                    if (configFile.exists()) {
                        configFile.delete();
                    } mListener.onComplete();
                    isDownloading = false;
                    System.gc();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                isDownloading = false;
                mListener.onFail();
            } catch (IOException e) {
                Log.e(TAG, "下载失败【" + dEntity.downloadUrl + "】 ");
                isDownloading = false;
                mListener.onFail();
            } catch (Exception e) {
                isDownloading = false;
                mListener.onFail();
            }
        }
    }

}
