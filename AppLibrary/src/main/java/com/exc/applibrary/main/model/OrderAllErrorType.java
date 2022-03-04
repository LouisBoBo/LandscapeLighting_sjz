package com.exc.applibrary.main.model;

import java.util.List;

public class OrderAllErrorType {
    /**
     * code : 200
     * data : {"endRow":12,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"faultTypeId":1,"faultTypeName":"其它","openOrder":0},{"faultTypeId":2,"faultTypeName":"服务器网络异常","openOrder":0},{"faultTypeId":6,"faultTypeName":"数据库无法访问","openOrder":0},{"faultTypeId":24,"faultTypeName":"视频处理失败","openOrder":0},{"faultTypeId":29,"faultTypeName":"节点离线","openOrder":0},{"faultTypeId":30,"faultTypeName":"网关模块通信异常","openOrder":0},{"faultTypeId":31,"faultTypeName":"夜间电流异常","openOrder":0},{"faultTypeId":32,"faultTypeName":"白天电流异常","openOrder":0},{"faultTypeId":45,"faultTypeName":"服务器秘钥解析错误","openOrder":0},{"faultTypeId":46,"faultTypeName":"应用服务故障","openOrder":0},{"faultTypeId":47,"faultTypeName":"日常维护","openOrder":0},{"faultTypeId":48,"faultTypeName":"生命周期维护","openOrder":0},{"faultTypeId":49,"faultTypeName":"强电箱开门","openOrder":0}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":null,"pageNum":1,"pageSize":13,"pages":1,"prePage":0,"size":13,"startRow":0,"total":13}
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
         * endRow : 12
         * firstPage : 1
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 1
         * list : [{"faultTypeId":1,"faultTypeName":"其它","openOrder":0},{"faultTypeId":2,"faultTypeName":"服务器网络异常","openOrder":0},{"faultTypeId":6,"faultTypeName":"数据库无法访问","openOrder":0},{"faultTypeId":24,"faultTypeName":"视频处理失败","openOrder":0},{"faultTypeId":29,"faultTypeName":"节点离线","openOrder":0},{"faultTypeId":30,"faultTypeName":"网关模块通信异常","openOrder":0},{"faultTypeId":31,"faultTypeName":"夜间电流异常","openOrder":0},{"faultTypeId":32,"faultTypeName":"白天电流异常","openOrder":0},{"faultTypeId":45,"faultTypeName":"服务器秘钥解析错误","openOrder":0},{"faultTypeId":46,"faultTypeName":"应用服务故障","openOrder":0},{"faultTypeId":47,"faultTypeName":"日常维护","openOrder":0},{"faultTypeId":48,"faultTypeName":"生命周期维护","openOrder":0},{"faultTypeId":49,"faultTypeName":"强电箱开门","openOrder":0}]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * orderBy : null
         * pageNum : 1
         * pageSize : 13
         * pages : 1
         * prePage : 0
         * size : 13
         * startRow : 0
         * total : 13
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
        private java.util.List<list> list;
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

        public List<list> getList() {
            return list;
        }

        public void setList(List<list> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class list {
            /**
             * faultTypeId : 1
             * faultTypeName : 其它
             * openOrder : 0
             */

            private int faultTypeId;
            private String faultTypeName;
            private int openOrder;

            public int getFaultTypeId() {
                return faultTypeId;
            }

            public void setFaultTypeId(int faultTypeId) {
                this.faultTypeId = faultTypeId;
            }

            public String getFaultTypeName() {
                return faultTypeName;
            }

            public void setFaultTypeName(String faultTypeName) {
                this.faultTypeName = faultTypeName;
            }

            public int getOpenOrder() {
                return openOrder;
            }

            public void setOpenOrder(int openOrder) {
                this.openOrder = openOrder;
            }
        }
    }
}
