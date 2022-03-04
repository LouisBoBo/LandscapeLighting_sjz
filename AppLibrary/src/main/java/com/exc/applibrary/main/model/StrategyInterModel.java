package com.exc.applibrary.main.model;

import java.util.List;

public class StrategyInterModel {

    /**
     * code : 200
     * data : {"endRow":4,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"id":58,"partitionId":1,"partitionName":"总控区","strategyExplain":"全局策略","strategyName":"测试","strategyNum":1,"strategyStatus":2,"strategyType":2},{"id":59,"partitionId":1,"partitionName":"总控区","strategyExplain":"ff","strategyName":"全市启动","strategyNum":111601,"strategyStatus":2,"strategyType":2}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":4,"startRow":1,"total":4}
     * message : SUCCESS
     */

    private int code;
    /**
     * endRow : 4
     * firstPage : 1
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * lastPage : 1
     * list : [{"id":58,"partitionId":1,"partitionName":"总控区","strategyExplain":"全局策略","strategyName":"测试","strategyNum":1,"strategyStatus":2,"strategyType":2},{"id":59,"partitionId":1,"partitionName":"总控区","strategyExplain":"ff","strategyName":"全市启动","strategyNum":111601,"strategyStatus":2,"strategyType":2}]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * pageNum : 1
     * pageSize : 10
     * pages : 1
     * prePage : 0
     * size : 4
     * startRow : 1
     * total : 4
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
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        /**
         * id : 58
         * partitionId : 1
         * partitionName : 总控区
         * strategyExplain : 全局策略
         * strategyName : 测试
         * strategyNum : 1
         * strategyStatus : 2
         * strategyType : 2
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
            private int id;
            private int partitionId;
            private String partitionName;
            private String strategyExplain;
            private String strategyName;
            private int strategyNum;
            private int strategyStatus;
            private int strategyType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }

            public String getPartitionName() {
                return partitionName;
            }

            public void setPartitionName(String partitionName) {
                this.partitionName = partitionName;
            }

            public String getStrategyExplain() {
                return strategyExplain;
            }

            public void setStrategyExplain(String strategyExplain) {
                this.strategyExplain = strategyExplain;
            }

            public String getStrategyName() {
                return strategyName;
            }

            public void setStrategyName(String strategyName) {
                this.strategyName = strategyName;
            }

            public int getStrategyNum() {
                return strategyNum;
            }

            public void setStrategyNum(int strategyNum) {
                this.strategyNum = strategyNum;
            }

            public int getStrategyStatus() {
                return strategyStatus;
            }

            public void setStrategyStatus(int strategyStatus) {
                this.strategyStatus = strategyStatus;
            }

            public int getStrategyType() {
                return strategyType;
            }

            public void setStrategyType(int strategyType) {
                this.strategyType = strategyType;
            }
        }
    }
}
