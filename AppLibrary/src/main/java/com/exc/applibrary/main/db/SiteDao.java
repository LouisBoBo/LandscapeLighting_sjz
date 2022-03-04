package com.exc.applibrary.main.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.exc.applibrary.main.model.PatitionFindListModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public interface SiteDao {
    @Insert(onConflict = REPLACE)
    void insert(PatitionFindListModel.DataBean.SiteListBean vo);

    @Insert(onConflict = REPLACE)
    void insert(List<PatitionFindListModel.DataBean.SiteListBean> vos);

    @Query("delete from site where id = :id")
    void deleteById(int id);

    @Query("delete from site")
    void deleteAll();

    //返回所有建筑
    @Query("select * from site order by id")
    List<PatitionFindListModel.DataBean.SiteListBean> query();

    //根据分区ID查出该分区下的建筑
    @Query("select * from site where partitionId = :partitionId order by id")
    List<PatitionFindListModel.DataBean.SiteListBean> query(int partitionId);

    //建筑模糊查询
    @Query("select * from site where name like '%' || :content || '%' order by id")
    List<PatitionFindListModel.DataBean.SiteListBean> query(String content);
}
