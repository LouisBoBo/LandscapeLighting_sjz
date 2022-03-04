package com.exc.applibrary.main.model;

import java.util.List;

public class TypeRealyModel {

    /**
     * code : 200
     * data : [{"id":4,"name":"点光源回路","parentId":2},{"id":5,"name":"线条灯回路","parentId":2},{"id":6,"name":"洗墙灯回路","parentId":2},{"id":7,"name":"控制器回路","parentId":2},{"id":8,"name":"交换机回路","parentId":2},{"id":9,"name":"投光灯回路","parentId":2},{"id":10,"name":"备用回路","parentId":2},{"id":11,"name":"路由器回路","parentId":2}]
     * message : SUCCESS
     */

    private int code;
    private String message;
    /**
     * id : 4
     * name : 点光源回路
     * parentId : 2
     */

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

    public static class DataBean {
        private int id;
        private String name;
        private int parentId;
        private Boolean isselect;

        public Boolean getIsselect() {
            return isselect;
        }

        public void setIsselect(Boolean isselect) {
            this.isselect = isselect;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }
}
