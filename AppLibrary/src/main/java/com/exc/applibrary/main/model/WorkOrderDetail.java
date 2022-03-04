package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;


public class WorkOrderDetail implements Serializable {

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

    public static class Data  implements Serializable{
        private String currentTime;
        private OrderNew orderNew;
        private List<?> faultStatisticalList;
        private List<OrderProcessList> orderProcessList;

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

        public OrderNew getOrderNew() {
            return orderNew;
        }

        public void setOrderNew(OrderNew orderNew) {
            this.orderNew = orderNew;
        }

        public List<?> getFaultStatisticalList() {
            return faultStatisticalList;
        }

        public void setFaultStatisticalList(List<?> faultStatisticalList) {
            this.faultStatisticalList = faultStatisticalList;
        }

        public List<OrderProcessList> getOrderProcessList() {
            return orderProcessList;
        }

        public void setOrderProcessList(List<OrderProcessList> orderProcessList) {
            this.orderProcessList = orderProcessList;
        }

        public static class OrderNew implements Serializable {
            private String addr;
            private int beforeStatus;
            private String createTime;
            private int creator;
            private String creatorName;
            private String description;
            private int faultTypeId;
            private String faultTypeName;
            private int id;
            private String handleTime;
            private String operatorName;
            private String expireTime;
            private int manager;
            private String managerName;
            private String name;
            private String operateTime;
            private int overtime;
            private int partitionId;
            private int operator;
            private String partitionName;
            private int statusId;
            private String statusName;
            private int urgeCount;
            private int version;
            private List<OrderPics> orderPics;

            public String getHandleTime() {
                return handleTime;
            }
            public void setHandleTime(String handleTime) {
                this.handleTime = handleTime;
            }

            public String getOperatorName() {
                return operatorName;
            }

            public void setOperatorName(String operatorName) {
                this.operatorName = operatorName;
            }


            public int getOperator() {
                return operator;
            }

            public void setOperator(int operator) {
                this.operator = operator;
            }



            public String getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(String expireTime) {
                this.expireTime = expireTime;
            }


            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public int getBeforeStatus() {
                return beforeStatus;
            }

            public void setBeforeStatus(int beforeStatus) {
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

            public int getOvertime() {
                return overtime;
            }

            public void setOvertime(int overtime) {
                this.overtime = overtime;
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

            public List<OrderPics> getOrderPics() {
                return orderPics;
            }

            public void setOrderPics(List<OrderPics> orderPics) {
                this.orderPics = orderPics;
            }

            public static class OrderPics implements Serializable{
                private int fileType;
                private String filename;
                private int id;
                private int orderId;
                private String realname;
                private boolean isVirtual;
                private boolean isXC;//是否是相册图片


                public boolean isXC() {
                    return isXC;
                }

                public void setXC(boolean XC) {
                    isXC = XC;
                }



                public boolean isVirtual() {
                    return isVirtual;
                }

                public void setVirtual(boolean virtual) {
                    isVirtual = virtual;
                }




                public int getFileType() {
                    return fileType;
                }

                public void setFileType(int fileType) {
                    this.fileType = fileType;
                }

                public String getFilename() {
                    return filename;
                }

                public void setFilename(String filename) {
                    this.filename = filename;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getOrderId() {
                    return orderId;
                }

                public void setOrderId(int orderId) {
                    this.orderId = orderId;
                }

                public String getRealname() {
                    return realname;
                }

                public void setRealname(String realname) {
                    this.realname = realname;
                }
            }
        }

        public static class OrderProcessList implements Serializable{
            private String ctime;
            private int id;
            private int oid;
            private int operator;
            private String operatorName;
            private OrderStatus orderStatus;
            private String remark;
            private int statusId;
            private int urgeCount;
            private int useTime;
            private List<?> orderPics;

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOid() {
                return oid;
            }

            public void setOid(int oid) {
                this.oid = oid;
            }

            public int getOperator() {
                return operator;
            }

            public void setOperator(int operator) {
                this.operator = operator;
            }

            public String getOperatorName() {
                return operatorName;
            }

            public void setOperatorName(String operatorName) {
                this.operatorName = operatorName;
            }

            public OrderStatus getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(OrderStatus orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getStatusId() {
                return statusId;
            }

            public void setStatusId(int statusId) {
                this.statusId = statusId;
            }

            public int getUrgeCount() {
                return urgeCount;
            }

            public void setUrgeCount(int urgeCount) {
                this.urgeCount = urgeCount;
            }

            public int getUseTime() {
                return useTime;
            }

            public void setUseTime(int useTime) {
                this.useTime = useTime;
            }

            public List<?> getOrderPics() {
                return orderPics;
            }

            public void setOrderPics(List<?> orderPics) {
                this.orderPics = orderPics;
            }

            public static class OrderStatus implements Serializable {
                private int id;
                private String name;

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
            }
        }
    }
}
