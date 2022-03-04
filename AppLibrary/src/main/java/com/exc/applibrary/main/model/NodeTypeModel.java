package com.exc.applibrary.main.model;

import java.util.List;

public class NodeTypeModel {

    /**
     * code : 200
     * data : {"endRow":2,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"addr":"南山","buildingId":null,"buildingName":null,"deviceManufacturer":1,"id":68,"ip":"192.168.1.5","isOffline":1,"isOpen":1,"mac":"00-70-70-95","name":"南山合广测试","networkType":1,"num":"HGCS1","offlineTime":"2021-10-22 10:39:57","partitionName":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":"南山","buildingId":null,"buildingName":null,"deviceManufacturer":2,"id":69,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"mac":"00-14-97-38-27-80","name":"南山自研","networkType":1,"num":"W1006","offlineTime":"2021-06-23 15:41:05","partitionName":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":null,"pageNum":1,"pageSize":5999,"pages":1,"prePage":0,"size":2,"startRow":1,"total":2}
     * message : SUCCESS
     * time : null
     */

    private int code;
    /**
     * endRow : 2
     * firstPage : 1
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * lastPage : 1
     * list : [{"addr":"南山","buildingId":null,"buildingName":null,"deviceManufacturer":1,"id":68,"ip":"192.168.1.5","isOffline":1,"isOpen":1,"mac":"00-70-70-95","name":"南山合广测试","networkType":1,"num":"HGCS1","offlineTime":"2021-10-22 10:39:57","partitionName":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":"南山","buildingId":null,"buildingName":null,"deviceManufacturer":2,"id":69,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"mac":"00-14-97-38-27-80","name":"南山自研","networkType":1,"num":"W1006","offlineTime":"2021-06-23 15:41:05","partitionName":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"}]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * orderBy : null
     * pageNum : 1
     * pageSize : 5999
     * pages : 1
     * prePage : 0
     * size : 2
     * startRow : 1
     * total : 2
     */

    private DataBean data;
    private String message;
    private Object time;

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

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public static class DataBean {
        private int endRow;
        private int firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int lastPage;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int navigatePages;
        private int nextPage;
        private Object orderBy;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        /**
         * addr : 南山
         * buildingId : null
         * buildingName : null
         * deviceManufacturer : 1
         * id : 68
         * ip : 192.168.1.5
         * isOffline : 1
         * isOpen : 1
         * mac : 00-70-70-95
         * name : 南山合广测试
         * networkType : 1
         * num : HGCS1
         * offlineTime : 2021-10-22 10:39:57
         * partitionName : null
         * siteName : null
         * startTime : null
         * totalEnergy : 0.0
         * version : 7.3
         */

        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
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

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            private String addr;
            private Object buildingId;
            private Object buildingName;
            private int deviceManufacturer;
            private int id;
            private String ip;
            private int isOffline;
            private int isOpen;
            private String mac;
            private String name;
            private int networkType;
            private String num;
            private String offlineTime;
            private Object partitionName;
            private Object siteName;
            private Object startTime;
            private double totalEnergy;
            private String version;
            private boolean isselesct;

            public boolean isIsselesct() {
                return isselesct;
            }

            public void setIsselesct(boolean isselesct) {
                this.isselesct = isselesct;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public Object getBuildingId() {
                return buildingId;
            }

            public void setBuildingId(Object buildingId) {
                this.buildingId = buildingId;
            }

            public Object getBuildingName() {
                return buildingName;
            }

            public void setBuildingName(Object buildingName) {
                this.buildingName = buildingName;
            }

            public int getDeviceManufacturer() {
                return deviceManufacturer;
            }

            public void setDeviceManufacturer(int deviceManufacturer) {
                this.deviceManufacturer = deviceManufacturer;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public int getIsOffline() {
                return isOffline;
            }

            public void setIsOffline(int isOffline) {
                this.isOffline = isOffline;
            }

            public int getIsOpen() {
                return isOpen;
            }

            public void setIsOpen(int isOpen) {
                this.isOpen = isOpen;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNetworkType() {
                return networkType;
            }

            public void setNetworkType(int networkType) {
                this.networkType = networkType;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getOfflineTime() {
                return offlineTime;
            }

            public void setOfflineTime(String offlineTime) {
                this.offlineTime = offlineTime;
            }

            public Object getPartitionName() {
                return partitionName;
            }

            public void setPartitionName(Object partitionName) {
                this.partitionName = partitionName;
            }

            public Object getSiteName() {
                return siteName;
            }

            public void setSiteName(Object siteName) {
                this.siteName = siteName;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public double getTotalEnergy() {
                return totalEnergy;
            }

            public void setTotalEnergy(double totalEnergy) {
                this.totalEnergy = totalEnergy;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }
    }
}
