package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ShowSwitchSiteList implements Serializable {
    /**
     * code : 200
     * data : {"endRow":3,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"buildingNum":1,"description":"te","id":30,"name":"te","partitionName":"灞桥区","programNum":1,"siteTypeId":3,"siteTypeName":"联动站点","status":0},{"buildingNum":1,"description":"南山","id":31,"name":"南山","partitionName":"灞桥区","programNum":2,"siteTypeId":3,"siteTypeName":"联动站点","status":0},{"buildingNum":1,"description":"测试","id":27,"name":"测试","partitionName":"灞桥区","programNum":4,"siteTypeId":3,"siteTypeName":"联动站点","status":0}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":null,"pageNum":1,"pageSize":5999,"pages":1,"prePage":0,"size":3,"startRow":1,"total":3}
     * message : SUCCESS
     */

    public int code;
    public DataBean data;
    public String message;

    @Data
    public static class DataBean implements Serializable {
        /**
         * endRow : 3
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"buildingNum":1,"description":"te","id":30,"name":"te","partitionName":"灞桥区","programNum":1,"siteTypeId":3,"siteTypeName":"联动站点","status":0},{"buildingNum":1,"description":"南山","id":31,"name":"南山","partitionName":"灞桥区","programNum":2,"siteTypeId":3,"siteTypeName":"联动站点","status":0},{"buildingNum":1,"description":"测试","id":27,"name":"测试","partitionName":"灞桥区","programNum":4,"siteTypeId":3,"siteTypeName":"联动站点","status":0}]
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
         * size : 3
         * startRow : 1
         * total : 3
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

        @Data
        public static class ListBean implements Serializable {
            /**
             * buildingNum : 1
             * description : te
             * id : 30
             * name : te
             * partitionName : 灞桥区
             * programNum : 1
             * siteTypeId : 3
             * siteTypeName : 联动站点
             * status : 0
             */

            public int buildingNum;
            public String description;
            public int id;
            public String name;
            public String partitionName;
            public int programNum;
            public int siteTypeId;
            public String siteTypeName;
            public int status;
        }
    }
}
