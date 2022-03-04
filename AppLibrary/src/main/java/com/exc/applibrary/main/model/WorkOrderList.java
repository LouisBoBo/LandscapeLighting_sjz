package com.exc.applibrary.main.model;

import java.util.List;

import zuo.biao.library.base.BaseModel;

public class WorkOrderList {

    /**
     * code : 200
     * data : {"createTime":"2020-12-18 17:52:44","list":{"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":8,"list":[{"addr":"1","assertId":null,"beforeStatus":null,"createTime":"2020-12-08 14:23:43","creator":399,"creatorName":"陈小明101","description":"1","expireTime":null,"faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":null,"id":454,"manager":1,"managerName":"超级管理员","name":"11","operateTime":"2020-12-08 14:23:43","operator":null,"operatorName":null,"operatorRole":null,"operatorRoleName":null,"orderPics":null,"overtime":0,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":9,"statusName":"待初审(市级)","urgeCount":0,"version":0},{"addr":"1","assertId":null,"beforeStatus":null,"createTime":"2020-12-08 14:22:15","creator":1,"creatorName":"超级管理员","description":"1","expireTime":null,"faultTypeId":1,"faultTypeName":"其它","handleTime":null,"id":453,"manager":1,"managerName":"超级管理员","name":"1","operateTime":"2020-12-08 14:22:15","operator":null,"operatorName":null,"operatorRole":null,"operatorRoleName":null,"orderPics":null,"overtime":0,"partitionId":1,"partitionName":"总控区","relation":null,"statusId":9,"statusName":"待初审(市级)","urgeCount":0,"version":0},{"addr":"11","assertId":null,"beforeStatus":15,"createTime":"2020-12-08 14:13:29","creator":1,"creatorName":"超级管理员","description":"11111","expireTime":"2020-12-08 15:20:29","faultTypeId":31,"faultTypeName":"夜间电流异常","handleTime":1,"id":452,"manager":1,"managerName":"超级管理员","name":"111","operateTime":"2020-12-08 15:20:29","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":10,"partitionName":"东西湖区","relation":null,"statusId":1,"statusName":"待初审(区级)","urgeCount":0,"version":2},{"addr":"111","assertId":null,"beforeStatus":11,"createTime":"2020-12-08 14:00:48","creator":1,"creatorName":"超级管理员","description":"1111","expireTime":"2020-12-09 01:05:26","faultTypeId":29,"faultTypeName":"节点离线","handleTime":11,"id":451,"manager":1,"managerName":"超级管理员","name":"111212","operateTime":"2020-12-15 16:14:28","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":12,"partitionName":"武昌区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"1111","assertId":null,"beforeStatus":15,"createTime":"2020-12-08 13:52:53","creator":1,"creatorName":"超级管理员","description":"111111","expireTime":"2020-12-08 15:00:02","faultTypeId":29,"faultTypeName":"节点离线","handleTime":1,"id":450,"manager":1,"managerName":"超级管理员","name":"1111","operateTime":"2020-12-08 15:00:02","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":5,"partitionName":"航发集团","relation":null,"statusId":1,"statusName":"待初审(区级)","urgeCount":0,"version":2},{"addr":"","assertId":null,"beforeStatus":5,"createTime":"2020-11-23 18:11:00","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-11-23 19:11:12","faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":1,"id":449,"manager":1,"managerName":"超级管理员","name":"测试007","operateTime":"2020-11-23 19:11:12","operator":78,"operatorName":"技术支持111","operatorRole":31,"operatorRoleName":"test","orderPics":null,"overtime":1,"partitionId":30,"partitionName":"硚口区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"45","assertId":null,"beforeStatus":11,"createTime":"2020-08-27 20:16:42","creator":1,"creatorName":"超级管理员","description":"152","expireTime":"2020-08-28 08:16:54","faultTypeId":6,"faultTypeName":"数据库无法访问","handleTime":12,"id":448,"manager":1,"managerName":"超级管理员","name":"108","operateTime":"2020-09-19 16:00:44","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"4","assertId":null,"beforeStatus":3,"createTime":"2020-08-27 20:09:21","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-08-28 07:09:46","faultTypeId":6,"faultTypeName":"数据库无法访问","handleTime":11,"id":447,"manager":1,"managerName":"超级管理员","name":"106","operateTime":"2020-08-28 07:09:46","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":3,"statusName":"待处理","urgeCount":0,"version":3},{"addr":"","assertId":null,"beforeStatus":15,"createTime":"2020-08-27 20:02:57","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-12-08 12:16:18","faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":1,"id":446,"manager":1,"managerName":"超级管理员","name":"107","operateTime":"2020-12-08 12:16:18","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":13,"statusName":"问题申报已通过","urgeCount":0,"version":12},{"addr":"12","assertId":null,"beforeStatus":3,"createTime":"2020-08-27 19:50:52","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-08-28 07:52:39","faultTypeId":30,"faultTypeName":"网关模块通信异常","handleTime":12,"id":445,"manager":1,"managerName":"超级管理员","name":"105","operateTime":"2020-08-28 07:52:39","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":3,"statusName":"待处理","urgeCount":0,"version":3}],"navigateFirstPage":1,"navigateLastPage":8,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"nextPage":2,"orderBy":null,"pageNum":1,"pageSize":10,"pages":11,"prePage":0,"size":10,"startRow":1,"total":108}}
     * message : SUCCESS
     */

