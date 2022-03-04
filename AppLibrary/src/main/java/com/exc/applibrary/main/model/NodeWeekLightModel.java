package com.exc.applibrary.main.model;

import java.util.List;

public class NodeWeekLightModel {

    /**
     * code : 200
     * data : {"data":null,"date":null,"dates":["11-16","11-17","11-18","11-19","11-20","11-21","11-22"],"numbers":[83,83,67,67,67,67,67],"offlineRate":null,"onlineRate":null}
     * message : SUCCESS
     * time : null
     */

    private int code;
    /**
     * data : null
     * date : null
     * dates : ["11-16","11-17","11-18","11-19","11-20","11-21","11-22"]
     * numbers : [83,83,67,67,67,67,67]
     * offlineRate : null
     * onlineRate : null
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
        private Object data;
        private Object date;
        private Object offlineRate;
        private Object onlineRate;
        private List<String> dates;
        private List<Integer> numbers;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public Object getOfflineRate() {
            return offlineRate;
        }

        public void setOfflineRate(Object offlineRate) {
            this.offlineRate = offlineRate;
        }

        public Object getOnlineRate() {
            return onlineRate;
        }

        public void setOnlineRate(Object onlineRate) {
            this.onlineRate = onlineRate;
        }

        public List<String> getDates() {
            return dates;
        }

        public void setDates(List<String> dates) {
            this.dates = dates;
        }

        public List<Integer> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }
    }
}
