package com.exc.applibrary.main.model;

public class PartitionDetailModel {

    /**
     * code : 200
     * data : {"partitionParameter":{"id":29,"isElectricityPermission":1,"isElectricityRunning":0,"isLightPermission":1,"isLightRunning":0,"isOwn":0,"isThirdParty":0,"partitionSn":"10"},"partition":{"addr":"湖北省武汉市东西湖区","buildingCount":88,"city":"武汉市","description":"东西湖区","district":"东西湖区","energyCharge":1.5,"id":10,"name":"东西湖区","offlineNum":19,"onlineNum":5,"pageNum":1,"pageSize":10,"province":"湖北省","sn":"13"}}
     * message : SUCCESS!
     */

    private int code;
    /**
     * partitionParameter : {"id":29,"isElectricityPermission":1,"isElectricityRunning":0,"isLightPermission":1,"isLightRunning":0,"isOwn":0,"isThirdParty":0,"partitionSn":"10"}
     * partition : {"addr":"湖北省武汉市东西湖区","buildingCount":88,"city":"武汉市","description":"东西湖区","district":"东西湖区","energyCharge":1.5,"id":10,"name":"东西湖区","offlineNum":19,"onlineNum":5,"pageNum":1,"pageSize":10,"province":"湖北省","sn":"13"}
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
        /**
         * id : 29
         * isElectricityPermission : 1
         * isElectricityRunning : 0
         * isLightPermission : 1
         * isLightRunning : 0
         * isOwn : 0
         * isThirdParty : 0
         * partitionSn : 10
         */

        private PartitionParameterBean partitionParameter;
        /**
         * addr : 湖北省武汉市东西湖区
         * buildingCount : 88
         * city : 武汉市
         * description : 东西湖区
         * district : 东西湖区
         * energyCharge : 1.5
         * id : 10
         * name : 东西湖区
         * offlineNum : 19
         * onlineNum : 5
         * pageNum : 1
         * pageSize : 10
         * province : 湖北省
         * sn : 13
         */

        private PartitionBean partition;

        public PartitionParameterBean getPartitionParameter() {
            return partitionParameter;
        }

        public void setPartitionParameter(PartitionParameterBean partitionParameter) {
            this.partitionParameter = partitionParameter;
        }

        public PartitionBean getPartition() {
            return partition;
        }

        public void setPartition(PartitionBean partition) {
            this.partition = partition;
        }

        public static class PartitionParameterBean {
            private int id;
            private int isElectricityPermission;
            private int isElectricityRunning;
            private int isLightPermission;
            private int isLightRunning;
            private int isOwn;
            private int isThirdParty;
            private String partitionSn;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsElectricityPermission() {
                return isElectricityPermission;
            }

            public void setIsElectricityPermission(int isElectricityPermission) {
                this.isElectricityPermission = isElectricityPermission;
            }

            public int getIsElectricityRunning() {
                return isElectricityRunning;
            }

            public void setIsElectricityRunning(int isElectricityRunning) {
                this.isElectricityRunning = isElectricityRunning;
            }

            public int getIsLightPermission() {
                return isLightPermission;
            }

            public void setIsLightPermission(int isLightPermission) {
                this.isLightPermission = isLightPermission;
            }

            public int getIsLightRunning() {
                return isLightRunning;
            }

            public void setIsLightRunning(int isLightRunning) {
                this.isLightRunning = isLightRunning;
            }

            public int getIsOwn() {
                return isOwn;
            }

            public void setIsOwn(int isOwn) {
                this.isOwn = isOwn;
            }

            public int getIsThirdParty() {
                return isThirdParty;
            }

            public void setIsThirdParty(int isThirdParty) {
                this.isThirdParty = isThirdParty;
            }

            public String getPartitionSn() {
                return partitionSn;
            }

            public void setPartitionSn(String partitionSn) {
                this.partitionSn = partitionSn;
            }
        }

        public static class PartitionBean {
            private String addr;
            private int buildingCount;
            private String city;
            private String description;
            private String district;
            private double energyCharge;
            private int id;
            private String name;
            private int offlineNum;
            private int onlineNum;
            private int pageNum;
            private int pageSize;
            private String province;
            private String sn;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public int getBuildingCount() {
                return buildingCount;
            }

            public void setBuildingCount(int buildingCount) {
                this.buildingCount = buildingCount;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public double getEnergyCharge() {
                return energyCharge;
            }

            public void setEnergyCharge(double energyCharge) {
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

            public int getOfflineNum() {
                return offlineNum;
            }

            public void setOfflineNum(int offlineNum) {
                this.offlineNum = offlineNum;
            }

            public int getOnlineNum() {
                return onlineNum;
            }

            public void setOnlineNum(int onlineNum) {
                this.onlineNum = onlineNum;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
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
}
