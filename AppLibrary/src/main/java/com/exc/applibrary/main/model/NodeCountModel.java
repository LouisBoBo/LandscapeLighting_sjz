package com.exc.applibrary.main.model;

public class NodeCountModel {

    /**
     * code : 200
     * data : {"nodeNum":19,"offLineNum":0,"offLineRate":0}
     * message : SUCCESS
     */

    private int code;
    /**
     * nodeNum : 19
     * offLineNum : 0
     * offLineRate : 0
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
        private int nodeNum;
        private int offLineNum;
        private int offLineRate;

        public int getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(int nodeNum) {
            this.nodeNum = nodeNum;
        }

        public int getOffLineNum() {
            return offLineNum;
        }

        public void setOffLineNum(int offLineNum) {
            this.offLineNum = offLineNum;
        }

        public int getOffLineRate() {
            return offLineRate;
        }

        public void setOffLineRate(int offLineRate) {
            this.offLineRate = offLineRate;
        }
    }
}
