package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoopList implements Serializable {
    /**
     * code : 200
     * data : {"endRow":15,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":8,"list":[{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":1,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3637,"isEdit":0,"name":"交流接触器模块,通道1","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":161,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":2,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3638,"isEdit":0,"name":"交流接触器模块,通道2","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":162,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":3,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3639,"isEdit":0,"name":"交流接触器模块,通道3","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":163,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":4,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3640,"isEdit":0,"name":"交流接触器模块,通道4","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":164,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":5,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3641,"isEdit":0,"name":"交流接触器模块,通道5","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":165,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":6,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3642,"isEdit":0,"name":"交流接触器模块,通道6","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":166,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":7,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3643,"isEdit":0,"name":"交流接触器模块,通道7","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":167,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":8,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3644,"isEdit":0,"name":"交流接触器模块,通道8","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":168,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":9,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3645,"isEdit":0,"name":"交流接触器模块,通道9","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":169,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":10,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3646,"isEdit":0,"name":"交流接触器模块,通道10","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":170,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":11,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3647,"isEdit":0,"name":"交流接触器模块,通道11","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":171,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":12,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3648,"isEdit":0,"name":"交流接触器模块,通道12","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":172,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-10","canIndex":null,"channelTypeName":"交流接触器回路","controlId":1,"currentValue":0,"deviceAddress":2,"did":206,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:58","id":3649,"isEdit":0,"name":"交流接触器模块,通道1","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":173,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-10","canIndex":null,"channelTypeName":"交流接触器回路","controlId":2,"currentValue":0,"deviceAddress":2,"did":206,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:58","id":3650,"isEdit":0,"name":"交流接触器模块,通道2","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":174,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-10","canIndex":null,"channelTypeName":"交流接触器回路","controlId":3,"currentValue":0,"deviceAddress":2,"did":206,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:58","id":3651,"isEdit":0,"name":"交流接触器模块,通道3","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":175,"value":0}],"navigateFirstPage":1,"navigateLastPage":8,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"nextPage":2,"orderBy":null,"pageNum":1,"pageSize":15,"pages":11,"prePage":0,"size":15,"startRow":1,"total":164}
     * message : SUCCESS
     * time : null
     */

    public int code;
    public DataBean data;
    public String message;
    public Object time;

    public static class DataBean implements Serializable {
        /**
         * endRow : 15
         * firstPage : 1
         * hasNextPage : true
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : false
         * lastPage : 8
         * list : [{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":1,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3637,"isEdit":0,"name":"交流接触器模块,通道1","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":161,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":2,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3638,"isEdit":0,"name":"交流接触器模块,通道2","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":162,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":3,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3639,"isEdit":0,"name":"交流接触器模块,通道3","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":163,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":4,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3640,"isEdit":0,"name":"交流接触器模块,通道4","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":164,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":5,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3641,"isEdit":0,"name":"交流接触器模块,通道5","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":165,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":6,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3642,"isEdit":0,"name":"交流接触器模块,通道6","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":166,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":7,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3643,"isEdit":0,"name":"交流接触器模块,通道7","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":167,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":8,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3644,"isEdit":0,"name":"交流接触器模块,通道8","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":168,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":9,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3645,"isEdit":0,"name":"交流接触器模块,通道9","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":169,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":10,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3646,"isEdit":0,"name":"交流接触器模块,通道10","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":170,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":11,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3647,"isEdit":0,"name":"交流接触器模块,通道11","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":171,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-9","canIndex":null,"channelTypeName":"交流接触器回路","controlId":12,"currentValue":0,"deviceAddress":1,"did":205,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:57","id":3648,"isEdit":0,"name":"交流接触器模块,通道12","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":172,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-10","canIndex":null,"channelTypeName":"交流接触器回路","controlId":1,"currentValue":0,"deviceAddress":2,"did":206,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:58","id":3649,"isEdit":0,"name":"交流接触器模块,通道1","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":173,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-10","canIndex":null,"channelTypeName":"交流接触器回路","controlId":2,"currentValue":0,"deviceAddress":2,"did":206,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:58","id":3650,"isEdit":0,"name":"交流接触器模块,通道2","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":174,"value":0},{"buildingName":null,"canChannelTypeId":12,"canDeviceName":"12路交流接触器模块-10","canIndex":null,"channelTypeName":"交流接触器回路","controlId":3,"currentValue":0,"deviceAddress":2,"did":206,"dsn":14,"electricityUpdateTime":"2021-04-14 11:07:58","id":3651,"isEdit":0,"name":"交流接触器模块,通道3","nid":2,"nodeName":"jiancang6lou","nodeNum":null,"nodeStatus":null,"sid":4,"tagId":175,"value":0}]
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * nextPage : 2
         * orderBy : null
         * pageNum : 1
         * pageSize : 15
         * pages : 11
         * prePage : 0
         * size : 15
         * startRow : 1
         * total : 164
         */

        public int endRow;
        public int firstPage;
        public boolean hasNextPage;
        public boolean hasPreviousPage;
        public boolean isFirstPage;
        public boolean isLastPage;
        public int lastPage;
        public int navigateFirstPage;
        public int navigateLastPage;
        public int navigatePages;
        public int nextPage;
        public Object orderBy;
        public int pageNum;
        public int pageSize;
        public int pages;
        public int prePage;
        public int size;
        public int startRow;
        public int total;
        public ArrayList<ListBean> list;
        public ArrayList<Integer> navigatepageNums;

        public static class ListBean implements Serializable {
            /**
             * buildingName : null
             * canChannelTypeId : 12
             * canDeviceName : 12路交流接触器模块-9
             * canIndex : null
             * channelTypeName : 交流接触器回路
             * controlId : 1
             * currentValue : 0
             * deviceAddress : 1
             * did : 205
             * dsn : 14
             * electricityUpdateTime : 2021-04-14 11:07:57
             * id : 3637
             * isEdit : 0
             * name : 交流接触器模块,通道1
             * nid : 2
             * nodeName : jiancang6lou
             * nodeNum : null
             * nodeStatus : null
             * sid : 4
             * tagId : 161
             * value : 0
             */

            public Object buildingName;
            public int canChannelTypeId;
            public String canDeviceName;
            public Object canIndex;
            public String channelTypeName;
            public int controlId;
            public int currentValue;
            public String deviceAddress;
            public int did;
            public String dsn;
            public String electricityUpdateTime;
            public int id;
            public int isEdit;
            public String name;
            public int nid;
            public String nodeName;
            public Object nodeNum;
            public Object nodeStatus;
            public int sid;
            public int tagId;
            public int value;
        }
    }
}
