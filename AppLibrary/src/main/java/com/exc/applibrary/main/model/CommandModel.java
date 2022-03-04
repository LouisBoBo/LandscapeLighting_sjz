package com.exc.applibrary.main.model;

import java.util.List;

public class CommandModel {
    /**
     * code : 200
     * data : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":3,"list":[{"commandType":2,"content":"ct ZoneShowStart3\n","id":16,"name":"购物中心停止","protocol":{"communicationMode":1,"deviceTypeId":9,"id":9,"name":"总控室分区控制","port":1702,"respDeviceTypeVO":{"id":9,"name":"总控室音响系统","respDeviceVOList":[{"deviceTypeId":9,"id":6,"ip":"192.168.112.13","name":"总控室音响50i"}]}},"protocolId":9}],"navigateFirstPage":1,"navigateLastPage":3,"navigatePages":8,"navigatepageNums":[1,2,3],"nextPage":2,"orderBy":null,"pageNum":1,"pageSize":10,"pages":3,"prePage":0,"size":10,"startRow":1,"total":24}
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
     * lastPage : 3
     * list : [{"commandType":2,"content":"ct ZoneShowStart3\n","id":16,"name":"购物中心停止","protocol":{"communicationMode":1,"deviceTypeId":9,"id":9,"name":"总控室分区控制","port":1702,"respDeviceTypeVO":{"id":9,"name":"总控室音响系统","respDeviceVOList":[{"deviceTypeId":9,"id":6,"ip":"192.168.112.13","name":"总控室音响50i"}]}},"protocolId":9}]
     * navigateFirstPage : 1
     * navigateLastPage : 3
     * navigatePages : 8
     * navigatepageNums : [1,2,3]
     * nextPage : 2
     * orderBy : null
     * pageNum : 1
     * pageSize : 10
     * pages : 3
     * prePage : 0
     * size : 10
     * startRow : 1
     * total : 24
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
         * commandType : 2
         * content : ct ZoneShowStart3
         * id : 16
         * name : 购物中心停止
         * protocol : {"communicationMode":1,"deviceTypeId":9,"id":9,"name":"总控室分区控制","port":1702,"respDeviceTypeVO":{"id":9,"name":"总控室音响系统","respDeviceVOList":[{"deviceTypeId":9,"id":6,"ip":"192.168.112.13","name":"总控室音响50i"}]}}
         * protocolId : 9
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
            private int commandType;
            private String content;
            private int id;
            private String name;
            private boolean is_select;

            public boolean isIs_select() {
                return is_select;
            }

            public void setIs_select(boolean is_select) {
                this.is_select = is_select;
            }

            /**
             * communicationMode : 1
             * deviceTypeId : 9
             * id : 9
             * name : 总控室分区控制
             * port : 1702
             * respDeviceTypeVO : {"id":9,"name":"总控室音响系统","respDeviceVOList":[{"deviceTypeId":9,"id":6,"ip":"192.168.112.13","name":"总控室音响50i"}]}
             */

            private ProtocolBean protocol;
            private int protocolId;

            public int getCommandType() {
                return commandType;
            }

            public void setCommandType(int commandType) {
                this.commandType = commandType;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ProtocolBean getProtocol() {
                return protocol;
            }

            public void setProtocol(ProtocolBean protocol) {
                this.protocol = protocol;
            }

            public int getProtocolId() {
                return protocolId;
            }

            public void setProtocolId(int protocolId) {
                this.protocolId = protocolId;
            }

            public static class ProtocolBean {
                private int communicationMode;
                private int deviceTypeId;
                private int id;
                private String name;
                private int port;
                /**
                 * id : 9
                 * name : 总控室音响系统
                 * respDeviceVOList : [{"deviceTypeId":9,"id":6,"ip":"192.168.112.13","name":"总控室音响50i"}]
                 */

                private RespDeviceTypeVOBean respDeviceTypeVO;

                public int getCommunicationMode() {
                    return communicationMode;
                }

                public void setCommunicationMode(int communicationMode) {
                    this.communicationMode = communicationMode;
                }

                public int getDeviceTypeId() {
                    return deviceTypeId;
                }

                public void setDeviceTypeId(int deviceTypeId) {
                    this.deviceTypeId = deviceTypeId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPort() {
                    return port;
                }

                public void setPort(int port) {
                    this.port = port;
                }

                public RespDeviceTypeVOBean getRespDeviceTypeVO() {
                    return respDeviceTypeVO;
                }

                public void setRespDeviceTypeVO(RespDeviceTypeVOBean respDeviceTypeVO) {
                    this.respDeviceTypeVO = respDeviceTypeVO;
                }

                public static class RespDeviceTypeVOBean {
                    private int id;
                    private String name;
                    /**
                     * deviceTypeId : 9
                     * id : 6
                     * ip : 192.168.112.13
                     * name : 总控室音响50i
                     */

                    private List<RespDeviceVOListBean> respDeviceVOList;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public List<RespDeviceVOListBean> getRespDeviceVOList() {
                        return respDeviceVOList;
                    }

                    public void setRespDeviceVOList(List<RespDeviceVOListBean> respDeviceVOList) {
                        this.respDeviceVOList = respDeviceVOList;
                    }

                    public static class RespDeviceVOListBean {
                        private int deviceTypeId;
                        private int id;
                        private String ip;
                        private String name;

                        public int getDeviceTypeId() {
                            return deviceTypeId;
                        }

                        public void setDeviceTypeId(int deviceTypeId) {
                            this.deviceTypeId = deviceTypeId;
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

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }
        }
    }
}
