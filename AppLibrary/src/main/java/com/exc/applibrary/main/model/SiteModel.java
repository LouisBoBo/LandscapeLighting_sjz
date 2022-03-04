package com.exc.applibrary.main.model;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SiteModel implements Serializable {


    /**
     * code : 200
     * data : {"addr":"测试","buildings":[{"addr":"测试","buildingTypeSn":3,"city":"太原市","description":"测试","district":"小店区","electricityNodes":[],"id":2,"isKey":0,"latitude":37.87,"longitude":112.53,"masterNodes":[],"name":"测试","nodes":[],"num":"W1006","partition":{"addr":null,"buildingIds":null,"city":null,"description":null,"district":null,"energyCharge":null,"id":1,"name":"灞桥区","province":null,"sn":null},"partitionId":1,"province":"山西省","quantity":null,"sites":[]}],"city":"太原市","description":"测试","district":"小店区","id":27,"name":"测试","nodes":[{"addr":null,"buildingId":null,"city":null,"controller":null,"description":null,"displayHeight":100,"displayWidth":100,"district":null,"height":0,"id":4,"ip":"192.168.112.122","mac":null,"name":"测试","noPartition":null,"num":"10067","ports":null,"province":null,"state":null,"version":null,"width":0,"x":"0","xMax":"1","y":"0","yMax":"1"}],"partitionId":1,"province":"山西省","siteTypeId":3,"status":0}
     * message : SUCCESS
     */

    public int code;
    public DataBean data;
    public String message;

    @Data
    public static class DataBean implements Serializable {
        /**
         * addr : 测试
         * buildings : [{"addr":"测试","buildingTypeSn":3,"city":"太原市","description":"测试","district":"小店区","electricityNodes":[],"id":2,"isKey":0,"latitude":37.87,"longitude":112.53,"masterNodes":[],"name":"测试","nodes":[],"num":"W1006","partition":{"addr":null,"buildingIds":null,"city":null,"description":null,"district":null,"energyCharge":null,"id":1,"name":"灞桥区","province":null,"sn":null},"partitionId":1,"province":"山西省","quantity":null,"sites":[]}]
         * city : 太原市
         * description : 测试
         * district : 小店区
         * id : 27
         * name : 测试
         * nodes : [{"addr":null,"buildingId":null,"city":null,"controller":null,"description":null,"displayHeight":100,"displayWidth":100,"district":null,"height":0,"id":4,"ip":"192.168.112.122","mac":null,"name":"测试","noPartition":null,"num":"10067","ports":null,"province":null,"state":null,"version":null,"width":0,"x":"0","xMax":"1","y":"0","yMax":"1"}]
         * partitionId : 1
         * province : 山西省
         * siteTypeId : 3
         * status : 0
         */

        public String addr;
        public String city;
        public String description;
        public String district;
        public int id;
        public String name;
        public int partitionId;
        public String province;
        public int siteTypeId;
        public int status;
        public List<BuildingsBean> buildings;
        public List<NodesBean> nodes;

        @Data
        public static class BuildingsBean implements Serializable {
            /**
             * addr : 测试
             * buildingTypeSn : 3
             * city : 太原市
             * description : 测试
             * district : 小店区
             * electricityNodes : []
             * id : 2
             * isKey : 0
             * latitude : 37.87
             * longitude : 112.53
             * masterNodes : []
             * name : 测试
             * nodes : []
             * num : W1006
             * partition : {"addr":null,"buildingIds":null,"city":null,"description":null,"district":null,"energyCharge":null,"id":1,"name":"灞桥区","province":null,"sn":null}
             * partitionId : 1
             * province : 山西省
             * quantity : null
             * sites : []
             */

            public String addr;
            public int buildingTypeSn;
            public String city;
            public String description;
            public String district;
            public int id;
            public int isKey;
            public double latitude;
            public double longitude;
            public String name;
            public String num;
            public PartitionBean partition;
            public int partitionId;
            public String province;
            public Object quantity;
            public List<?> electricityNodes;
            public List<?> masterNodes;
            public List<?> nodes;
            public List<?> sites;

            @Data
            public static class PartitionBean implements Serializable {
                /**
                 * addr : null
                 * buildingIds : null
                 * city : null
                 * description : null
                 * district : null
                 * energyCharge : null
                 * id : 1
                 * name : 灞桥区
                 * province : null
                 * sn : null
                 */

                public Object addr;
                public Object buildingIds;
                public Object city;
                public Object description;
                public Object district;
                public Object energyCharge;
                public int id;
                public String name;
                public Object province;
                public Object sn;
            }
        }

        @Data
        public static class NodesBean implements Serializable {
            /**
             * addr : null
             * buildingId : null
             * city : null
             * controller : null
             * description : null
             * displayHeight : 100
             * displayWidth : 100
             * district : null
             * height : 0
             * id : 4
             * ip : 192.168.112.122
             * mac : null
             * name : 测试
             * noPartition : null
             * num : 10067
             * ports : null
             * province : null
             * state : null
             * version : null
             * width : 0
             * x : 0
             * xMax : 1
             * y : 0
             * yMax : 1
             */

            public Object addr;
            public Object buildingId;
            public Object city;
            public Object controller;
            public Object description;
            public int displayHeight;
            public int displayWidth;
            public Object district;
            public int height;
            public int id;
            public String ip;
            public Object mac;
            public String name;
            public Object noPartition;
            public String num;
            public Object ports;
            public Object province;
            public Object state;
            public Object version;
            public int width;
            public String x;
            public String xMax;
            public String y;
            public String yMax;
        }
    }
}
