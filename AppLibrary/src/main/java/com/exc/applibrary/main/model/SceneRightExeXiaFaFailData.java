package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;

public class SceneRightExeXiaFaFailData implements Serializable {
    /**
     * code : 400
     * data : {"defaultNum":2,"nodeNames":["集中控制器","富比伦"],"successNum":2}
     * message : 节点控制失败
     */

    private int code;
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

    public static class DataBean implements Serializable {
        /**
         * defaultNum : 2
         * nodeNames : ["集中控制器","富比伦"]
         * successNum : 2
         */

        private int defaultNum;
        private int successNum;
        private List<String> nodeNames;

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

        public List<String> getNodeNames() {
            return nodeNames;
        }

        public void setNodeNames(List<String> nodeNames) {
            this.nodeNames = nodeNames;
        }
    }
}
