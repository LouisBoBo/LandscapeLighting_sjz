package com.exc.applibrary.main.model;

import java.util.List;

public class StrategyDetailModel {


    /**
     * code : 200
     * data : {"cycleTypes":[1,2,3,4,5,6,7],"description":"333","endDate":null,"id":6,"mode":1,"name":"333","pointInfos":[{"commandName":"总控室全区模式","description":"333","deviceName":"总控室音响50i","id":5,"startTime":"18:55:30"}],"startDate":"","status":0}
     * message : SUCCESS
     */

    private int code;
    /**
     * cycleTypes : [1,2,3,4,5,6,7]
     * description : 333
     * endDate : null
     * id : 6
     * mode : 1
     * name : 333
     * pointInfos : [{"commandName":"总控室全区模式","description":"333","deviceName":"总控室音响50i","id":5,"startTime":"18:55:30"}]
     * startDate :
     * status : 0
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
        private String description;
        private Object endDate;
        private int id;
        private int mode;
        private String name;
        private String startDate;
        private int status;
        private List<Integer> cycleTypes;
        /**
         * commandName : 总控室全区模式
         * description : 333
         * deviceName : 总控室音响50i
         * id : 5
         * startTime : 18:55:30
         */

        private List<PointInfosBean> pointInfos;

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

        public List<PointInfosBean> getPointInfos() {
            return pointInfos;
        }

        public void setPointInfos(List<PointInfosBean> pointInfos) {
            this.pointInfos = pointInfos;
        }

        public static class PointInfosBean {
            private String commandName;
            private String description;
            private String deviceName;
            private int id;
            private String startTime;

            public String getCommandName() {
                return commandName;
            }

            public void setCommandName(String commandName) {
                this.commandName = commandName;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }
    }
}
