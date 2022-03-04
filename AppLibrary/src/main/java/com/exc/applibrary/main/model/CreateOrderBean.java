package com.exc.applibrary.main.model;

import java.util.List;

public class CreateOrderBean {
    private String name;
    private int faultTypeId;
    private int partitionId;
    private String addr;
    private String description;
    private List<?> faultIdList;
    private List<Integer> imgIdList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFaultTypeId() {
        return faultTypeId;
    }

    public void setFaultTypeId(int faultTypeId) {
        this.faultTypeId = faultTypeId;
    }

    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<?> getFaultIdList() {
        return faultIdList;
    }

    public void setFaultIdList(List<?> faultIdList) {
        this.faultIdList = faultIdList;
    }

    public List<Integer> getImgIdList() {
        return imgIdList;
    }

    public void setImgIdList(List<Integer> imgIdList) {
        this.imgIdList = imgIdList;
    }
}
