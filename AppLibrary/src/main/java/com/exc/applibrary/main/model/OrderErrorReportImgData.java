package com.exc.applibrary.main.model;

public class OrderErrorReportImgData {
    private String imgUrl;
    private int id;
    private boolean isVirtual;
    private String filename;
    private boolean isXC;//是否是相册图片


    public boolean isXC() {
        return isXC;
    }

    public void setXC(boolean XC) {
        isXC = XC;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public boolean isVirtual() {
        return isVirtual;
    }

    public void setVirtual(boolean virtual) {
        isVirtual = virtual;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
