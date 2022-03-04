package com.exc.applibrary.main.model;

public class BaseBean2 {
    //{"code":200,"message":"SUCCESS!"}
    private int code;
    private String message;


    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private int data;//消息接收通知开关，0开启通知  1关闭通知

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
