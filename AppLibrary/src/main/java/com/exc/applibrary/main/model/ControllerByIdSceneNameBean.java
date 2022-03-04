package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;

public class ControllerByIdSceneNameBean implements Serializable {

    /**
     * code : 200
     * data : [{"cycleNum":1,"dsn":17,"name":"全开","nid":93,"nodeName":"集中控制器","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":1},{"cycleNum":1,"dsn":17,"name":"全开","nid":126,"nodeName":"南山海王自研强电节点","partitionId":5,"partitionName":"星区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":92,"nodeName":"强电平台专测","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":96,"nodeName":"富比伦","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":97,"nodeName":"集控","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":106,"nodeName":"测试54","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":108,"nodeName":"测试11","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":109,"nodeName":"说的话就开机1","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":110,"nodeName":"测认识6546","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":111,"nodeName":"SDFSFSF","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":111,"nodeName":"SDFSFSF","partitionId":1,"partitionName":"硚口区","tagId":2,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":114,"nodeName":"手动阀手动阀45","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":117,"nodeName":"萨达","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":118,"nodeName":"士大夫士大夫十分","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":119,"nodeName":"撒地方大师傅","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":121,"nodeName":"101","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":122,"nodeName":"测试节点","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":123,"nodeName":"强电网关111","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0},{"cycleNum":0,"dsn":17,"name":"全开","nid":124,"nodeName":"112.155","partitionId":1,"partitionName":"硚口区","tagId":1,"timingNum":0}]
     * message : SUCCESS
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * cycleNum : 1
         * dsn : 17
         * name : 全开
         * nid : 93
         * nodeName : 集中控制器
         * partitionId : 1
         * partitionName : 硚口区
         * tagId : 1
         * timingNum : 1
         */

        private int cycleNum;
        private int dsn;
        private String name;
        private int nid;
        private String nodeName;
        private int partitionId;
        private String partitionName;
        private int tagId;
        private int timingNum;
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }


        public int getCycleNum() {
            return cycleNum;
        }

        public void setCycleNum(int cycleNum) {
            this.cycleNum = cycleNum;
        }

        public int getDsn() {
            return dsn;
        }

        public void setDsn(int dsn) {
            this.dsn = dsn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNid() {
            return nid;
        }

        public void setNid(int nid) {
            this.nid = nid;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public int getPartitionId() {
            return partitionId;
        }

        public void setPartitionId(int partitionId) {
            this.partitionId = partitionId;
        }

        public String getPartitionName() {
            return partitionName;
        }

        public void setPartitionName(String partitionName) {
            this.partitionName = partitionName;
        }

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public int getTimingNum() {
            return timingNum;
        }

        public void setTimingNum(int timingNum) {
            this.timingNum = timingNum;
        }
    }
}
