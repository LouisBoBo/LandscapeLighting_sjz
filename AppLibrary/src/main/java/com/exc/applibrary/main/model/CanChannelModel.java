package com.exc.applibrary.main.model;

public class CanChannelModel {

    /**
     * id : 400100
     * name : 驱动模块,AAL,MH,134,20_1.1.9,通道1
     * canChannelTypeId : 4
     */

    private int id;
    private String name;
    private int canChannelTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCanChannelTypeId() {
        return canChannelTypeId;
    }

    public void setCanChannelTypeId(int canChannelTypeId) {
        this.canChannelTypeId = canChannelTypeId;
    }
}
