package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartitionList implements Serializable {
    /**
     * code : 200
     * data : [{"addr":"灞桥区","buildingIds":null,"city":null,"description":null,"district":null,"energyCharge":null,"id":1,"name":"灞桥区","province":null,"sn":"1"}]
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
         * addr : 灞桥区
         * buildingIds : null
         * city : null
         * description : null
         * district : null
         * energyCharge : null
         * id : 1
         * name : 灞桥区
         * province : null
         * sn : 1
         */

        private String addr;
        private Object buildingIds;
        private Object city;
        private Object description;
        private Object district;
        private Object energyCharge;
        private int id;
        private String name;
        private Object province;
        private String sn;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public Object getBuildingIds() {
            return buildingIds;
        }

        public void setBuildingIds(Object buildingIds) {
            this.buildingIds = buildingIds;
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

        public Object getEnergyCharge() {
            return energyCharge;
        }

        public void setEnergyCharge(Object energyCharge) {
            this.energyCharge = energyCharge;
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

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }
    }
}
