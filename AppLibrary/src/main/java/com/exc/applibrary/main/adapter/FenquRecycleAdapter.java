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

public class FenquRecycleAdapter extends RecyclerView.Adapter<FenquRecycleAdapter.VH> {
    private String adapte_style;//1分区 2建筑
    private List<PartitionModel.DataBean> dataBeanList;

    private FenquRecycleAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemSiteClick(String select_text , int index);//建筑
    }

    public void setOnItemClickListener(FenquRecycleAdapter.OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public void setmDatas(PartitionModel model) {

        dataBeanList = model.getData();
        for (int i =0 ;i<dataBeanList.size();i++){
            dataBeanList.get(i).setIsselect(i==0?true:false);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fenqu_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        if (dataBeanList.get(holder.getAdapterPosition()).getIsselect()) {
            holder.itemView.setBackgroundResource(R.drawable.background_photo_selector);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.background_photo_unselector);
        }
        holder.context.setText(dataBeanList.get(position).getName());
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

        for (int i = 0; i < dataBeanList.size(); i++) {
            if (holder.getAdapterPosition() == i) {
                dataBeanList.get(holder.getAdapterPosition()).setIsselect(true);
            } else {
                dataBeanList.get(i).setIsselect(false);
            }
        }
        onItemClickListener.onItemSiteClick(dataBeanList.get(holder.getAdapterPosition()).getName(),holder.getAdapterPosition());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size()>0?dataBeanList.size():0;
    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public TextView context;
        public VH(View v) {
            super(v);
            context = v.findViewById(R.id.tv_content);
        }
    }

    public void refreshUI(int position) {
        notifyDataSetChanged();
    }

}
