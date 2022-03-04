package com.exc.applibrary.main.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Building {
    @PrimaryKey
    private int id;
    private int partitionId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
