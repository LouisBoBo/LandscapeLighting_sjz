package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SceneSiteList implements Serializable {
    /**
     * code : 200
     * data : [{"addr":null,"buildings":[],"city":null,"description":null,"district":null,"id":26,"name":"1111","nodes":[],"partitionId":1,"province":null,"siteTypeId":null,"status":null},{"addr":null,"buildings":[],"city":null,"description":null,"district":null,"id":27,"name":"测试","nodes":[],"partitionId":1,"province":null,"siteTypeId":null,"status":null},{"addr":null,"buildings":[],"city":null,"description":null,"district":null,"id":29,"name":"111","nodes":[],"partitionId":8,"province":null,"siteTypeId":null,"status":null}]
     * message : SUCCESS
     */

    private int code;
    private String message;
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * addr : null
         * buildings : []
         * city : null
         * description : null
         * district : null
         * id : 26
         * name : 1111
         * nodes : []
         * partitionId : 1
         * province : null
         * siteTypeId : null
         * status : null
         */

        private Object addr;
        private Object city;
        private Object description;
        private Object district;
        private int id;
        private String name;
        private int partitionId;
        private Object province;
        private Object siteTypeId;
        private Object status;
        private List<?> buildings;
        private List<?> nodes;

        public Object getAddr() {
            return addr;
        }

        public void setAddr(Object addr) {
            this.addr = addr;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
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

        public int getPartitionId() {
            return partitionId;
        }

        public void setPartitionId(int partitionId) {
            this.partitionId = partitionId;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getSiteTypeId() {
            return siteTypeId;
        }

        public void setSiteTypeId(Object siteTypeId) {
            this.siteTypeId = siteTypeId;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public List<?> getBuildings() {
            return buildings;
        }

        public void setBuildings(List<?> buildings) {
            this.buildings = buildings;
        }

        public List<?> getNodes() {
            return nodes;
        }

        public void setNodes(List<?> nodes) {
            this.nodes = nodes;
        }
    }
}
