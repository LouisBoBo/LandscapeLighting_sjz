package com.exc.applibrary.main.model;

import java.util.List;

public class SceneBatchXiaFaRequestBean {
    private List<TimerVOS> timerVOS;

    public List<TimerVOS> getTimerVOS() {
        return timerVOS;
    }

    public void setTimerVOS(List<TimerVOS> timerVOS) {
        this.timerVOS = timerVOS;
    }

    public static class TimerVOS {
        private int nid;
        private int type;
        private List<TimingParameters> timingParameters;

        public int getNid() {
            return nid;
        }

        public void setNid(int nid) {
            this.nid = nid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<TimingParameters> getTimingParameters() {
            return timingParameters;
        }

        public void setTimingParameters(List<TimingParameters> timingParameters) {
            this.timingParameters = timingParameters;
        }

        public static class TimingParameters {
            private int type;
            private String beginDate;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            private String time;

            public int getIsExecute() {
                return isExecute;
            }

            public void setIsExecute(int isExecute) {
                this.isExecute = isExecute;
            }

            private int isExecute;

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            private String endDate;
            private int tagId;
            private List<Integer> cycleTypes;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBeginDate() {
                return beginDate;
            }

            public void setBeginDate(String beginDate) {
                this.beginDate = beginDate;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
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
