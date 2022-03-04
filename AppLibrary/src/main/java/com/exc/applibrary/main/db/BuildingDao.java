package com.exc.applibrary.main.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.exc.applibrary.main.model.PatitionFindListModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BuildingDao {

    @Insert(onConflict = REPLACE)
    void insert(PatitionFindListModel.DataBean.BuildingListBean vo);

    @Insert(onConflict = REPLACE)
    void insert(List<PatitionFindListModel.DataBean.BuildingListBean> vos);

    @Query("delete from building where id = :id")
    void deleteById(int id);

    @Query("delete from building")
    void deleteAll();

    //返回所有建筑
    @Query("select * from building order by id")
    List<PatitionFindListModel.DataBean.BuildingListBean> query();

    //根据分区ID查出该分区下的建筑
    @Query("select * from building where partitionId = :partitionId order by id")
    List<PatitionFindListModel.DataBean.BuildingListBean> query(int partitionId);

    //建筑模糊查询
    @Query("select * from building where name like '%' || :content || '%' order by id")
    List<PatitionFindListModel.DataBean.BuildingListBean> query(String content);
}
