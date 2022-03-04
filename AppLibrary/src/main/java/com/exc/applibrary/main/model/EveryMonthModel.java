package com.exc.applibrary.main.model;

import java.util.List;

public class EveryMonthModel {

    /**
     * code : 200
     * data : {"date":null,"dates":["1","3"],"energies":[172.6999969482422,0],"partitionId":1,"totalEnergy":172.6999969482422}
     * message : SUCCESS
     */

    private int code;
    /**
     * date : null
     * dates : ["1","3"]
     * energies : [172.6999969482422,0]
     * partitionId : 1
     * totalEnergy : 172.6999969482422
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
        private Object date;
        private int partitionId;
        private double totalEnergy;
        private List<String> dates;
        private List<Double> energies;

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public int getPartitionId() {
            return partitionId;
        }

        public void setPartitionId(int partitionId) {
            this.partitionId = partitionId;
        }

        public double getTotalEnergy() {
            return totalEnergy;
        }

        public void setTotalEnergy(double totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

        public List<String> getDates() {
            return dates;
        }

        public void setDates(List<String> dates) {
            this.dates = dates;
        }

        public List<Double> getEnergies() {
            return energies;
        }

        public void setEnergies(List<Double> energies) {
            this.energies = energies;
        }
    }
}
