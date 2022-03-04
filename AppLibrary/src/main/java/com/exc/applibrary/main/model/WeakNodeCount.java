package com.exc.applibrary.main.model;

public class WeakNodeCount {


    /**
     * code : 200
     * data : {"count":14,"offLine":14,"onLine":0}
     * message : SUCCESS
     * time : null
     */

    private int code;
    /**
     * count : 14
     * offLine : 14
     * onLine : 0
     */

    private DataBean data;
    private String message;
    private Object time;

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

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public static class DataBean {
        private int count;
        private int offLine;
        private int onLine;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getOffLine() {
            return offLine;
        }

        public void setOffLine(int offLine) {
            this.offLine = offLine;
        }

        public int getOnLine() {
            return onLine;
        }

        public void setOnLine(int onLine) {
            this.onLine = onLine;
        }
    }
}
