package com.exc.applibrary.main.engine;

import com.exc.applibrary.main.customview.CharacterParser;
import com.exc.applibrary.main.customview.PinyinComparator;
import com.exc.applibrary.main.model.CategoryModel;
import com.exc.applibrary.main.model.ChatModel;
import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.GoodsModel;
import com.exc.applibrary.main.model.IndexModel;
import com.exc.applibrary.main.model.PatitionFindListModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/26 上午1:03
 * 描述:
 */
public class DataEngine {
    public static List<IndexModel> loadPartitionIndexModelData(List<PatitionFindListModel.DataBean.PartitionListBean> partitionListBeans) {
        List<IndexModel> data = new ArrayList<>();
        for (PatitionFindListModel.DataBean.PartitionListBean buildingListBean : partitionListBeans){
            data.add(new IndexModel(buildingListBean.getName(),buildingListBean.getId(),buildingListBean.getId()));
        }

        PinyinComparator pinyinComparator = new PinyinComparator();
        CharacterParser characterParser = CharacterParser.getInstance();
        for (IndexModel indexModel : data) {
            indexModel.is_select = false;
            indexModel.topc = characterParser.getSelling(indexModel.name).substring(0, 1).toUpperCase();
            if (indexModel.name.equals("重庆")) {
                indexModel.topc = "C";
            }
        }
        Collections.sort(data, pinyinComparator);
        return data;
    }
    public static List<IndexModel> loadIndexModelData(List<PatitionFindListModel.DataBean.BuildingListBean> buildingListBeans) {
        List<IndexModel> data = new ArrayList<>();
        for (PatitionFindListModel.DataBean.BuildingListBean buildingListBean : buildingListBeans){
            data.add(new IndexModel(buildingListBean.getName(),buildingListBean.getId(),buildingListBean.getPartitionId()));
        }

        PinyinComparator pinyinComparator = new PinyinComparator();
        CharacterParser characterParser = CharacterParser.getInstance();
        for (IndexModel indexModel : data) {
            indexModel.is_select = false;
            indexModel.topc = characterParser.getSelling(indexModel.name).substring(0, 1).toUpperCase();
            if (indexModel.name.equals("重庆")) {
                indexModel.topc = "C";
            }
        }
        Collections.sort(data, pinyinComparator);
        return data;
    }

    public static List<IndexModel> loadsiteIndexModelData(List<PatitionFindListModel.DataBean.SiteListBean> siteListBeans) {
        List<IndexModel> data = new ArrayList<>();
        for (PatitionFindListModel.DataBean.SiteListBean siteListBean : siteListBeans){
            data.add(new IndexModel(siteListBean.getName(),siteListBean.getId(),siteListBean.getPartitionId()));
        }

        PinyinComparator pinyinComparator = new PinyinComparator();
        CharacterParser characterParser = CharacterParser.getInstance();
        for (IndexModel indexModel : data) {
            indexModel.is_select = false;
            indexModel.topc = characterParser.getSelling(indexModel.name).substring(0, 1).toUpperCase();
            if (indexModel.name.equals("重庆")) {
                indexModel.topc = "C";
            }
        }
        Collections.sort(data, pinyinComparator);
        return data;
    }

    public static List<IndexModel> loadnodeIndexModelData(List<ElectricityNodeModel.DataBean.ListBean> listBeans) {
        List<IndexModel> data = new ArrayList<>();
        for (ElectricityNodeModel.DataBean.ListBean listBean : listBeans){
            data.add(new IndexModel(listBean.getName(),listBean.getId(),0));
        }

        PinyinComparator pinyinComparator = new PinyinComparator();
        CharacterParser characterParser = CharacterParser.getInstance();
        for (IndexModel indexModel : data) {
            indexModel.is_select = false;
            indexModel.topc = characterParser.getSelling(indexModel.name).substring(0, 1).toUpperCase();
            if (indexModel.name.equals("重庆")) {
                indexModel.topc = "C";
            }
        }
        Collections.sort(data, pinyinComparator);
        return data;
    }

    public static List<ChatModel> loadChatModelData() {
        List<ChatModel> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 3 != 0) {
                data.add(new ChatModel("消息" + i, ChatModel.UserType.Me, i % 2 == 0 ? ChatModel.SendStatus.Success : ChatModel.SendStatus.Failure));
            } else {
                data.add(new ChatModel("消息" + i, ChatModel.UserType.Other, null));
            }
        }
        return data;
    }

    public static List<CategoryModel> loadCategoryData() {
        List<CategoryModel> categoryModelList = new ArrayList<>();
        CategoryModel categoryModel;
        int categoryCount = 20;
        for (int i = 0; i < categoryCount; i++) {
            categoryModel = new CategoryModel(i, "分类" + i);
            categoryModel.goodsModelList = new ArrayList<>();

            int goodsCount = categoryCount % (i + 1) + 1;

            for (int j = 0; j < goodsCount; j++) {
                categoryModel.goodsModelList.add(new GoodsModel(j, "商品" + i + j, i));
            }

            categoryModelList.add(categoryModel);
        }
        return categoryModelList;
    }
}