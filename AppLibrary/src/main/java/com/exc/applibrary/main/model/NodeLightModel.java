package com.exc.applibrary.main.model;

import java.util.List;

public class NodeLightModel {

    /**
     * code : 200
     * data : {"data":[{"num":0,"time":1},{"num":0,"time":2},{"num":0,"time":3},{"num":0,"time":4},{"num":0,"time":5},{"num":0,"time":6},{"num":0,"time":7},{"num":0,"time":8},{"num":0,"time":9},{"num":0,"time":10},{"num":0,"time":11},{"num":0,"time":12},{"num":0,"time":13},{"num":0,"time":14},{"num":0,"time":15},{"num":0,"time":16},{"num":9,"time":17},{"num":0,"time":18},{"num":9,"time":19},{"num":0,"time":20},{"num":0,"time":21},{"num":0,"time":22},{"num":0,"time":23},{"num":0,"time":24},{"num":0,"time":25},{"num":0,"time":26},{"num":0,"time":27},{"num":0,"time":28},{"num":0,"time":29},{"num":0,"time":30},{"num":0,"time":31}],"date":"2021-05","dates":["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"],"numbers":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,9,0,0,0,0,0,0,0,0,0,0,0,0],"offlineRate":null,"onlineRate":null}
     * message : SUCCESS
     */

    private int code;
    /**
     * data : [{"num":0,"time":1},{"num":0,"time":2},{"num":0,"time":3},{"num":0,"time":4},{"num":0,"time":5},{"num":0,"time":6},{"num":0,"time":7},{"num":0,"time":8},{"num":0,"time":9},{"num":0,"time":10},{"num":0,"time":11},{"num":0,"time":12},{"num":0,"time":13},{"num":0,"time":14},{"num":0,"time":15},{"num":0,"time":16},{"num":9,"time":17},{"num":0,"time":18},{"num":9,"time":19},{"num":0,"time":20},{"num":0,"time":21},{"num":0,"time":22},{"num":0,"time":23},{"num":0,"time":24},{"num":0,"time":25},{"num":0,"time":26},{"num":0,"time":27},{"num":0,"time":28},{"num":0,"time":29},{"num":0,"time":30},{"num":0,"time":31}]
     * date : 2021-05
     * dates : ["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"]
     * numbers : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,9,0,0,0,0,0,0,0,0,0,0,0,0]
     * offlineRate : null
     * onlineRate : null
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
        private String date;
        private Object offlineRate;
        private Object onlineRate;
        /**
         * num : 0
         * time : 1
         */

        private List<DataBean> data;
        private List<String> dates;
        private List<Integer> numbers;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
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

        public static class Data1Bean {
            private int num;
            private int time;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }
        }
    }
}
