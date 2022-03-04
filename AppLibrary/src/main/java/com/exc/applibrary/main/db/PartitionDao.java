package com.exc.applibrary.main.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.exc.applibrary.main.model.PatitionFindListModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PartitionDao {
    @Insert(onConflict = REPLACE)
    void insert(PatitionFindListModel.DataBean.PartitionListBean vo);

    @Insert(onConflict = REPLACE)
    void insert(List<PatitionFindListModel.DataBean.PartitionListBean> vos);

    @Query("delete from partition where id = :id")
    void deleteById(int id);

    @Query("delete from partition")
    void deleteAll();

    //返回所有建筑
    @Query("select * from partition order by id")
    List<PatitionFindListModel.DataBean.PartitionListBean> query();

    //根据分区ID查出该分区下的建筑
    @Query("select * from partition where id = :id order by id")
    List<PatitionFindListModel.DataBean.PartitionListBean> query(int id);

    //建筑模糊查询
    @Query("select * from partition where name like '%' || :content || '%' order by id")
    List<PatitionFindListModel.DataBean.PartitionListBean> query(String content);
}
