package com.exc.applibrary.main.model;


public class LoopSearchEventBean   {

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    private boolean isInit;


    /**
     * 站点（建筑物名称）
     */
    private String buildingName;

    /**
     * 分区id
     */
    private int partitionId = -1;

    /**
     * 站点id
     */
    private int siteId = -1;
    /**
     * 节点id
     */
    private int nid = -1;
    /**
     * 回路类型id
     */
    private int channelTypeId = -1;
    /**
     * 回路状态（1开启，0关闭）
     */
    private int status = -1;
    /**
     * 节点在线状态（1离线，0在线）
     */
    private int offline = -1;


    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(int channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }


}
