package com.exc.applibrary.main.model;

import java.util.List;

public class OnlineDaysModel {


    /**
     * code : 200
     * data : {"list":[{"historyData":[{"time":"2021-02-25","onlineRate":0},{"time":"2021-02-26","onlineRate":0},{"time":"2021-02-27","onlineRate":0},{"time":"2021-02-28","onlineRate":0},{"time":"2021-03-01","onlineRate":0},{"time":"2021-03-02","onlineRate":0},{"time":"2021-03-03","onlineRate":0}],"partitionId":12,"partitionName":"武昌区"}]}
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

    public static class DataBean {
        /**
         * historyData : [{"time":"2021-02-25","onlineRate":0},{"time":"2021-02-26","onlineRate":0},{"time":"2021-02-27","onlineRate":0},{"time":"2021-02-28","onlineRate":0},{"time":"2021-03-01","onlineRate":0},{"time":"2021-03-02","onlineRate":0},{"time":"2021-03-03","onlineRate":0}]
         * partitionId : 12
         * partitionName : 武昌区
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int partitionId;
            private String partitionName;
            /**
             * time : 2021-02-25
             * onlineRate : 0
             */

            private List<HistoryDataBean> historyData;

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

            public List<HistoryDataBean> getHistoryData() {
                return historyData;
            }

            public void setHistoryData(List<HistoryDataBean> historyData) {
                this.historyData = historyData;
            }

            public static class HistoryDataBean {
                private String time;
                private int onlineRate;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getOnlineRate() {
                    return onlineRate;
                }

                public void setOnlineRate(int onlineRate) {
                    this.onlineRate = onlineRate;
                }
            }
        }
    }
}
