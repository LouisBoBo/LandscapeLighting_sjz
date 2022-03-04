package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;

public class NodeList implements Serializable {
    /**
     * code : 200
     * data : {"endRow":11,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"addr":null,"buildingId":null,"buildingName":null,"id":5,"ip":"10.0.209.142","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"2000E","networkType":2,"num":"35678","offlineTime":"2021-03-30 16:19:02","partitionName":null,"routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:32:32","siteName":null,"startTime":null,"totalEnergy":0,"version":"4.12"},{"addr":null,"buildingId":null,"buildingName":null,"id":2,"ip":"192.168.10.240","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"jiancang6lou","networkType":2,"num":"25356","offlineTime":"2021-04-14 11:13:25","partitionName":null,"routerIsOffline":3,"routerOfflineTime":"2021-04-14 11:36:13","siteName":null,"startTime":null,"totalEnergy":0,"version":"4.13"},{"addr":null,"buildingId":null,"buildingName":null,"id":3,"ip":"10.0.209.142","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"jiancang6loucs","networkType":2,"num":"25486","offlineTime":"2021-04-19 09:27:48","partitionName":null,"routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:32:29","siteName":null,"startTime":null,"totalEnergy":0,"version":"4.13"},{"addr":null,"buildingId":null,"buildingName":null,"id":8,"ip":"10.0.209.56","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"云控演示柜","networkType":2,"num":"YKYSG","offlineTime":"2021-04-01 12:21:06","partitionName":null,"routerIsOffline":3,"routerOfflineTime":"2021-04-19 16:28:59","siteName":null,"startTime":null,"totalEnergy":0.03999999910593033,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":6,"ip":"192.168.112.65","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试","networkType":1,"num":"10003","offlineTime":"2021-03-30 11:01:00","partitionName":"灞桥区","routerIsOffline":3,"routerOfflineTime":"2021-04-20 14:00:43","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":10,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试","networkType":1,"num":"W1006","offlineTime":"2021-04-06 14:14:28","partitionName":"灞桥区","routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:33:12","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":7,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试2","networkType":1,"num":"10007","offlineTime":"2021-03-30 11:05:35","partitionName":"灞桥区","routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:32:46","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":11,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试3","networkType":1,"num":"10008","offlineTime":"2021-04-06 14:16:37","partitionName":"灞桥区","routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:33:12","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":null,"buildingName":null,"id":12,"ip":"192.168.112.105","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"移动端","networkType":1,"num":"10023","offlineTime":"2021-04-06 14:22:20","partitionName":null,"routerIsOffline":3,"routerOfflineTime":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":1,"buildingName":"灞桥区","id":1,"ip":"10.0.209.137","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"西安","networkType":2,"num":"88888","offlineTime":"2021-03-26 17:52:26","partitionName":"灞桥区","routerIsOffline":3,"routerOfflineTime":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":null,"buildingName":null,"id":9,"ip":"192.168.21.2","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"路灯展厅","networkType":1,"num":"10006","offlineTime":"2021-04-13 15:31:34","partitionName":null,"routerIsOffline":3,"routerOfflineTime":"2021-04-13 15:28:33","siteName":null,"startTime":null,"totalEnergy":2.9619066993127054E37,"version":"2.11"}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":null,"pageNum":1,"pageSize":5999,"pages":1,"prePage":0,"size":11,"startRow":1,"total":11}
     * message : SUCCESS
     * time : null
     */

    public int code;
    public DataBean data;
    public String message;
    public Object time;

