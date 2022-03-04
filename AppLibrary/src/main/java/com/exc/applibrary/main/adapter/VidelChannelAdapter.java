package com.exc.applibrary.main.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.model.BuildingDetailModel;

import java.util.ArrayList;
import java.util.List;

public class VidelChannelAdapter extends RecyclerView.Adapter<VidelChannelAdapter.VH> {
    private List<BuildingDetailModel.DataBean.NodeListBean> dataList;
    private BuildingDetailModel model;

    private VidelChannelAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemSiteClick(BuildingDetailModel.DataBean.NodeListBean bean, int video_style);//建筑 video 1停止 2下发 3其它
    }

    public void setOnItemClickListener(VidelChannelAdapter.OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public void setmDatas(BuildingDetailModel model) {
        model = model;
        dataList = new ArrayList<BuildingDetailModel.DataBean.NodeListBean>();
        for(int i=0;i<model.getData().getNodeList().size();i++)
        {
            dataList.add(model.getData().getNodeList().get(i));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bottom_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.context.setText(dataList.get(position).getName());
        holder.video_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemSiteClick(dataList.get(holder.getAdapterPosition()),1);
            }
        });
        holder.video_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemSiteClick(dataList.get(holder.getAdapterPosition()),2);
            }
        });

        holder.right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemSiteClick(dataList.get(holder.getAdapterPosition()),3);
            }
        });
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
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public TextView context;
        public TextView video_change;
        public TextView video_stop;
        public ImageView right_img;

        public VH(View v) {
            super(v);
            context = v.findViewById(R.id.build_videochange);
            video_change = v.findViewById(R.id.buildbtn_videochange);
            video_stop = v.findViewById(R.id.buildbtn_videostop);
            right_img = v.findViewById(R.id.right_img);
        }
    }

    public void refreshUI(int position) {
        notifyDataSetChanged();
    }

}

