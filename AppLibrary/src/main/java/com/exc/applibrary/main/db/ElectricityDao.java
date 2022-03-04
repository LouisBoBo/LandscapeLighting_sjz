package com.exc.applibrary.main.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.PatitionFindListModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ElectricityDao {
    @Insert(onConflict = REPLACE)
    void insert(ElectricityNodeModel.DataBean.ListBean vo);

    @Insert(onConflict = REPLACE)
    void insert(List<ElectricityNodeModel.DataBean.ListBean> vos);

    @Query("delete from electricity where id = :id")
    void deleteById(int id);

    @Query("delete from electricity")
    void deleteAll();

    //返回所有节点
    @Query("select * from electricity order by id")
    List<ElectricityNodeModel.DataBean.ListBean> query();

    //根据分区ID查出该分区下的建筑
    @Query("select * from electricity where id = :id order by id")
    List<ElectricityNodeModel.DataBean.ListBean> query(int id);

    //建筑模糊查询
    @Query("select * from electricity where name like '%' || :content || '%' order by id")
    List<ElectricityNodeModel.DataBean.ListBean> query(String content);
}