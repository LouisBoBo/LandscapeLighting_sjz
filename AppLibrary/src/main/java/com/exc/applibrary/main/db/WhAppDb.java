package com.exc.applibrary.main.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.PatitionFindListModel;

@Database(entities = {PatitionFindListModel.DataBean.BuildingListBean.class,PatitionFindListModel.DataBean.SiteListBean.class,PatitionFindListModel.DataBean.PartitionListBean.class, ElectricityNodeModel.DataBean.ListBean.class}, version = 1, exportSchema = false)
public abstract class WhAppDb extends RoomDatabase {
    private static WhAppDb instance;

    public synchronized static WhAppDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, WhAppDb.class, "wuhan_db").allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract BuildingDao getBuildingDao();
    public abstract SiteDao getSiteDao();
    public abstract PartitionDao getPartitionDao();
    public abstract ElectricityDao getElectricityDao();
}
