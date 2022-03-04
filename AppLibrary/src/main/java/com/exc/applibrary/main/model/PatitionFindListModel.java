package com.exc.applibrary.main.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

public class PatitionFindListModel implements Serializable {


    /**
     * code : 200
     * data : {"partitionList":[],"siteList":[{"buildings":[],"id":531,"name":"沌口经开万达","nodes":[],"pageNum":1,"pageSize":10,"partitionId":3}],"buildingList":[{"electricityNodes":[],"id":6,"masterNodes":[],"name":"沌口四标万达广场4号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":7,"masterNodes":[],"name":"沌口四标万达广场5号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":8,"masterNodes":[],"name":"沌口四标万达广场6号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":9,"masterNodes":[],"name":"沌口四标万达广场7号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":10,"masterNodes":[],"name":"沌口四标万达广场8号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":11,"masterNodes":[],"name":"沌口四标万达广场9号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":220,"masterNodes":[],"name":"沌口四标万达广场3号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":343,"masterNodes":[],"name":"沌口四标万达广场2号楼","nodes":[],"partitionId":3,"sites":[]},{"electricityNodes":[],"id":2739,"masterNodes":[],"name":"江夏联投广场万达影城1#","nodes":[],"partitionId":27,"sites":[]},{"electricityNodes":[],"id":3405,"masterNodes":[],"name":"武昌万达御湖世家1","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3406,"masterNodes":[],"name":"武昌万达御湖世家2","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3407,"masterNodes":[],"name":"武昌万达御湖世家3","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3408,"masterNodes":[],"name":"武昌万达御湖世家4","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3409,"masterNodes":[],"name":"武昌万达御湖世家5","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3410,"masterNodes":[],"name":"武昌万达御湖世家6","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3411,"masterNodes":[],"name":"武昌万达御湖世家7","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3412,"masterNodes":[],"name":"武昌万达御湖世家8","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3413,"masterNodes":[],"name":"武昌万达御湖世家9","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3414,"masterNodes":[],"name":"武昌万达御湖世家10","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3415,"masterNodes":[],"name":"武昌万达御湖世家11","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3416,"masterNodes":[],"name":"武昌万达御湖世家12","nodes":[],"partitionId":12,"sites":[]},{"electricityNodes":[],"id":3417,"masterNodes":[],"name":"武昌万达电影乐园","nodes":[],"partitionId":12,"sites":[]}]}
     * message : SUCCESS!
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
        private List<PartitionListBean> partitionList;
        /**
         * buildings : []
         * id : 531
         * name : 沌口经开万达
         * nodes : []
         * pageNum : 1
         * pageSize : 10
         * partitionId : 3
         */

        private List<SiteListBean> siteList;
        /**
         * electricityNodes : []
         * id : 6
         * masterNodes : []
         * name : 沌口四标万达广场4号楼
         * nodes : []
         * partitionId : 3
         * sites : []
         */

        private List<BuildingListBean> buildingList;

        public List<PartitionListBean> getPartitionList() {
            return partitionList;
        }

        public void setPartitionList(List<PartitionListBean> partitionList) {
            this.partitionList = partitionList;
        }

        public List<SiteListBean> getSiteList() {
            return siteList;
        }

        public void setSiteList(List<SiteListBean> siteList) {
            this.siteList = siteList;
        }

        public List<BuildingListBean> getBuildingList() {
            return buildingList;
        }

        public void setBuildingList(List<BuildingListBean> buildingList) {
            this.buildingList = buildingList;
        }

        @Entity(tableName = "partition")
        public static class PartitionListBean implements Serializable{
            @PrimaryKey
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

        @Entity(tableName = "site")
        public static class SiteListBean implements Serializable{
            @PrimaryKey
            private int id;
            private String name;
            private int partitionId;

            @Ignore
            private int pageNum;
            private int pageSize;


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

            public int getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }

        }

        @Entity(tableName = "building")
        public static class BuildingListBean implements Serializable{
            @PrimaryKey
            private int id;
            private String name;
            private int partitionId;

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

            public int getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }
        }
    }
}
