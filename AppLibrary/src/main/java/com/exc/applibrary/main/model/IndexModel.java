package com.exc.applibrary.main.model;

public class IndexModel {
    public String topc;
    public String name;
    public int id;
    public int partitionid;
    public Boolean is_select;

    public IndexModel(String name,int id ,int partitionid) {
        this.name = name;
        this.id = id;
        this.partitionid = partitionid;
    }
}