package com.exc.applibrary.main.model;

public class SelectBuildModelScene {
    private int partition_id;
    private int site_id;
    private int build_id;
    private int resultCode;
    private String partition_name;
    private String site_name;
    private String build_name;
    private int select_data_style = 0; //1本页返回 2搜索返回 3搜索更多返回

    public int getPartition_id() {
        return partition_id;
    }

    public void setPartition_id(int partition_id) {
        this.partition_id = partition_id;
    }

    public int getSelect_data_style() {
        return select_data_style;
    }

    public void setSelect_data_style(int select_data_style) {
        this.select_data_style = select_data_style;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public int getBuild_id() {
        return build_id;
    }

    public void setBuild_id(int build_id) {
        this.build_id = build_id;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getPartition_name() {
        return partition_name;
    }

    public void setPartition_name(String partition_name) {
        this.partition_name = partition_name;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getBuild_name() {
        return build_name;
    }

    public void setBuild_name(String build_name) {
        this.build_name = build_name;
    }
}
