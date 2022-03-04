package com.exc.applibrary.main.monitoring;

import java.io.Serializable;
import java.util.List;

public class MonitoringListData implements Serializable {
    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean implements Serializable {
            private String cameraIndexCode;
            private int altitude;
            private String cameraName;
            private int cameraType;
            private String cameraTypeName;
            private String capabilitySet;
            private String channelNo;
            private String channelType;
            private String channelTypeName;
            private String createTime;
            private String encodeDevIndexCode;
            private String regionIndexCode;
            private int transType;
            private String transTypeName;
            private String updateTime;
            private String latitude;
            private String longitude;
            private boolean isSelect;


            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }




            public String getCameraIndexCode() {
                return cameraIndexCode;
            }

            public void setCameraIndexCode(String cameraIndexCode) {
                this.cameraIndexCode = cameraIndexCode;
            }

            public int getAltitude() {
                return altitude;
            }

            public void setAltitude(int altitude) {
                this.altitude = altitude;
            }

            public String getCameraName() {
                return cameraName;
            }

            public void setCameraName(String cameraName) {
                this.cameraName = cameraName;
            }

            public int getCameraType() {
                return cameraType;
            }

            public void setCameraType(int cameraType) {
                this.cameraType = cameraType;
            }

            public String getCameraTypeName() {
                return cameraTypeName;
            }

            public void setCameraTypeName(String cameraTypeName) {
                this.cameraTypeName = cameraTypeName;
            }

            public String getCapabilitySet() {
                return capabilitySet;
            }

            public void setCapabilitySet(String capabilitySet) {
                this.capabilitySet = capabilitySet;
            }

            public String getChannelNo() {
                return channelNo;
            }

            public void setChannelNo(String channelNo) {
                this.channelNo = channelNo;
            }

            public String getChannelType() {
                return channelType;
            }

            public void setChannelType(String channelType) {
                this.channelType = channelType;
            }

            public String getChannelTypeName() {
                return channelTypeName;
            }

            public void setChannelTypeName(String channelTypeName) {
                this.channelTypeName = channelTypeName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEncodeDevIndexCode() {
                return encodeDevIndexCode;
            }

            public void setEncodeDevIndexCode(String encodeDevIndexCode) {
                this.encodeDevIndexCode = encodeDevIndexCode;
            }

            public String getRegionIndexCode() {
                return regionIndexCode;
            }

            public void setRegionIndexCode(String regionIndexCode) {
                this.regionIndexCode = regionIndexCode;
            }

            public int getTransType() {
                return transType;
            }

            public void setTransType(int transType) {
                this.transType = transType;
            }

            public String getTransTypeName() {
                return transTypeName;
            }

            public void setTransTypeName(String transTypeName) {
                this.transTypeName = transTypeName;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
        }
    }
}
