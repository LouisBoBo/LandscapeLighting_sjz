package com.exc.applibrary.main.model;

public class ProjectionModel {
    private String device_name;
    private String device_ip;
    private Boolean is_select;
    private int style;//1服务器 2开机卡 3投影

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public Boolean getIs_select() {
        return is_select;
    }

    public void setIs_select(Boolean is_select) {
        this.is_select = is_select;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_ip() {
        return device_ip;
    }

    public void setDevice_ip(String device_ip) {
        this.device_ip = device_ip;
    }
}
