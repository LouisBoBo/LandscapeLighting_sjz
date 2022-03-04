package com.exc.applibrary.main.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageListBean implements Serializable {

    /**
     * code : 200
     * data : {"endRow":12,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":8,"list":[{"createTime":"2021-03-23 10:22:16","headline":"运营后台服务异常","id":278740,"sid":1,"status":0,"text":"运营后台服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:22:01","headline":"物联网服务异常","id":278739,"sid":1,"status":0,"text":"物联网服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:21:46","headline":"帧同步服务异常","id":278738,"sid":1,"status":0,"text":"帧同步服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:21:31","headline":"文件上传服务异常","id":278737,"sid":1,"status":0,"text":"文件上传服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:21:16","headline":"云控后台服务无法连接","id":278736,"sid":1,"status":0,"text":"后台服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:20:40","headline":"文件传输服务异常","id":278735,"sid":1,"status":0,"text":"文件传输服务异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:20:19","headline":"无法ping通10.192.47.9，请检查MysqlServer服务器网络状态","id":278733,"sid":1,"status":0,"text":"无法ping通10.192.47.9,MysqlServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:19","headline":"前台服务故障","id":278734,"sid":1,"status":0,"text":"前台服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:20:18","headline":"无法ping通10.192.47.11，请检查FileTransferServer服务器网络状态","id":278732,"sid":1,"status":0,"text":"无法ping通10.192.47.11,FileTransferServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:17","headline":"无法ping通10.192.47.8，请检查RouterServer服务器网络状态","id":278731,"sid":1,"status":0,"text":"无法ping通10.192.47.8,RouterServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:16","headline":"无法ping通10.192.47.201，请检查TimeServer服务器网络状态","id":278730,"sid":1,"status":0,"text":"无法ping通10.192.47.201,TimeServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:15","headline":"无法ping通10.192.47.12，请检查MapServer服务器网络状态","id":278729,"sid":1,"status":0,"text":"无法ping通10.192.47.12,MapServer网络状态异常,请检修服务器网络！"}],"navigateFirstPage":1,"navigateLastPage":8,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"nextPage":2,"orderBy":null,"pageNum":1,"pageSize":12,"pages":23229,"prePage":0,"size":12,"startRow":1,"total":278738}
     * message : SUCCESS
     */

    private int code;
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

    public static class DataBean implements Serializable {
        /**
         * endRow : 12
         * firstPage : 1
         * hasNextPage : true
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : false
         * lastPage : 8
         * list : [{"createTime":"2021-03-23 10:22:16","headline":"运营后台服务异常","id":278740,"sid":1,"status":0,"text":"运营后台服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:22:01","headline":"物联网服务异常","id":278739,"sid":1,"status":0,"text":"物联网服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:21:46","headline":"帧同步服务异常","id":278738,"sid":1,"status":0,"text":"帧同步服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:21:31","headline":"文件上传服务异常","id":278737,"sid":1,"status":0,"text":"文件上传服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:21:16","headline":"云控后台服务无法连接","id":278736,"sid":1,"status":0,"text":"后台服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:20:40","headline":"文件传输服务异常","id":278735,"sid":1,"status":0,"text":"文件传输服务异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:20:19","headline":"无法ping通10.192.47.9，请检查MysqlServer服务器网络状态","id":278733,"sid":1,"status":0,"text":"无法ping通10.192.47.9,MysqlServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:19","headline":"前台服务故障","id":278734,"sid":1,"status":0,"text":"前台服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动"},{"createTime":"2021-03-23 10:20:18","headline":"无法ping通10.192.47.11，请检查FileTransferServer服务器网络状态","id":278732,"sid":1,"status":0,"text":"无法ping通10.192.47.11,FileTransferServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:17","headline":"无法ping通10.192.47.8，请检查RouterServer服务器网络状态","id":278731,"sid":1,"status":0,"text":"无法ping通10.192.47.8,RouterServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:16","headline":"无法ping通10.192.47.201，请检查TimeServer服务器网络状态","id":278730,"sid":1,"status":0,"text":"无法ping通10.192.47.201,TimeServer网络状态异常,请检修服务器网络！"},{"createTime":"2021-03-23 10:20:15","headline":"无法ping通10.192.47.12，请检查MapServer服务器网络状态","id":278729,"sid":1,"status":0,"text":"无法ping通10.192.47.12,MapServer网络状态异常,请检修服务器网络！"}]
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * nextPage : 2
         * orderBy : null
         * pageNum : 1
         * pageSize : 12
         * pages : 23229
         * prePage : 0
         * size : 12
         * startRow : 1
         * total : 278738
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
        private ArrayList<ListBean> list;
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

        public ArrayList<ListBean> getList() {
            return list;
        }

        public void setList(ArrayList<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean implements Serializable {
            /**
             * createTime : 2021-03-23 10:22:16
             * headline : 运营后台服务异常
             * id : 278740
             * sid : 1
             * status : 0
             * text : 运营后台服务出现异常。请检查服务器是否开机、或者服务端口号是否开启、或者防火墙是否开放该服务端口、或者服务是否已设置开机启动
             */

            private String createTime;
            private String headline;
            private int id;
            private int sid;
            private int status;
            private String text;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getHeadline() {
                return headline;
            }

            public void setHeadline(String headline) {
                this.headline = headline;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSid() {
                return sid;
            }

            public void setSid(int sid) {
                this.sid = sid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
