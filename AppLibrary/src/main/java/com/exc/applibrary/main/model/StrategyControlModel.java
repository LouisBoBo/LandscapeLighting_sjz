package com.exc.applibrary.main.model;

import java.util.List;

public class StrategyControlModel {

    /**
     * cycleTypes : [1,2,3,4,5,6,7]
     * description : 11111111111
     * endDate : null
     * id : 2
     * mode : 1
     * name : 11111
     * pointInfos : null
     * startDate :
     * status : 1
     */

    private String description;
    private Object endDate;
    private int id;
    private int mode;
    private String name;
    private Object pointInfos;
    private String startDate;
    private int status;
    private List<Integer> cycleTypes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPointInfos() {
        return pointInfos;
    }

    public void setPointInfos(Object pointInfos) {
        this.pointInfos = pointInfos;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Integer> getCycleTypes() {
        return cycleTypes;
    }

    public void setCycleTypes(List<Integer> cycleTypes) {
        this.cycleTypes = cycleTypes;
    }
}
