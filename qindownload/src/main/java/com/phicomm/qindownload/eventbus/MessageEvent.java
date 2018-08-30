package com.phicomm.qindownload.eventbus;

/**
 * @author qinxiang.zhu on 2018/8/22.
 * Copyright (C) 2018 Phicomm.
 */
public class MessageEvent{
    private String message;
    public  MessageEvent(String message){
        this.message=message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
