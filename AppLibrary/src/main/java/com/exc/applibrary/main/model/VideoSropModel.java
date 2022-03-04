package com.exc.applibrary.main.model;

import java.util.List;

public class VideoSropModel {

    /**
     * code : 1
     * data : {"defaultNum":2,"nums":["asd12","DDZXL"],"successNum":0}
     * returnMsg : {"errCode":"0x00000009","errDesc":"部分节点无法连接"}
     */

    private int code;
    /**
     * defaultNum : 2
     * nums : ["asd12","DDZXL"]
     * successNum : 0
     */

    private DataBean data;
    /**
     * errCode : 0x00000009
     * errDesc : 部分节点无法连接
     */

    private ReturnMsgBean returnMsg;

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

    public ReturnMsgBean getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(ReturnMsgBean returnMsg) {
        this.returnMsg = returnMsg;
    }

    public static class DataBean {
        private int defaultNum;
        private int successNum;
        private List<String> nums;

        public int getDefaultNum() {
            return defaultNum;
        }

        public void setDefaultNum(int defaultNum) {
            this.defaultNum = defaultNum;
        }

        public int getSuccessNum() {
            return successNum;
        }

        public void setSuccessNum(int successNum) {
            this.successNum = successNum;
        }

        public List<String> getNums() {
            return nums;
        }

        public void setNums(List<String> nums) {
            this.nums = nums;
        }
    }

    public static class ReturnMsgBean {
        private String errCode;
        private String errDesc;

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getErrDesc() {
            return errDesc;
        }

        public void setErrDesc(String errDesc) {
            this.errDesc = errDesc;
        }
    }
}
