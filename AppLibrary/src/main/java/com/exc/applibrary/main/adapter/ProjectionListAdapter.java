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
import com.exc.applibrary.main.model.ChannelAllModel;
import com.exc.applibrary.main.model.DeviceListModel;
import com.exc.applibrary.main.model.ProjectionModel;
import com.exc.applibrary.main.model.TypeRealyModel;

import java.util.List;

public class ProjectionListAdapter extends RecyclerView.Adapter<ProjectionListAdapter.VH> {
    private List<DeviceListModel.DataBean.ListBean> dataBeanList;
    private TypeRealyModel typeRealyModel;
    private ProjectionListAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemSiteClick(String select_text, int index, boolean is_select);
    }

    public void setOnItemClickListener(ProjectionListAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setmDatas(List<DeviceListModel.DataBean.ListBean> listBeans) {

        for (DeviceListModel.DataBean.ListBean listBean : listBeans) {
            if(listBean.getIs_select() == null) {
                listBean.setIs_select(false);
            }
        }
        dataBeanList = listBeans;
        typeRealyModel = typeRealyModel;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_projection, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.device_name.setText(dataBeanList.get(position).getName());
        holder.device_ip.setText(dataBeanList.get(position).getIp());

        if(dataBeanList.get(holder.getAdapterPosition()).getIs_select()){
            holder.baseview.setBackgroundResource(R.drawable.background_build_selector);
        }else {
            holder.baseview.setBackgroundResource(R.color.alph);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            //item 点击事件
            public void onClick(View v) {
                for (int i=0;i<dataBeanList.size();i++){
                    if(i == position){
                        boolean itemselect = dataBeanList.get(holder.getAdapterPosition()).getIs_select();
                        dataBeanList.get(holder.getAdapterPosition()).setIs_select(!itemselect);
                    }else {
                        dataBeanList.get(i).setIs_select(false);
                    }
                }

                itemSelectClick(holder);
                notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("ResourceType")
    public void itemSelectClick(VH holder){

        onItemClickListener.onItemSiteClick(dataBeanList.get(holder.getAdapterPosition()).getName(),holder.getAdapterPosition(),dataBeanList.get(holder.getAdapterPosition()).getIs_select());
    }

    @Override
    public int getItemCount() {
        if(dataBeanList != null){
            return dataBeanList.size();
        }else
            return 0;

    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public TextView device_name;
        public TextView device_ip;
        public View baseview;

        public VH(View v) {
            super(v);

            device_name = v.findViewById(R.id.device_name);
            device_ip = v.findViewById(R.id.device_ip);
            baseview = v.findViewById(R.id.view_baseview);
        }
    }

    public void refreshUI(List<DeviceListModel.DataBean.ListBean> listBeans) {
        dataBeanList = listBeans;
        notifyDataSetChanged();
    }

}

