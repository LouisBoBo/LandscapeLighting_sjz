package com.exc.applibrary.main.model;


import java.io.Serializable;
import java.util.List;

public class ShowIngListData implements Serializable {

    private int nowPlayTime;
    private int howLongEnd;
    private int code;
    private List<String> nowPlayFileList;

    public int getNowPlayTime() {
        return nowPlayTime;
    }

    public void setNowPlayTime(int nowPlayTime) {
        this.nowPlayTime = nowPlayTime;
    }

    public int getHowLongEnd() {
        return howLongEnd;
    }

    public void setHowLongEnd(int howLongEnd) {
        this.howLongEnd = howLongEnd;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getNowPlayFileList() {
        return nowPlayFileList;
    }

    public void setNowPlayFileList(List<String> nowPlayFileList) {
        this.nowPlayFileList = nowPlayFileList;
    }

}
