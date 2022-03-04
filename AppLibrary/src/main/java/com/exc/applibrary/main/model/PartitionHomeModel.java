package com.exc.applibrary.main.model;

public class PartitionHomeModel {

    /**
     * code : 200
     * data : {"dataSource":1,"description":"武昌区","offlineNum":0,"offlineRate":100,"onlineNum":0,"onlineRate":0,"totalEnergy":0,"totalNum":111}
     * message : SUCCESS
     */

    private int code;
    /**
     * dataSource : 1
     * description : 武昌区
     * offlineNum : 0
     * offlineRate : 100
     * onlineNum : 0
     * onlineRate : 0
     * totalEnergy : 0.0
     * totalNum : 111
     */

    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private int dataSource;
        private String description;
        private int offlineNum;
        private int offlineRate;
        private int onlineNum;
        private int onlineRate;
        private double totalEnergy;
        private int totalNum;

        public int getDataSource() {
            return dataSource;
        }

        public void setDataSource(int dataSource) {
            this.dataSource = dataSource;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getOfflineNum() {
            return offlineNum;
        }

        public void setOfflineNum(int offlineNum) {
            this.offlineNum = offlineNum;
        }

        public int getOfflineRate() {
            return offlineRate;
        }

        public void setOfflineRate(int offlineRate) {
            this.offlineRate = offlineRate;
        }

        public int getOnlineNum() {
            return onlineNum;
        }

        public void setOnlineNum(int onlineNum) {
            this.onlineNum = onlineNum;
        }

        public int getOnlineRate() {
            return onlineRate;
        }

        public void setOnlineRate(int onlineRate) {
            this.onlineRate = onlineRate;
        }

        public double getTotalEnergy() {
            return totalEnergy;
        }

        public void setTotalEnergy(double totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }
    }
}