    public static class DataBean implements Serializable {
        /**
         * endRow : 11
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"addr":null,"buildingId":null,"buildingName":null,"id":5,"ip":"10.0.209.142","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"2000E","networkType":2,"num":"35678","offlineTime":"2021-03-30 16:19:02","partitionName":null,"routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:32:32","siteName":null,"startTime":null,"totalEnergy":0,"version":"4.12"},{"addr":null,"buildingId":null,"buildingName":null,"id":2,"ip":"192.168.10.240","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"jiancang6lou","networkType":2,"num":"25356","offlineTime":"2021-04-14 11:13:25","partitionName":null,"routerIsOffline":3,"routerOfflineTime":"2021-04-14 11:36:13","siteName":null,"startTime":null,"totalEnergy":0,"version":"4.13"},{"addr":null,"buildingId":null,"buildingName":null,"id":3,"ip":"10.0.209.142","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"jiancang6loucs","networkType":2,"num":"25486","offlineTime":"2021-04-19 09:27:48","partitionName":null,"routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:32:29","siteName":null,"startTime":null,"totalEnergy":0,"version":"4.13"},{"addr":null,"buildingId":null,"buildingName":null,"id":8,"ip":"10.0.209.56","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"云控演示柜","networkType":2,"num":"YKYSG","offlineTime":"2021-04-01 12:21:06","partitionName":null,"routerIsOffline":3,"routerOfflineTime":"2021-04-19 16:28:59","siteName":null,"startTime":null,"totalEnergy":0.03999999910593033,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":6,"ip":"192.168.112.65","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试","networkType":1,"num":"10003","offlineTime":"2021-03-30 11:01:00","partitionName":"灞桥区","routerIsOffline":3,"routerOfflineTime":"2021-04-20 14:00:43","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":10,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试","networkType":1,"num":"W1006","offlineTime":"2021-04-06 14:14:28","partitionName":"灞桥区","routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:33:12","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":7,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试2","networkType":1,"num":"10007","offlineTime":"2021-03-30 11:05:35","partitionName":"灞桥区","routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:32:46","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":2,"buildingName":"测试","id":11,"ip":"192.168.112.104","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"测试3","networkType":1,"num":"10008","offlineTime":"2021-04-06 14:16:37","partitionName":"灞桥区","routerIsOffline":1,"routerOfflineTime":"2021-04-20 14:33:12","siteName":"测试","startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":null,"buildingName":null,"id":12,"ip":"192.168.112.105","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"移动端","networkType":1,"num":"10023","offlineTime":"2021-04-06 14:22:20","partitionName":null,"routerIsOffline":3,"routerOfflineTime":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":1,"buildingName":"灞桥区","id":1,"ip":"10.0.209.137","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"西安","networkType":2,"num":"88888","offlineTime":"2021-03-26 17:52:26","partitionName":"灞桥区","routerIsOffline":3,"routerOfflineTime":null,"siteName":null,"startTime":null,"totalEnergy":0,"version":"7.3"},{"addr":null,"buildingId":null,"buildingName":null,"id":9,"ip":"192.168.21.2","isOffline":1,"isOpen":1,"latitude":null,"longitude":null,"name":"路灯展厅","networkType":1,"num":"10006","offlineTime":"2021-04-13 15:31:34","partitionName":null,"routerIsOffline":3,"routerOfflineTime":"2021-04-13 15:28:33","siteName":null,"startTime":null,"totalEnergy":2.9619066993127054E37,"version":"2.11"}]
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
         * size : 11
         * startRow : 1
         * total : 11
         */

        public int endRow;
        public int firstPage;
        public boolean hasNextPage;
        public boolean hasPreviousPage;
        public boolean isFirstPage;
        public boolean isLastPage;
        public int lastPage;
        public int navigateFirstPage;
        public int navigateLastPage;
        public int navigatePages;
        public int nextPage;
        public Object orderBy;
        public int pageNum;
        public int pageSize;
        public int pages;
        public int prePage;
        public int size;
        public int startRow;
        public int total;
        public List<ListBean> list;
        public List<Integer> navigatepageNums;

        public static class ListBean implements Serializable {
            /**
             * addr : null
             * buildingId : null
             * buildingName : null
             * id : 5
             * ip : 10.0.209.142
             * isOffline : 1
             * isOpen : 1
             * latitude : null
             * longitude : null
             * name : 2000E
             * networkType : 2
             * num : 35678
             * offlineTime : 2021-03-30 16:19:02
             * partitionName : null
             * routerIsOffline : 1
             * routerOfflineTime : 2021-04-20 14:32:32
             * siteName : null
             * startTime : null
             * totalEnergy : 0
             * version : 4.12
             */

            public Object addr;
            public Object buildingId;
            public Object buildingName;
            public int id;
            public String ip;
            public int isOffline;
            public int isOpen;
            public Object latitude;
            public Object longitude;
            public String name;
            public int networkType;
            public String num;
            public String offlineTime;
            public Object partitionName;
            public int routerIsOffline;
            public String routerOfflineTime;
            public Object siteName;
            public Object startTime;
            public int totalEnergy;
            public String version;
        }
    }
}
