package com.exc.applibrary.main.model;

import java.util.List;

public class ChannelAllModel {

    /**
     * code : 200
     * data : {"endRow":3,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93243,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道1","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":181,"value":0},{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93245,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道2","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":183,"value":0},{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93247,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道3","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":185,"value":0},{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93249,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道4","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":187,"value":0}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":4,"pages":1,"prePage":0,"size":4,"startRow":0,"total":4}
     * message : SUCCESS
     */

    private int code;
    /**
     * endRow : 3
     * firstPage : 1
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * lastPage : 1
     * list : [{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93243,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道1","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":181,"value":0},{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93245,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道2","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":183,"value":0},{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93247,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道3","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":185,"value":0},{"buildingName":"硚口7天连锁酒店1","canChannelTypeId":2,"canDeviceName":"驱动模块-A1-MLC-1374_1.1.11","channelTypeName":"继电器回路","currentValue":0,"did":4945,"dsn":22,"id":93249,"isEdit":0,"name":"驱动模块-A1-MLC-1374_1.1.11-通道4","nid":821,"nodeName":"7天连锁酒店","sid":4,"tagId":187,"value":0}]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * pageNum : 1
     * pageSize : 4
     * pages : 1
     * prePage : 0
     * size : 4
     * startRow : 0
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
         * buildingName : 硚口7天连锁酒店1
         * canChannelTypeId : 2
         * canDeviceName : 驱动模块-A1-MLC-1374_1.1.11
         * channelTypeName : 继电器回路
         * currentValue : 0
         * did : 4945
         * dsn : 22
         * id : 93243
         * isEdit : 0
         * name : 驱动模块-A1-MLC-1374_1.1.11-通道1
         * nid : 821
         * nodeName : 7天连锁酒店
         * sid : 4
         * tagId : 181
         * value : 0
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
            private String buildingName;
            private int canChannelTypeId;
            private String canDeviceName;
            private String channelTypeName;
            private int currentValue;
            private int did;
            private int dsn;
            private int id;
            private int isEdit;
            private String name;
            private int nid;
            private String nodeName;
            private int sid;
            private int tagId;
            private int value;
            private Boolean isselect;
            private Boolean item_select;

            public Boolean getIsselect() {
                return isselect;
            }

            public void setIsselect(Boolean isselect) {
                this.isselect = isselect;
            }

            public Boolean getItem_select() {
                return item_select;
            }

            public void setItem_select(Boolean item_select) {
                this.item_select = item_select;
            }

            public String getBuildingName() {
                return buildingName;
            }

            public void setBuildingName(String buildingName) {
                this.buildingName = buildingName;
            }

            public int getCanChannelTypeId() {
                return canChannelTypeId;
            }

            public void setCanChannelTypeId(int canChannelTypeId) {
                this.canChannelTypeId = canChannelTypeId;
            }

            public String getCanDeviceName() {
                return canDeviceName;
            }

            public void setCanDeviceName(String canDeviceName) {
                this.canDeviceName = canDeviceName;
            }

            public String getChannelTypeName() {
                return channelTypeName;
            }

            public void setChannelTypeName(String channelTypeName) {
                this.channelTypeName = channelTypeName;
            }

            public int getCurrentValue() {
                return currentValue;
            }

            public void setCurrentValue(int currentValue) {
                this.currentValue = currentValue;
            }

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public int getDsn() {
                return dsn;
            }

            public void setDsn(int dsn) {
                this.dsn = dsn;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsEdit() {
                return isEdit;
            }

            public void setIsEdit(int isEdit) {
                this.isEdit = isEdit;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNid() {
                return nid;
            }

            public void setNid(int nid) {
                this.nid = nid;
            }

            public String getNodeName() {
                return nodeName;
            }

            public void setNodeName(String nodeName) {
                this.nodeName = nodeName;
            }

            public int getSid() {
                return sid;
            }

            public void setSid(int sid) {
                this.sid = sid;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
