package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;

public class BuildingModel implements Serializable {

    /**
     * code : 200
     * data : {"list":[{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":1,"latitude":30.542599738896495,"num":"00100","isKey":0,"description":"泰邦大厦","siteName":null,"nodeList":null,"buildingTypeSn":5,"partitionName":"硚口区","province":"广东省","district":"南山区","electricityNodeList":null,"name":"泰邦大厦建筑物","isOffline":1,"id":1,"state":1,"addr":"泰邦大厦","longitude":114.3075942993164,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":5,"latitude":30,"num":"123","isKey":1,"description":"海王银河科技大厦","siteName":"星区","nodeList":null,"buildingTypeSn":3,"partitionName":"星区","province":"广东省","district":"南山区","electricityNodeList":null,"name":"测试海王建筑物","isOffline":1,"id":12,"state":2,"addr":"海王银河科技大厦","longitude":114,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":1,"latitude":12,"num":"00111","isKey":2,"description":"的双方各","siteName":"星区,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"广东省","district":"宝安区","electricityNodeList":null,"name":"星建筑1","isOffline":1,"id":6,"state":2,"addr":"电饭锅电饭锅","longitude":13,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"天津城区","partitionId":1,"latitude":30.546074002033322,"num":"lllll","isKey":1,"description":"2134","siteName":"泰邦大厦,大区站点,测试站点L","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"天津市","district":"河东区","electricityNodeList":null,"name":"测试建筑L","isOffline":1,"id":9,"state":2,"addr":"1234","longitude":114.30437564849855,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":1,"latitude":1,"num":"66666","isKey":0,"description":"1108","siteName":"泰邦大厦,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"广东省","district":"南山区","electricityNodeList":null,"name":"1108","isOffline":1,"id":3,"state":3,"addr":"1108","longitude":1,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"乌海市","partitionId":1,"latitude":1,"num":"12333","isKey":0,"description":"1","siteName":"南山区,大区站点,测试","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"内蒙古自治区","district":"海勃湾区","electricityNodeList":null,"name":"测试节点","isOffline":1,"id":5,"state":3,"addr":"测试","longitude":1,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"唐山市","partitionId":1,"latitude":30.549548140852163,"num":"weqr1","isKey":1,"description":"r五七二","siteName":"星区,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"河北省","district":"古冶区","electricityNodeList":null,"name":"we'q'r","isOffline":1,"id":8,"state":3,"addr":"而我却r","longitude":114.29124355316164,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"唐山市","partitionId":1,"latitude":30.546850150213572,"num":"uuuuu","isKey":1,"description":"qwe","siteName":"泰邦大厦,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"河北省","district":"路北区","electricityNodeList":null,"name":"测试建筑u","isOffline":1,"id":10,"state":3,"addr":"qwe","longitude":114.30137157440187,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"吕梁市","partitionId":1,"latitude":30.54346215774267,"num":"SX912","isKey":2,"description":"山西吕梁现场演示","siteName":"硚口区,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"山西省","district":"离石区","electricityNodeList":null,"name":"山西吕梁现场演示","isOffline":1,"id":11,"state":3,"addr":"山西吕梁现场演示","longitude":114.29772377014162,"masterNodeList":null}],"totalNum":9,"onlineNum":0}
     * message : SUCCESS
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
         * list : [{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":1,"latitude":30.542599738896495,"num":"00100","isKey":0,"description":"泰邦大厦","siteName":null,"nodeList":null,"buildingTypeSn":5,"partitionName":"硚口区","province":"广东省","district":"南山区","electricityNodeList":null,"name":"泰邦大厦建筑物","isOffline":1,"id":1,"state":1,"addr":"泰邦大厦","longitude":114.3075942993164,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":5,"latitude":30,"num":"123","isKey":1,"description":"海王银河科技大厦","siteName":"星区","nodeList":null,"buildingTypeSn":3,"partitionName":"星区","province":"广东省","district":"南山区","electricityNodeList":null,"name":"测试海王建筑物","isOffline":1,"id":12,"state":2,"addr":"海王银河科技大厦","longitude":114,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":1,"latitude":12,"num":"00111","isKey":2,"description":"的双方各","siteName":"星区,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"广东省","district":"宝安区","electricityNodeList":null,"name":"星建筑1","isOffline":1,"id":6,"state":2,"addr":"电饭锅电饭锅","longitude":13,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"天津城区","partitionId":1,"latitude":30.546074002033322,"num":"lllll","isKey":1,"description":"2134","siteName":"泰邦大厦,大区站点,测试站点L","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"天津市","district":"河东区","electricityNodeList":null,"name":"测试建筑L","isOffline":1,"id":9,"state":2,"addr":"1234","longitude":114.30437564849855,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"深圳市","partitionId":1,"latitude":1,"num":"66666","isKey":0,"description":"1108","siteName":"泰邦大厦,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"广东省","district":"南山区","electricityNodeList":null,"name":"1108","isOffline":1,"id":3,"state":3,"addr":"1108","longitude":1,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"乌海市","partitionId":1,"latitude":1,"num":"12333","isKey":0,"description":"1","siteName":"南山区,大区站点,测试","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"内蒙古自治区","district":"海勃湾区","electricityNodeList":null,"name":"测试节点","isOffline":1,"id":5,"state":3,"addr":"测试","longitude":1,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"唐山市","partitionId":1,"latitude":30.549548140852163,"num":"weqr1","isKey":1,"description":"r五七二","siteName":"星区,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"河北省","district":"古冶区","electricityNodeList":null,"name":"we'q'r","isOffline":1,"id":8,"state":3,"addr":"而我却r","longitude":114.29124355316164,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"唐山市","partitionId":1,"latitude":30.546850150213572,"num":"uuuuu","isKey":1,"description":"qwe","siteName":"泰邦大厦,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"河北省","district":"路北区","electricityNodeList":null,"name":"测试建筑u","isOffline":1,"id":10,"state":3,"addr":"qwe","longitude":114.30137157440187,"masterNodeList":null},{"nodeNum":null,"quantity":1,"city":"吕梁市","partitionId":1,"latitude":30.54346215774267,"num":"SX912","isKey":2,"description":"山西吕梁现场演示","siteName":"硚口区,大区站点","nodeList":null,"buildingTypeSn":3,"partitionName":"硚口区","province":"山西省","district":"离石区","electricityNodeList":null,"name":"山西吕梁现场演示","isOffline":1,"id":11,"state":3,"addr":"山西吕梁现场演示","longitude":114.29772377014162,"masterNodeList":null}]
         * totalNum : 9
         * onlineNum : 0
         */

