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

import java.util.ArrayList;
import java.util.List;

public class DashboardRecycleAdapter extends RecyclerView.Adapter<DashboardRecycleAdapter.VH> {
    private List<String> dataList;
    
    public void setmDatas() {
        dataList = new ArrayList<String>();
        dataList.add("切换节目");
        dataList.add("场景执行");
        dataList.add("回路控制");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.img.setImageResource(R.mipmap.ic_launcher_round);
        holder.context.setText(dataList.get(position));
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
        public ImageView img;
        public TextView context;

        public VH(View v) {
            super(v);
            img = v.findViewById(R.id.video_imageView);
            context = v.findViewById(R.id.video_context);
        }
    }

    public void refreshUI(int position) {
        notifyDataSetChanged();
    }

}
