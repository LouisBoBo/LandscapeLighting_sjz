package com.exc.applibrary.main.model;

import java.util.List;

public class CommandStartModel {

    /**
     * code : 400
     * data : {"successNum":0,"defaultNum":1,"successObjectList":[],"failObjectList":[{"maCommandName":"总控室全区开启","tpDeviceName":"总控室音响50i","message":"下发失败"}]}
     * message : 指令 全部 下发失败
     */

    private int code;
    /**
     * successNum : 0
     * defaultNum : 1
     * successObjectList : []
     * failObjectList : [{"maCommandName":"总控室全区开启","tpDeviceName":"总控室音响50i","message":"下发失败"}]
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
        private int successNum;
        private int defaultNum;
        private List<?> successObjectList;
        /**
         * maCommandName : 总控室全区开启
         * tpDeviceName : 总控室音响50i
         * message : 下发失败
         */

        private List<FailObjectListBean> failObjectList;

        public int getSuccessNum() {
            return successNum;
        }

        public void setSuccessNum(int successNum) {
            this.successNum = successNum;
        }

        public int getDefaultNum() {
            return defaultNum;
        }

        public void setDefaultNum(int defaultNum) {
            this.defaultNum = defaultNum;
        }

        public List<?> getSuccessObjectList() {
            return successObjectList;
        }

        public void setSuccessObjectList(List<?> successObjectList) {
            this.successObjectList = successObjectList;
        }

        public List<FailObjectListBean> getFailObjectList() {
            return failObjectList;
        }

        public void setFailObjectList(List<FailObjectListBean> failObjectList) {
            this.failObjectList = failObjectList;
        }

        public static class FailObjectListBean {
            private String maCommandName;
            private String tpDeviceName;
            private String message;

            public String getMaCommandName() {
                return maCommandName;
            }

            public void setMaCommandName(String maCommandName) {
                this.maCommandName = maCommandName;
            }

            public String getTpDeviceName() {
                return tpDeviceName;
            }

            public void setTpDeviceName(String tpDeviceName) {
                this.tpDeviceName = tpDeviceName;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}
