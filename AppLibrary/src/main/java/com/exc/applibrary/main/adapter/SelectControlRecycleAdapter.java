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
import com.exc.applibrary.main.model.TypeRealyModel;

import java.util.List;

public class SelectControlRecycleAdapter extends RecyclerView.Adapter<SelectControlRecycleAdapter.VH> {

    private SelectControlRecycleAdapter.OnItemClickListener onItemClickListener;
    private List<TypeRealyModel.DataBean> dataBeanList;
    public interface OnItemClickListener {
        void onItemSiteClick(String select_text , int id);
    }

    public void setOnItemClickListener(SelectControlRecycleAdapter.OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public void setmDatas(TypeRealyModel model,String channelTypeName) {
        dataBeanList = model.getData();
        for (TypeRealyModel.DataBean dataBean : dataBeanList) {
            dataBean.setIsselect((dataBean.getName().equals(channelTypeName))?true:false);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bottom_selectcontrol_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.context.setText(dataBeanList.get(position).getName());
        if (dataBeanList.get(holder.getAdapterPosition()).getIsselect()) {
            holder.headimg.setBackgroundResource(R.mipmap.contron_select);
        } else {
            holder.headimg.setBackgroundResource(R.mipmap.control_unselect);
        }
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

        for(int i=0;i<dataBeanList.size();i++){
            dataBeanList.get(i).setIsselect(i==holder.getAdapterPosition()?true:false);
        }
        onItemClickListener.onItemSiteClick(dataBeanList.get(holder.getAdapterPosition()).getName(),dataBeanList.get(holder.getAdapterPosition()).getId());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public ImageView headimg;
        public TextView context;
        public VH(View v) {
            super(v);
            headimg = v.findViewById(R.id.select_headimage);
            context = v.findViewById(R.id.select_content);
        }
    }

    public void refreshUI(int position) {
        notifyDataSetChanged();
    }
}