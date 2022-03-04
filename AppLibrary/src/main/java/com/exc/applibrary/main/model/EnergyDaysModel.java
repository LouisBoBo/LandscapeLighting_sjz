package com.exc.applibrary.main.model;

import java.util.List;

public class EnergyDaysModel {


    /**
     * code : 200
     * data : {"list":[{"historyData":[{"electricEnergy1":0,"time":"2021-02-25"},{"electricEnergy1":0,"time":"2021-02-26"},{"electricEnergy1":0,"time":"2021-02-27"},{"electricEnergy1":0,"time":"2021-02-28"},{"electricEnergy1":0,"time":"2021-03-01"},{"electricEnergy1":0,"time":"2021-03-02"},{"electricEnergy1":0,"time":"2021-03-03"}],"partitionId":12,"partitionName":"武昌区"}]}
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
         * historyData : [{"electricEnergy1":0,"time":"2021-02-25"},{"electricEnergy1":0,"time":"2021-02-26"},{"electricEnergy1":0,"time":"2021-02-27"},{"electricEnergy1":0,"time":"2021-02-28"},{"electricEnergy1":0,"time":"2021-03-01"},{"electricEnergy1":0,"time":"2021-03-02"},{"electricEnergy1":0,"time":"2021-03-03"}]
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
             * electricEnergy1 : 0.0
             * time : 2021-02-25
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
                private double electricEnergy1;
                private String time;

                public double getElectricEnergy1() {
                    return electricEnergy1;
                }

                public void setElectricEnergy1(double electricEnergy1) {
                    this.electricEnergy1 = electricEnergy1;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }
            }
        }
    }
}
