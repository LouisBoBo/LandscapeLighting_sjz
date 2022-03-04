package com.exc.applibrary.main.model;

import java.util.List;

public class SceneDataListById {
    private int code;
    private String message;
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {

        private int isCheck;
        private String nodeName;


        public int getIsCheck() {
            return isCheck;
        }

        public void setIsCheck(int isCheck) {
            this.isCheck = isCheck;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }


        private int cycleNum;
        private int dsn;
        private int id;
        private int isEdit;
        private String name;
        private int tagId;
        private int timingNum;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsEdit() {
            return isEdit;
        }

        public void setIsEdit(int isEdit) {
            this.isEdit = isEdit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
