package com.exc.applibrary.main.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

public class ElectricityNodeModel {

    /**
     * code : 200
     * data : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":8,"list":[{"addr":null,"buildingId":1181,"buildingName":"汉阳5点5医药产业大厦","id":636,"ip":"10.10.48.191","isOffline":0,"isOpen":1,"name":"5点5医药产业大厦","networkType":1,"num":"5D5YY","offlineTime":"2020-05-08 14:09:14","partitionName":"汉阳区","siteName":"汉阳5点5医药产业大厦,F区块站点,路线站点-6号保障线路,路线站点-重点保障建筑,汉阳区重点保障线路站点,6号保障线路","startTime":"2019-04-03 14:32:29","totalEnergy":10650.5,"version":"7.8"},{"addr":null,"buildingId":1701,"buildingName":"武昌东海湾719研究所22","id":1148,"ip":"10.11.251.206","isOffline":0,"isOpen":1,"name":"719研究所22号楼","networkType":2,"num":"71922","offlineTime":"2020-05-08 14:12:06","partitionName":"武昌区","siteName":"武昌东海湾站点,武昌719研究所,路线站点-6号保障线路,路线站点-重点保障建筑,武昌区重点保障建筑,6号保障线路,巡线20191019,武汉市动态楼宇","startTime":"2019-04-20 14:56:15","totalEnergy":8199.7998046875,"version":"7.8"},{"addr":null,"buildingId":1702,"buildingName":"武昌东海湾719研究所23","id":1141,"ip":"10.10.64.176","isOffline":0,"isOpen":1,"name":"719研究所23号楼","networkType":1,"num":"71923","offlineTime":"2020-05-08 14:12:07","partitionName":"武昌区","siteName":"武昌东海湾站点,武昌719研究所,路线站点-6号保障线路,路线站点-重点保障建筑,武昌区重点保障建筑,6号保障线路,巡线20191019,武汉市动态楼宇","startTime":"2019-04-20 14:24:10","totalEnergy":7944.33984375,"version":"7.8"},{"addr":null,"buildingId":1832,"buildingName":"硚口7天连锁酒店1","id":821,"ip":"10.10.32.77","isOffline":0,"isOpen":1,"name":"7天连锁酒店","networkType":1,"num":"QTJD1","offlineTime":"2020-05-08 14:09:14","partitionName":"硚口区","siteName":"硚口7天连锁酒店,硚口区EPC站点,硚口区重点保障建筑,12号保障线路,武汉市联机建筑","startTime":"2019-04-08 16:08:39","totalEnergy":4123.2099609375,"version":"7.8"},{"addr":null,"buildingId":107,"buildingName":"沌口三标P+R停车场","id":86,"ip":"10.10.96.156","isOffline":0,"isOpen":1,"name":"P+R停车场","networkType":1,"num":"W0035","offlineTime":"2020-05-08 14:12:08","partitionName":"经开区","siteName":"沌口P+R停车场,路线站点-6号保障线路,路线站点-重点保障建筑,沌口区重点保障线路站点,6号保障线路","startTime":"2019-02-24 12:23:54","totalEnergy":6521.14990234375,"version":"7.8"},{"addr":null,"buildingId":456,"buildingName":"洪山V7星公馆1","id":2507,"ip":"10.10.128.211","isOffline":0,"isOpen":1,"name":"V7星公馆1号楼","networkType":1,"num":"VGG01","offlineTime":"2020-05-08 14:09:14","partitionName":"洪山区","siteName":"洪山V7星公馆站点,C区块站点,1号保障线路,洪山区重点保障线路站点,徐东大街至东湖宾馆楼宇,市级接待线路,武汉市联机建筑,Z1天河机场\u2014长江二桥\u2014东湖徐东大街","startTime":"2019-07-02 18:08:02","totalEnergy":0,"version":"7.8"},{"addr":null,"buildingId":457,"buildingName":"洪山V7星公馆2","id":2499,"ip":"10.10.128.210","isOffline":0,"isOpen":1,"name":"V7星公馆2号楼","networkType":1,"num":"VGG02","offlineTime":"2020-05-08 14:09:14","partitionName":"洪山区","siteName":"洪山V7星公馆站点,C区块站点,1号保障线路,洪山区重点保障线路站点,徐东大街至东湖宾馆楼宇,市级接待线路,武汉市联机建筑,Z1天河机场\u2014长江二桥\u2014东湖徐东大街","startTime":"2019-07-02 16:47:55","totalEnergy":0,"version":"7.8"},{"addr":null,"buildingId":1160,"buildingName":"汉阳WM我们的垂直社交圈","id":503,"ip":"10.11.253.140","isOffline":0,"isOpen":1,"name":"WM我们的垂直社交圈","networkType":2,"num":"CZS01","offlineTime":"2020-05-08 14:12:08","partitionName":"汉阳区","siteName":"汉阳垂直社交圈,F区块站点,路线站点-6号保障线路,路线站点-重点保障建筑,汉阳区重点保障线路站点,6号保障线路","startTime":"2019-03-31 15:27:55","totalEnergy":12157.5,"version":"7.8"},{"addr":null,"buildingId":594,"buildingName":"洪山珞南四标洪山一元堂中医门诊部","id":1373,"ip":"10.11.250.24","isOffline":0,"isOpen":1,"name":"一元堂","networkType":2,"num":"YYT01","offlineTime":"2020-05-08 14:12:04","partitionName":"洪山区","siteName":"洪山一元堂中医门诊部,路线站点-5号保障线路,路线站点-重点保障建筑,5号保障线路,洪山区重点保障线路站点","startTime":"2019-04-27 16:32:02","totalEnergy":12569.099609375,"version":"7.8"},{"addr":null,"buildingId":17,"buildingName":"沌口四标万科翡翠玖玺8001","id":104,"ip":"10.10.96.144","isOffline":0,"isOpen":1,"name":"万科翡翠玖玺8001","networkType":1,"num":"W0044","offlineTime":"2020-05-08 14:12:08","partitionName":"经开区","siteName":"沌口万科翡翠玖玺站点,G区块站点,沌口区重点保障线路站点,6号保障线路,体育中心周边灯光控制楼宇,武汉市联机建筑","startTime":"2019-02-24 18:36:03","totalEnergy":10169.7001953125,"version":"7.8"}],"navigateFirstPage":1,"navigateLastPage":8,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"nextPage":2,"orderBy":null,"pageNum":1,"pageSize":10,"pages":274,"prePage":0,"size":10,"startRow":1,"total":2739}
     * message : SUCCESS
     */

