package com.exc.applibrary.main.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.PatitionFindListModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.VH> {

    private class User {
        private String name;
        private int id;
        private int partitionId;
        private boolean isselect;

        public User(String title, int id, int partitionId, boolean isselect) {
            this.name = title;
            this.id = id;
            this.partitionId = partitionId;
            this.isselect = isselect;
        }

        public String getTitle() {
            return name;
        }

        public void setTitle(String title) {
            this.name = title;
        }

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

        public boolean isIsselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }
    }

    private List<User> pub_userList = new ArrayList<>();
    private List<User> userList1 = new ArrayList<>();
    private List<User> userList2 = new ArrayList<>();
    private List<User> userList3 = new ArrayList<>();
    private List<User> userList4 = new ArrayList<>();
    private int recycle_style;//1分区 2站点 3建筑

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemSiteClick(String select_text , int index);//建筑
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_searchresult_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if(pub_userList.size() >=2){
            if(holder.getAdapterPosition() ==2){
                holder.itemView.findViewById(R.id.bottom_line).setVisibility(View.INVISIBLE);
            }else {
                holder.itemView.findViewById(R.id.bottom_line).setVisibility(View.VISIBLE);
            }
        }else {
            if(holder.getAdapterPosition() == pub_userList.size()-1){
                holder.itemView.findViewById(R.id.bottom_line).setVisibility(View.INVISIBLE);
            }else {
                holder.itemView.findViewById(R.id.bottom_line).setVisibility(View.VISIBLE);
            }
        }

        if (pub_userList.get(holder.getAdapterPosition()).isselect) {
            holder.itemView.findViewById(R.id.tv_select).setBackgroundResource(R.drawable.background_btn_selector);
        } else {
            holder.itemView.findViewById(R.id.tv_select).setBackgroundResource(R.color.alph);
        }
        holder.context.setText(pub_userList.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            //item 点击事件
            public void onClick(View v) {
                itemHolderClick(holder);
            }
        });
    }

    @SuppressLint("ResourceType")
    public void itemHolderClick(VH holder) {
        for (int i = 0; i < pub_userList.size(); i++) {
            if (holder.getAdapterPosition() == i) {
                pub_userList.get(holder.getAdapterPosition()).setIsselect(true);
            } else {
                pub_userList.get(i).setIsselect(false);
            }
        }
        onItemClickListener.onItemSiteClick(pub_userList.get(holder.getAdapterPosition()).name , holder.getAdapterPosition());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pub_userList.size()>3?3:pub_userList.size();
    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public TextView context;

        public VH(View v) {
            super(v);
            context = v.findViewById(R.id.tv_content);
        }
    }

    public void refreshPartitionUI(List<PatitionFindListModel.DataBean.PartitionListBean>listBeans){
        userList1 = new ArrayList<>();
        for (int i=0; i<listBeans.size();i++){
            PatitionFindListModel.DataBean.PartitionListBean partitionListBean = listBeans.get(i);
            User user = new User(partitionListBean.getName(),partitionListBean.getId(),partitionListBean.getId(),false);
            userList1.add(user);
        }
        pub_userList = userList1;
        notifyDataSetChanged();
    }
    public void refreshSiteUI(List<PatitionFindListModel.DataBean.SiteListBean> listBeans) {
        userList2 = new ArrayList<>();
        for (int i=0; i<listBeans.size();i++){
            PatitionFindListModel.DataBean.SiteListBean siteListBean = listBeans.get(i);
            User user = new User(siteListBean.getName(),siteListBean.getId(),siteListBean.getPartitionId(),false);
            userList2.add(user);
        }
        pub_userList = userList2;
        notifyDataSetChanged();
    }

    public void refreshBindingUI(List<PatitionFindListModel.DataBean.BuildingListBean> listBeans) {
        userList3 = new ArrayList<>();
        for (int i=0; i<listBeans.size();i++){
            PatitionFindListModel.DataBean.BuildingListBean buildingListBean = listBeans.get(i);
            User user = new User(buildingListBean.getName(),buildingListBean.getId(),buildingListBean.getPartitionId(),false);
            userList3.add(user);
        }
        pub_userList = userList3;
        notifyDataSetChanged();
    }

    public void refreshNodeUI(List<ElectricityNodeModel.DataBean.ListBean> listBeans){
        userList4 = new ArrayList<>();
        for (int i=0; i<listBeans.size();i++){
            ElectricityNodeModel.DataBean.ListBean listBean = listBeans.get(i);
            User user = new User(listBean.getName(),listBean.getId(),0,false);
            userList4.add(user);
        }
        pub_userList = userList4;
        notifyDataSetChanged();
    }
}