    private int code;
    private Data data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Data {
        /**
         * createTime : 2020-12-18 17:52:44
         * list : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":8,"list":[{"addr":"1","assertId":null,"beforeStatus":null,"createTime":"2020-12-08 14:23:43","creator":399,"creatorName":"陈小明101","description":"1","expireTime":null,"faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":null,"id":454,"manager":1,"managerName":"超级管理员","name":"11","operateTime":"2020-12-08 14:23:43","operator":null,"operatorName":null,"operatorRole":null,"operatorRoleName":null,"orderPics":null,"overtime":0,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":9,"statusName":"待初审(市级)","urgeCount":0,"version":0},{"addr":"1","assertId":null,"beforeStatus":null,"createTime":"2020-12-08 14:22:15","creator":1,"creatorName":"超级管理员","description":"1","expireTime":null,"faultTypeId":1,"faultTypeName":"其它","handleTime":null,"id":453,"manager":1,"managerName":"超级管理员","name":"1","operateTime":"2020-12-08 14:22:15","operator":null,"operatorName":null,"operatorRole":null,"operatorRoleName":null,"orderPics":null,"overtime":0,"partitionId":1,"partitionName":"总控区","relation":null,"statusId":9,"statusName":"待初审(市级)","urgeCount":0,"version":0},{"addr":"11","assertId":null,"beforeStatus":15,"createTime":"2020-12-08 14:13:29","creator":1,"creatorName":"超级管理员","description":"11111","expireTime":"2020-12-08 15:20:29","faultTypeId":31,"faultTypeName":"夜间电流异常","handleTime":1,"id":452,"manager":1,"managerName":"超级管理员","name":"111","operateTime":"2020-12-08 15:20:29","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":10,"partitionName":"东西湖区","relation":null,"statusId":1,"statusName":"待初审(区级)","urgeCount":0,"version":2},{"addr":"111","assertId":null,"beforeStatus":11,"createTime":"2020-12-08 14:00:48","creator":1,"creatorName":"超级管理员","description":"1111","expireTime":"2020-12-09 01:05:26","faultTypeId":29,"faultTypeName":"节点离线","handleTime":11,"id":451,"manager":1,"managerName":"超级管理员","name":"111212","operateTime":"2020-12-15 16:14:28","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":12,"partitionName":"武昌区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"1111","assertId":null,"beforeStatus":15,"createTime":"2020-12-08 13:52:53","creator":1,"creatorName":"超级管理员","description":"111111","expireTime":"2020-12-08 15:00:02","faultTypeId":29,"faultTypeName":"节点离线","handleTime":1,"id":450,"manager":1,"managerName":"超级管理员","name":"1111","operateTime":"2020-12-08 15:00:02","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":5,"partitionName":"航发集团","relation":null,"statusId":1,"statusName":"待初审(区级)","urgeCount":0,"version":2},{"addr":"","assertId":null,"beforeStatus":5,"createTime":"2020-11-23 18:11:00","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-11-23 19:11:12","faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":1,"id":449,"manager":1,"managerName":"超级管理员","name":"测试007","operateTime":"2020-11-23 19:11:12","operator":78,"operatorName":"技术支持111","operatorRole":31,"operatorRoleName":"test","orderPics":null,"overtime":1,"partitionId":30,"partitionName":"硚口区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"45","assertId":null,"beforeStatus":11,"createTime":"2020-08-27 20:16:42","creator":1,"creatorName":"超级管理员","description":"152","expireTime":"2020-08-28 08:16:54","faultTypeId":6,"faultTypeName":"数据库无法访问","handleTime":12,"id":448,"manager":1,"managerName":"超级管理员","name":"108","operateTime":"2020-09-19 16:00:44","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"4","assertId":null,"beforeStatus":3,"createTime":"2020-08-27 20:09:21","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-08-28 07:09:46","faultTypeId":6,"faultTypeName":"数据库无法访问","handleTime":11,"id":447,"manager":1,"managerName":"超级管理员","name":"106","operateTime":"2020-08-28 07:09:46","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":3,"statusName":"待处理","urgeCount":0,"version":3},{"addr":"","assertId":null,"beforeStatus":15,"createTime":"2020-08-27 20:02:57","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-12-08 12:16:18","faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":1,"id":446,"manager":1,"managerName":"超级管理员","name":"107","operateTime":"2020-12-08 12:16:18","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":13,"statusName":"问题申报已通过","urgeCount":0,"version":12},{"addr":"12","assertId":null,"beforeStatus":3,"createTime":"2020-08-27 19:50:52","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-08-28 07:52:39","faultTypeId":30,"faultTypeName":"网关模块通信异常","handleTime":12,"id":445,"manager":1,"managerName":"超级管理员","name":"105","operateTime":"2020-08-28 07:52:39","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":3,"statusName":"待处理","urgeCount":0,"version":3}],"navigateFirstPage":1,"navigateLastPage":8,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"nextPage":2,"orderBy":null,"pageNum":1,"pageSize":10,"pages":11,"prePage":0,"size":10,"startRow":1,"total":108}
         */

        private String createTime;
        private list list;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public list getList() {
            return list;
        }

        public void setList(list list) {
            this.list = list;
        }

        public static class list {
            /**
             * endRow : 10
             * firstPage : 1
             * hasNextPage : true
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : false
             * lastPage : 8
             * list : [{"addr":"1","assertId":null,"beforeStatus":null,"createTime":"2020-12-08 14:23:43","creator":399,"creatorName":"陈小明101","description":"1","expireTime":null,"faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":null,"id":454,"manager":1,"managerName":"超级管理员","name":"11","operateTime":"2020-12-08 14:23:43","operator":null,"operatorName":null,"operatorRole":null,"operatorRoleName":null,"orderPics":null,"overtime":0,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":9,"statusName":"待初审(市级)","urgeCount":0,"version":0},{"addr":"1","assertId":null,"beforeStatus":null,"createTime":"2020-12-08 14:22:15","creator":1,"creatorName":"超级管理员","description":"1","expireTime":null,"faultTypeId":1,"faultTypeName":"其它","handleTime":null,"id":453,"manager":1,"managerName":"超级管理员","name":"1","operateTime":"2020-12-08 14:22:15","operator":null,"operatorName":null,"operatorRole":null,"operatorRoleName":null,"orderPics":null,"overtime":0,"partitionId":1,"partitionName":"总控区","relation":null,"statusId":9,"statusName":"待初审(市级)","urgeCount":0,"version":0},{"addr":"11","assertId":null,"beforeStatus":15,"createTime":"2020-12-08 14:13:29","creator":1,"creatorName":"超级管理员","description":"11111","expireTime":"2020-12-08 15:20:29","faultTypeId":31,"faultTypeName":"夜间电流异常","handleTime":1,"id":452,"manager":1,"managerName":"超级管理员","name":"111","operateTime":"2020-12-08 15:20:29","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":10,"partitionName":"东西湖区","relation":null,"statusId":1,"statusName":"待初审(区级)","urgeCount":0,"version":2},{"addr":"111","assertId":null,"beforeStatus":11,"createTime":"2020-12-08 14:00:48","creator":1,"creatorName":"超级管理员","description":"1111","expireTime":"2020-12-09 01:05:26","faultTypeId":29,"faultTypeName":"节点离线","handleTime":11,"id":451,"manager":1,"managerName":"超级管理员","name":"111212","operateTime":"2020-12-15 16:14:28","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":12,"partitionName":"武昌区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"1111","assertId":null,"beforeStatus":15,"createTime":"2020-12-08 13:52:53","creator":1,"creatorName":"超级管理员","description":"111111","expireTime":"2020-12-08 15:00:02","faultTypeId":29,"faultTypeName":"节点离线","handleTime":1,"id":450,"manager":1,"managerName":"超级管理员","name":"1111","operateTime":"2020-12-08 15:00:02","operator":1,"operatorName":"超级管理员","operatorRole":4,"operatorRoleName":"分区管理员","orderPics":null,"overtime":1,"partitionId":5,"partitionName":"航发集团","relation":null,"statusId":1,"statusName":"待初审(区级)","urgeCount":0,"version":2},{"addr":"","assertId":null,"beforeStatus":5,"createTime":"2020-11-23 18:11:00","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-11-23 19:11:12","faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":1,"id":449,"manager":1,"managerName":"超级管理员","name":"测试007","operateTime":"2020-11-23 19:11:12","operator":78,"operatorName":"技术支持111","operatorRole":31,"operatorRoleName":"test","orderPics":null,"overtime":1,"partitionId":30,"partitionName":"硚口区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"45","assertId":null,"beforeStatus":11,"createTime":"2020-08-27 20:16:42","creator":1,"creatorName":"超级管理员","description":"152","expireTime":"2020-08-28 08:16:54","faultTypeId":6,"faultTypeName":"数据库无法访问","handleTime":12,"id":448,"manager":1,"managerName":"超级管理员","name":"108","operateTime":"2020-09-19 16:00:44","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":5,"statusName":"正在处理","urgeCount":0,"version":4},{"addr":"4","assertId":null,"beforeStatus":3,"createTime":"2020-08-27 20:09:21","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-08-28 07:09:46","faultTypeId":6,"faultTypeName":"数据库无法访问","handleTime":11,"id":447,"manager":1,"managerName":"超级管理员","name":"106","operateTime":"2020-08-28 07:09:46","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":3,"statusName":"待处理","urgeCount":0,"version":3},{"addr":"","assertId":null,"beforeStatus":15,"createTime":"2020-08-27 20:02:57","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-12-08 12:16:18","faultTypeId":2,"faultTypeName":"服务器网络异常","handleTime":1,"id":446,"manager":1,"managerName":"超级管理员","name":"107","operateTime":"2020-12-08 12:16:18","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":13,"statusName":"问题申报已通过","urgeCount":0,"version":12},{"addr":"12","assertId":null,"beforeStatus":3,"createTime":"2020-08-27 19:50:52","creator":1,"creatorName":"超级管理员","description":"","expireTime":"2020-08-28 07:52:39","faultTypeId":30,"faultTypeName":"网关模块通信异常","handleTime":12,"id":445,"manager":1,"managerName":"超级管理员","name":"105","operateTime":"2020-08-28 07:52:39","operator":400,"operatorName":"陈小明102","operatorRole":5,"operatorRoleName":"技术支持","orderPics":null,"overtime":1,"partitionId":3,"partitionName":"经开区","relation":null,"statusId":3,"statusName":"待处理","urgeCount":0,"version":3}]
             * navigateFirstPage : 1
             * navigateLastPage : 8
             * navigatePages : 8
             * navigatepageNums : [1,2,3,4,5,6,7,8]
             * nextPage : 2
             * orderBy : null
             * pageNum : 1
             * pageSize : 10
             * pages : 11
             * prePage : 0
             * size : 10
             * startRow : 1
             * total : 108
             */

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
            private java.util.List<WorkOrderItem> list;
            private java.util.List<Integer> navigatepageNums;

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

            public List<WorkOrderItem> getList() {
                return list;
            }

            public void setList(List<WorkOrderItem> list) {
                this.list = list;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class WorkOrderItem extends BaseModel {
                /**
                 * addr : 1
                 * assertId : null
                 * beforeStatus : null
                 * createTime : 2020-12-08 14:23:43
                 * creator : 399
                 * creatorName : 陈小明101
                 * description : 1
                 * expireTime : null
                 * faultTypeId : 2
                 * faultTypeName : 服务器网络异常
                 * handleTime : null
                 * id : 454
                 * manager : 1
                 * managerName : 超级管理员
                 * name : 11
                 * operateTime : 2020-12-08 14:23:43
                 * operator : null
                 * operatorName : null
                 * operatorRole : null
                 * operatorRoleName : null
                 * orderPics : null
                 * overtime : 0
                 * partitionId : 3
                 * partitionName : 经开区
                 * relation : null
                 * statusId : 9
                 * statusName : 待初审(市级)
                 * urgeCount : 0
                 * version : 0
                 */

                private String addr;
                private String assertId;
                private Object beforeStatus;
                private String createTime;
                private int creator;
                private String creatorName;
                private String description;
                private String expireTime;

                public String getCurrentTime() {
                    return currentTime;
                }

                public void setCurrentTime(String currentTime) {
                    this.currentTime = currentTime;
                }

                private String currentTime;
                private String faultTypeId;
                private String faultTypeName;
                private Object handleTime;
                private int id;
                private int manager;
                private String managerName;
                private String name;
                private String operateTime;
                private int operator;
                private Object operatorRole;
                private Object operatorRoleName;
                private Object orderPics;
                private int overtime;
                private String partitionId;
                private String partitionName;
                private Object relation;
                private int statusId;
                private String statusName;
                private int urgeCount;
                private int version;

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                public Object getAssertId() {
                    return assertId;
                }

                public void setAssertId(String assertId) {
                    this.assertId = assertId;
                }

                public Object getBeforeStatus() {
                    return beforeStatus;
                }

                public void setBeforeStatus(Object beforeStatus) {
                    this.beforeStatus = beforeStatus;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getCreator() {
                    return creator;
                }

                public void setCreator(int creator) {
                    this.creator = creator;
                }

                public String getCreatorName() {
                    return creatorName;
                }

                public void setCreatorName(String creatorName) {
                    this.creatorName = creatorName;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getExpireTime() {
                    return expireTime;
                }

                public void setExpireTime(String expireTime) {
                    this.expireTime = expireTime;
                }

                public String getFaultTypeId() {
                    return faultTypeId;
                }

                public void setFaultTypeId(String faultTypeId) {
                    this.faultTypeId = faultTypeId;
                }

                public String getFaultTypeName() {
                    return faultTypeName;
                }

                public void setFaultTypeName(String faultTypeName) {
                    this.faultTypeName = faultTypeName;
                }

                public Object getHandleTime() {
                    return handleTime;
                }

                public void setHandleTime(Object handleTime) {
                    this.handleTime = handleTime;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getManager() {
                    return manager;
                }

                public void setManager(int manager) {
                    this.manager = manager;
                }

                public String getManagerName() {
                    return managerName;
                }

                public void setManagerName(String managerName) {
                    this.managerName = managerName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOperateTime() {
                    return operateTime;
                }

                public void setOperateTime(String operateTime) {
                    this.operateTime = operateTime;
                }

                public int getOperator() {
                    return operator;
                }

                public void setOperator(int operator) {
                    this.operator = operator;
                }



                public Object getOperatorRole() {
                    return operatorRole;
                }

                public void setOperatorRole(Object operatorRole) {
                    this.operatorRole = operatorRole;
                }

                public Object getOperatorRoleName() {
                    return operatorRoleName;
                }

                public void setOperatorRoleName(Object operatorRoleName) {
                    this.operatorRoleName = operatorRoleName;
                }

                public Object getOrderPics() {
                    return orderPics;
                }

                public void setOrderPics(Object orderPics) {
                    this.orderPics = orderPics;
                }

                public int getOvertime() {
                    return overtime;
                }

                public void setOvertime(int overtime) {
                    this.overtime = overtime;
                }

                public String getPartitionId() {
                    return partitionId;
                }

                public void setPartitionId(String partitionId) {
                    this.partitionId = partitionId;
                }

                public String getPartitionName() {
                    return partitionName;
                }

                public void setPartitionName(String partitionName) {
                    this.partitionName = partitionName;
                }

                public Object getRelation() {
                    return relation;
                }

                public void setRelation(Object relation) {
                    this.relation = relation;
                }

                public int getStatusId() {
                    return statusId;
                }

                public void setStatusId(int statusId) {
                    this.statusId = statusId;
                }

                public String getStatusName() {
                    return statusName;
                }

                public void setStatusName(String statusName) {
                    this.statusName = statusName;
                }

                public int getUrgeCount() {
                    return urgeCount;
                }

                public void setUrgeCount(int urgeCount) {
                    this.urgeCount = urgeCount;
                }

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }

                @Override
                protected boolean isCorrect() {
                    return true;
                }
            }
        }
    }
}