    private int code;
    /**
     * endRow : 10
     * firstPage : 1
     * hasNextPage : true
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : false
     * lastPage : 8
     * list : [{"addr":null,"buildingId":1181,"buildingName":"汉阳5点5医药产业大厦","id":636,"ip":"10.10.48.191","isOffline":0,"isOpen":1,"name":"5点5医药产业大厦","networkType":1,"num":"5D5YY","offlineTime":"2020-05-08 14:09:14","partitionName":"汉阳区","siteName":"汉阳5点5医药产业大厦,F区块站点,路线站点-6号保障线路,路线站点-重点保障建筑,汉阳区重点保障线路站点,6号保障线路","startTime":"2019-04-03 14:32:29","totalEnergy":10650.5,"version":"7.8"},{"addr":null,"buildingId":1701,"buildingName":"武昌东海湾719研究所22","id":1148,"ip":"10.11.251.206","isOffline":0,"isOpen":1,"name":"719研究所22号楼","networkType":2,"num":"71922","offlineTime":"2020-05-08 14:12:06","partitionName":"武昌区","siteName":"武昌东海湾站点,武昌719研究所,路线站点-6号保障线路,路线站点-重点保障建筑,武昌区重点保障建筑,6号保障线路,巡线20191019,武汉市动态楼宇","startTime":"2019-04-20 14:56:15","totalEnergy":8199.7998046875,"version":"7.8"},{"addr":null,"buildingId":1702,"buildingName":"武昌东海湾719研究所23","id":1141,"ip":"10.10.64.176","isOffline":0,"isOpen":1,"name":"719研究所23号楼","networkType":1,"num":"71923","offlineTime":"2020-05-08 14:12:07","partitionName":"武昌区","siteName":"武昌东海湾站点,武昌719研究所,路线站点-6号保障线路,路线站点-重点保障建筑,武昌区重点保障建筑,6号保障线路,巡线20191019,武汉市动态楼宇","startTime":"2019-04-20 14:24:10","totalEnergy":7944.33984375,"version":"7.8"},{"addr":null,"buildingId":1832,"buildingName":"硚口7天连锁酒店1","id":821,"ip":"10.10.32.77","isOffline":0,"isOpen":1,"name":"7天连锁酒店","networkType":1,"num":"QTJD1","offlineTime":"2020-05-08 14:09:14","partitionName":"硚口区","siteName":"硚口7天连锁酒店,硚口区EPC站点,硚口区重点保障建筑,12号保障线路,武汉市联机建筑","startTime":"2019-04-08 16:08:39","totalEnergy":4123.2099609375,"version":"7.8"},{"addr":null,"buildingId":107,"buildingName":"沌口三标P+R停车场","id":86,"ip":"10.10.96.156","isOffline":0,"isOpen":1,"name":"P+R停车场","networkType":1,"num":"W0035","offlineTime":"2020-05-08 14:12:08","partitionName":"经开区","siteName":"沌口P+R停车场,路线站点-6号保障线路,路线站点-重点保障建筑,沌口区重点保障线路站点,6号保障线路","startTime":"2019-02-24 12:23:54","totalEnergy":6521.14990234375,"version":"7.8"},{"addr":null,"buildingId":456,"buildingName":"洪山V7星公馆1","id":2507,"ip":"10.10.128.211","isOffline":0,"isOpen":1,"name":"V7星公馆1号楼","networkType":1,"num":"VGG01","offlineTime":"2020-05-08 14:09:14","partitionName":"洪山区","siteName":"洪山V7星公馆站点,C区块站点,1号保障线路,洪山区重点保障线路站点,徐东大街至东湖宾馆楼宇,市级接待线路,武汉市联机建筑,Z1天河机场\u2014长江二桥\u2014东湖徐东大街","startTime":"2019-07-02 18:08:02","totalEnergy":0,"version":"7.8"},{"addr":null,"buildingId":457,"buildingName":"洪山V7星公馆2","id":2499,"ip":"10.10.128.210","isOffline":0,"isOpen":1,"name":"V7星公馆2号楼","networkType":1,"num":"VGG02","offlineTime":"2020-05-08 14:09:14","partitionName":"洪山区","siteName":"洪山V7星公馆站点,C区块站点,1号保障线路,洪山区重点保障线路站点,徐东大街至东湖宾馆楼宇,市级接待线路,武汉市联机建筑,Z1天河机场\u2014长江二桥\u2014东湖徐东大街","startTime":"2019-07-02 16:47:55","totalEnergy":0,"version":"7.8"},{"addr":null,"buildingId":1160,"buildingName":"汉阳WM我们的垂直社交圈","id":503,"ip":"10.11.253.140","isOffline":0,"isOpen":1,"name":"WM我们的垂直社交圈","networkType":2,"num":"CZS01","offlineTime":"2020-05-08 14:12:08","partitionName":"汉阳区","siteName":"汉阳垂直社交圈,F区块站点,路线站点-6号保障线路,路线站点-重点保障建筑,汉阳区重点保障线路站点,6号保障线路","startTime":"2019-03-31 15:27:55","totalEnergy":12157.5,"version":"7.8"},{"addr":null,"buildingId":594,"buildingName":"洪山珞南四标洪山一元堂中医门诊部","id":1373,"ip":"10.11.250.24","isOffline":0,"isOpen":1,"name":"一元堂","networkType":2,"num":"YYT01","offlineTime":"2020-05-08 14:12:04","partitionName":"洪山区","siteName":"洪山一元堂中医门诊部,路线站点-5号保障线路,路线站点-重点保障建筑,5号保障线路,洪山区重点保障线路站点","startTime":"2019-04-27 16:32:02","totalEnergy":12569.099609375,"version":"7.8"},{"addr":null,"buildingId":17,"buildingName":"沌口四标万科翡翠玖玺8001","id":104,"ip":"10.10.96.144","isOffline":0,"isOpen":1,"name":"万科翡翠玖玺8001","networkType":1,"num":"W0044","offlineTime":"2020-05-08 14:12:08","partitionName":"经开区","siteName":"沌口万科翡翠玖玺站点,G区块站点,沌口区重点保障线路站点,6号保障线路,体育中心周边灯光控制楼宇,武汉市联机建筑","startTime":"2019-02-24 18:36:03","totalEnergy":10169.7001953125,"version":"7.8"}]
     * navigateFirstPage : 1
     * navigateLastPage : 8
     * navigatePages : 8
     * navigatepageNums : [1,2,3,4,5,6,7,8]
     * nextPage : 2
     * orderBy : null
     * pageNum : 1
     * pageSize : 10
     * pages : 274
     * prePage : 0
     * size : 10
     * startRow : 1
     * total : 2739
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
         * addr : null
         * buildingId : 1181
         * buildingName : 汉阳5点5医药产业大厦
         * id : 636
         * ip : 10.10.48.191
         * isOffline : 0
         * isOpen : 1
         * name : 5点5医药产业大厦
         * networkType : 1
         * num : 5D5YY
         * offlineTime : 2020-05-08 14:09:14
         * partitionName : 汉阳区
         * siteName : 汉阳5点5医药产业大厦,F区块站点,路线站点-6号保障线路,路线站点-重点保障建筑,汉阳区重点保障线路站点,6号保障线路
         * startTime : 2019-04-03 14:32:29
         * totalEnergy : 10650.5
         * version : 7.8
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

        @Entity(tableName = "electricity")
        public static class ListBean implements Serializable {
            @PrimaryKey
            private int id;
            private String name;

            @Ignore
            private Object addr;
            private int buildingId;
            private String buildingName;
            private String ip;
            private int isOffline;
            private int isOpen;
            private int networkType;
            private String num;
            private String offlineTime;
            private String partitionName;
            private String siteName;
            private String startTime;
            private double totalEnergy;
            private String version;

            public Object getAddr() {
                return addr;
            }

            public void setAddr(Object addr) {
                this.addr = addr;
            }

            public int getBuildingId() {
                return buildingId;
            }

            public void setBuildingId(int buildingId) {
                this.buildingId = buildingId;
            }

            public String getBuildingName() {
                return buildingName;
            }

            public void setBuildingName(String buildingName) {
                this.buildingName = buildingName;
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

            public String getPartitionName() {
                return partitionName;
            }

            public void setPartitionName(String partitionName) {
                this.partitionName = partitionName;
            }

            public String getSiteName() {
                return siteName;
            }

            public void setSiteName(String siteName) {
                this.siteName = siteName;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
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
