package com.exc.applibrary.main.model;

import java.util.List;

public class StrategyModel {

    /**
     * code : 200
     * data : {"endRow":5,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"cycleTypes":[1,2,3,4,5,6,7],"description":"11111111111","endDate":null,"id":2,"mode":1,"name":"11111","pointInfos":null,"startDate":"","status":1},{"cycleTypes":null,"description":"11111111111","endDate":"2021-02-03","id":3,"mode":2,"name":"11111","pointInfos":null,"startDate":"2021-02-01","status":0},{"cycleTypes":null,"description":"111111111112","endDate":"2021-02-03","id":4,"mode":2,"name":"111112","pointInfos":null,"startDate":"2021-02-01","status":0},{"cycleTypes":null,"description":"111111111112","endDate":"2021-02-03","id":5,"mode":2,"name":"111112","pointInfos":null,"startDate":"2021-02-01","status":0},{"cycleTypes":[1,2,3,4,5,6,7],"description":"333","endDate":null,"id":6,"mode":1,"name":"333","pointInfos":null,"startDate":"","status":0}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"orderBy":null,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}
     * message : SUCCESS
     */

    private int code;
    /**
     * endRow : 5
     * firstPage : 1
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * lastPage : 1
     * list : [{"cycleTypes":[1,2,3,4,5,6,7],"description":"11111111111","endDate":null,"id":2,"mode":1,"name":"11111","pointInfos":null,"startDate":"","status":1},{"cycleTypes":null,"description":"11111111111","endDate":"2021-02-03","id":3,"mode":2,"name":"11111","pointInfos":null,"startDate":"2021-02-01","status":0},{"cycleTypes":null,"description":"111111111112","endDate":"2021-02-03","id":4,"mode":2,"name":"111112","pointInfos":null,"startDate":"2021-02-01","status":0},{"cycleTypes":null,"description":"111111111112","endDate":"2021-02-03","id":5,"mode":2,"name":"111112","pointInfos":null,"startDate":"2021-02-01","status":0},{"cycleTypes":[1,2,3,4,5,6,7],"description":"333","endDate":null,"id":6,"mode":1,"name":"333","pointInfos":null,"startDate":"","status":0}]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * orderBy : null
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * prePage : 0
     * size : 5
     * startRow : 1
     * total : 5
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
         * cycleTypes : [1,2,3,4,5,6,7]
         * description : 11111111111
         * endDate : null
         * id : 2
         * mode : 1
         * name : 11111
         * pointInfos : null
         * startDate :
         * status : 1
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
            private String description;
            private Object endDate;
            private int id;
            private int mode;
            private String name;
            private Object pointInfos;
            private String startDate;
            private int status;
            private List<Integer> cycleTypes;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getEndDate() {
                return endDate;
            }

            public void setEndDate(Object endDate) {
                this.endDate = endDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMode() {
                return mode;
            }

            public void setMode(int mode) {
                this.mode = mode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getPointInfos() {
                return pointInfos;
            }

            public void setPointInfos(Object pointInfos) {
                this.pointInfos = pointInfos;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<Integer> getCycleTypes() {
                return cycleTypes;
            }

            public void setCycleTypes(List<Integer> cycleTypes) {
                this.cycleTypes = cycleTypes;
            }
        }
    }
}
