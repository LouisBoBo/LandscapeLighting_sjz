package com.exc.applibrary.main.model;

import java.util.List;

public class BuildingDetailModel {


    /**
     * code : 200
     * data : {"nodeList":[{"addr":"(test)和谐路","city":"武汉市","controller":2,"description":"","district":"洪山区","ip":"10.11.252.116","name":"(test)白马鑫居西区10","num":"L9904","ports":"2012","videoList":[{"duration":"00:00:08.00","frameNumber":240,"height":"60","nodeNum":"L9904","status":5,"vidId":"7df8c5e3-e05c-4093-903b-e481a97b4074","vidName":"111科技炫酷视频5656sds1572XGG02.mp4","width":"60"}]}],"building":{"addr":"(test)和谐路","buildingTypeSn":3,"city":"武汉市","description":"(test)白马鑫居西区10","district":"洪山区","electricityNodes":[],"id":4298,"isKey":0,"latitude":30.59965,"longitude":114.417284,"masterNodes":[],"name":"(test)洪山和平三标白马馨居西区10号楼","nodes":[],"num":"XGG02","partitionId":4,"province":"湖北省","quantity":1,"sites":[]}}
     * message : SUCCESS!
     */

    private int code;
    /**
     * nodeList : [{"addr":"(test)和谐路","city":"武汉市","controller":2,"description":"","district":"洪山区","ip":"10.11.252.116","name":"(test)白马鑫居西区10","num":"L9904","ports":"2012","videoList":[{"duration":"00:00:08.00","frameNumber":240,"height":"60","nodeNum":"L9904","status":5,"vidId":"7df8c5e3-e05c-4093-903b-e481a97b4074","vidName":"111科技炫酷视频5656sds1572XGG02.mp4","width":"60"}]}]
     * building : {"addr":"(test)和谐路","buildingTypeSn":3,"city":"武汉市","description":"(test)白马鑫居西区10","district":"洪山区","electricityNodes":[],"id":4298,"isKey":0,"latitude":30.59965,"longitude":114.417284,"masterNodes":[],"name":"(test)洪山和平三标白马馨居西区10号楼","nodes":[],"num":"XGG02","partitionId":4,"province":"湖北省","quantity":1,"sites":[]}
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
         * addr : (test)和谐路
         * buildingTypeSn : 3
         * city : 武汉市
         * description : (test)白马鑫居西区10
         * district : 洪山区
         * electricityNodes : []
         * id : 4298
         * isKey : 0
         * latitude : 30.59965
         * longitude : 114.417284
         * masterNodes : []
         * name : (test)洪山和平三标白马馨居西区10号楼
         * nodes : []
         * num : XGG02
         * partitionId : 4
         * province : 湖北省
         * quantity : 1
         * sites : []
         */

        private BuildingBean building;
        /**
         * addr : (test)和谐路
         * city : 武汉市
         * controller : 2
         * description :
         * district : 洪山区
         * ip : 10.11.252.116
         * name : (test)白马鑫居西区10
         * num : L9904
         * ports : 2012
         * videoList : [{"duration":"00:00:08.00","frameNumber":240,"height":"60","nodeNum":"L9904","status":5,"vidId":"7df8c5e3-e05c-4093-903b-e481a97b4074","vidName":"111科技炫酷视频5656sds1572XGG02.mp4","width":"60"}]
         */

        private List<NodeListBean> nodeList;

        public BuildingBean getBuilding() {
            return building;
        }

        public void setBuilding(BuildingBean building) {
            this.building = building;
        }

        public List<NodeListBean> getNodeList() {
            return nodeList;
        }

        public void setNodeList(List<NodeListBean> nodeList) {
            this.nodeList = nodeList;
        }

        public static class BuildingBean {
            private String addr;
            private int buildingTypeSn;
            private String city;
            private String description;
            private String district;
            private int id;
            private int isKey;
            private double latitude;
            private double longitude;
            private String name;
            private String num;
            private int partitionId;
            private String province;
            private int quantity;
            private List<?> electricityNodes;
            private List<?> masterNodes;
            private List<?> nodes;
            private List<?> sites;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public int getBuildingTypeSn() {
                return buildingTypeSn;
            }

            public void setBuildingTypeSn(int buildingTypeSn) {
                this.buildingTypeSn = buildingTypeSn;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsKey() {
                return isKey;
            }

            public void setIsKey(int isKey) {
                this.isKey = isKey;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public int getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public List<?> getElectricityNodes() {
                return electricityNodes;
            }

            public void setElectricityNodes(List<?> electricityNodes) {
                this.electricityNodes = electricityNodes;
            }

            public List<?> getMasterNodes() {
                return masterNodes;
            }

            public void setMasterNodes(List<?> masterNodes) {
                this.masterNodes = masterNodes;
            }

            public List<?> getNodes() {
                return nodes;
            }

            public void setNodes(List<?> nodes) {
                this.nodes = nodes;
            }

            public List<?> getSites() {
                return sites;
            }

            public void setSites(List<?> sites) {
                this.sites = sites;
            }
        }

        public static class NodeListBean {
            private String addr;
            private String city;
            private int controller;
            private String description;
            private String district;
            private String ip;
            private String name;
            private String num;
            private String ports;
            /**
             * duration : 00:00:08.00
             * frameNumber : 240
             * height : 60
             * nodeNum : L9904
             * status : 5
             * vidId : 7df8c5e3-e05c-4093-903b-e481a97b4074
             * vidName : 111科技炫酷视频5656sds1572XGG02.mp4
             * width : 60
             */

            private List<VideoListBean> videoList;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getController() {
                return controller;
            }

            public void setController(int controller) {
                this.controller = controller;
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

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPorts() {
                return ports;
            }

            public void setPorts(String ports) {
                this.ports = ports;
            }

            public List<VideoListBean> getVideoList() {
                return videoList;
            }

            public void setVideoList(List<VideoListBean> videoList) {
                this.videoList = videoList;
            }

            public static class VideoListBean {
                private String duration;
                private int frameNumber;
                private String height;
                private String nodeNum;
                private int status;
                private String vidId;
                private String vidName;
                private String width;

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public int getFrameNumber() {
                    return frameNumber;
                }

                public void setFrameNumber(int frameNumber) {
                    this.frameNumber = frameNumber;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getNodeNum() {
                    return nodeNum;
                }

                public void setNodeNum(String nodeNum) {
                    this.nodeNum = nodeNum;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getVidId() {
                    return vidId;
                }

                public void setVidId(String vidId) {
                    this.vidId = vidId;
                }

                public String getVidName() {
                    return vidName;
                }

                public void setVidName(String vidName) {
                    this.vidName = vidName;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }
            }
        }
    }
}
