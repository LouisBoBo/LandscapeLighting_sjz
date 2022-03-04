package com.exc.applibrary.main.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.model.PartitionModel;

import java.util.List;

public class BuildRecycleAdapter extends RecyclerView.Adapter<BuildRecycleAdapter.VH> {
    private List<PartitionModel.DataBean.SiteListBean> siteList;

    public void setmDatas(PartitionModel.DataBean bean) {

        siteList = bean.getSiteList();
        for (int i =0 ;i<siteList.size();i++){
            siteList.get(i).setIsselect(false);
        }

        notifyDataSetChanged();
    }

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemSiteClick(String select_text , int site_id,int partition_id);//建筑
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_build_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        if (siteList.get(holder.getAdapterPosition()).getIsselect()) {
            holder.itemView.setBackgroundResource(R.drawable.background_build_selector);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.background_build_unselector);
        }
        holder.context.setText(siteList.get(position).getName());
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
        Log.i("sjfasj", "点击了");
        for (int i = 0; i < siteList.size(); i++) {
            if (holder.getAdapterPosition() == i) {
                siteList.get(holder.getAdapterPosition()).setIsselect(true);
            } else {
                siteList.get(i).setIsselect(false);
            }
        }
        onItemClickListener.onItemSiteClick(siteList.get(holder.getAdapterPosition()).getName(),siteList.get(holder.getAdapterPosition()).getId(),siteList.get(holder.getAdapterPosition()).getPartitionId());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return siteList.size()>0?siteList.size():0;
    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public TextView context;
        public VH(View v) {
            super(v);
            context = v.findViewById(R.id.tv_content);
        }
    }

    public void refreshUI(PartitionModel.DataBean bean) {
        siteList = bean.getSiteList();
        for (int i =0 ;i<siteList.size();i++){
            siteList.get(i).setIsselect(false);
        }

        notifyDataSetChanged();
    }

}