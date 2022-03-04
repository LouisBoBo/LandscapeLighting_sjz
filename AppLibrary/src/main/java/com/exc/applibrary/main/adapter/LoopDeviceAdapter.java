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
import com.exc.applibrary.main.model.NodeTypeModel;
import com.exc.applibrary.main.model.PartitionList;
import com.exc.applibrary.main.model.PartitionModel;
import com.exc.applibrary.main.model.TypeRealyModel;

import java.util.ArrayList;
import java.util.List;

public class LoopDeviceAdapter extends RecyclerView.Adapter<LoopDeviceAdapter.VH> {
    private String adapte_style;//1分区 2建筑
    private List<NodeTypeModel.DataBean.ListBean> dataBeanList = new ArrayList<>();

    private LoopDeviceAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemSiteClick(String select_text , int id);
    }

    public void setOnItemClickListener(LoopDeviceAdapter.OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public void setmDatas(NodeTypeModel model) {
        dataBeanList = new ArrayList<>();
        if(model != null && model.getData().getList().size() >0) {
            NodeTypeModel.DataBean.ListBean zerobean = new NodeTypeModel.DataBean.ListBean();
            zerobean.setIsOffline(2);
            zerobean.setId(0);
            zerobean.setIsselesct(true);
            zerobean.setName("全部节点");
            dataBeanList.add(zerobean);

            for (NodeTypeModel.DataBean.ListBean bean : model.getData().getList()) {
                bean.setIsselesct(false);
                dataBeanList.add(bean);
            }

            notifyDataSetChanged();
        }
    }

    public void setPartitionDatas(PartitionList model){
        dataBeanList = new ArrayList<>();
        if(model != null) {
            int i = 0;
            for (PartitionList.DataBean datum : model.getData()) {
                i++;
                NodeTypeModel.DataBean.ListBean bean = new NodeTypeModel.DataBean.ListBean();
                bean.setIsselesct(i == 1 ? true : false);
                bean.setIsOffline(2);
                bean.setName(datum.getName());
                bean.setId(datum.getId());

                dataBeanList.add(bean);
            }

            notifyDataSetChanged();
        }
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

        if (dataBeanList.get(holder.getAdapterPosition()).isIsselesct()) {
            holder.itemView.setBackgroundResource(R.drawable.background_photo_selector);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.background_photo_unselector);
        }

        if(dataBeanList.get(holder.getAdapterPosition()).getIsOffline()==2){
            holder.imagetitle.setVisibility(View.GONE);
        }else if(dataBeanList.get(holder.getAdapterPosition()).getIsOffline()==1){
            holder.imagetitle.setImageResource(R.mipmap.wifi_offline);
        }else if(dataBeanList.get(holder.getAdapterPosition()).getIsOffline()==0){
            holder.imagetitle.setImageResource(R.mipmap.wifi_online);
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
                dataBeanList.get(holder.getAdapterPosition()).setIsselesct(true);
            } else {
                dataBeanList.get(i).setIsselesct(false);
            }
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
        public TextView context;
        public ImageView imagetitle;
        public VH(View v) {
            super(v);
            context = v.findViewById(R.id.tv_content);
            imagetitle = v.findViewById(R.id.img_title);
        }
    }

    public void refreshUI(int position) {
        notifyDataSetChanged();
    }

}