        private int totalNum;
        private int onlineNum;
        private List<ListBean> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getOnlineNum() {
            return onlineNum;
        }

        public void setOnlineNum(int onlineNum) {
            this.onlineNum = onlineNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * nodeNum : null
             * quantity : 1
             * city : 深圳市
             * partitionId : 1
             * latitude : 30.542599738896495
             * num : 00100
             * isKey : 0
             * description : 泰邦大厦
             * siteName : null
             * nodeList : null
             * buildingTypeSn : 5
             * partitionName : 硚口区
             * province : 广东省
             * district : 南山区
             * electricityNodeList : null
             * name : 泰邦大厦建筑物
             * isOffline : 1
             * id : 1
             * state : 1
             * addr : 泰邦大厦
             * longitude : 114.3075942993164
             * masterNodeList : null
             */

            private Object nodeNum;
            private int quantity;
            private String city;
            private int partitionId;
            private double latitude;
            private String num;
            private int isKey;
            private String description;
            private Object siteName;
            private Object nodeList;
            private int buildingTypeSn;
            private String partitionName;
            private String province;
            private String district;
            private Object electricityNodeList;
            private String name;
            private int isOffline;
            private int id;
            private int state;
            private String addr;
            private double longitude;
            private Object masterNodeList;

            public Object getNodeNum() {
                return nodeNum;
            }

            public void setNodeNum(Object nodeNum) {
                this.nodeNum = nodeNum;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public int getIsKey() {
                return isKey;
            }

            public void setIsKey(int isKey) {
                this.isKey = isKey;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getSiteName() {
                return siteName;
            }

            public void setSiteName(Object siteName) {
                this.siteName = siteName;
            }

            public Object getNodeList() {
                return nodeList;
            }

            public void setNodeList(Object nodeList) {
                this.nodeList = nodeList;
            }

            public int getBuildingTypeSn() {
                return buildingTypeSn;
            }

            public void setBuildingTypeSn(int buildingTypeSn) {
                this.buildingTypeSn = buildingTypeSn;
            }

            public String getPartitionName() {
                return partitionName;
            }

            public void setPartitionName(String partitionName) {
                this.partitionName = partitionName;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public Object getElectricityNodeList() {
                return electricityNodeList;
            }

            public void setElectricityNodeList(Object electricityNodeList) {
                this.electricityNodeList = electricityNodeList;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIsOffline() {
                return isOffline;
            }

            public void setIsOffline(int isOffline) {
                this.isOffline = isOffline;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public Object getMasterNodeList() {
                return masterNodeList;
            }

            public void setMasterNodeList(Object masterNodeList) {
                this.masterNodeList = masterNodeList;
            }
        }
    }
}
