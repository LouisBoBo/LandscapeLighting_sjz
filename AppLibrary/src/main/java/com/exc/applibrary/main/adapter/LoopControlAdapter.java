package com.exc.applibrary.main.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.exc.applibrary.main.model.PartitionModel;
import com.exc.applibrary.main.model.TypeRealyModel;

import java.util.List;

public class LoopControlAdapter extends RecyclerView.Adapter<LoopControlAdapter.VH> {
    private String adapte_style;//1分区 2建筑
    private List<ChannelAllModel.DataBean.ListBean> dataBeanList;
    private TypeRealyModel typeRealyModel;
    private LoopControlAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemSiteClick(String select_text, int index,int type);//建筑 type =1 修改 2编辑
    }

    public void setOnItemClickListener(LoopControlAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setmDatas(List<ChannelAllModel.DataBean.ListBean> listBeans) {

        for (ChannelAllModel.DataBean.ListBean listBean : listBeans) {
            listBean.setIsselect(false);
        }
        dataBeanList = listBeans;
        typeRealyModel = typeRealyModel;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_loop_device_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        int value = dataBeanList.get(position).getValue();

        holder.loop_name.setText("回路名称：" + dataBeanList.get(position).getName());
        holder.loop_node.setText("节点名称：" + dataBeanList.get(position).getNodeName());
        holder.loop_state.setText("实时状态：");
        holder.img_switch.setBackgroundResource(value==1?R.mipmap.newcontrol_switch_open:R.mipmap.newcontrol_switch_close);

//        if(dataBeanList.get(holder.getAdapterPosition()).getItem_select()){
//            holder.baseview_device.setBackgroundResource(R.drawable.background_control_selectbase);
//        }else {
//            holder.baseview_device.setBackgroundResource(R.drawable.background_control_base);
//        }


        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemEditHolderClick(holder);
            }
        });
        holder.img_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSwitchClick(holder);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            //item 点击事件
            public void onClick(View v) {
                boolean itemselect = dataBeanList.get(holder.getAdapterPosition()).getItem_select();
                dataBeanList.get(holder.getAdapterPosition()).setItem_select(!itemselect);

                itemSelectClick(holder);
                notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("ResourceType")
    public void itemEditHolderClick(VH holder) {
        Log.i("sjfasj", "点击了");

        onItemClickListener.onItemSiteClick(dataBeanList.get(holder.getAdapterPosition()).getName(),holder.getAdapterPosition(),2);
    }

    public void itemSwitchClick(VH holder){
        if (dataBeanList.get(holder.getAdapterPosition()).getIsselect()) {
            dataBeanList.get(holder.getAdapterPosition()).setIsselect(false);
        } else {
            dataBeanList.get(holder.getAdapterPosition()).setIsselect(true);
        }

        onItemClickListener.onItemSiteClick(dataBeanList.get(holder.getAdapterPosition()).getName(),holder.getAdapterPosition(),1);
    }

    public void itemSelectClick(VH holder){

        onItemClickListener.onItemSiteClick(dataBeanList.get(holder.getAdapterPosition()).getName(),holder.getAdapterPosition(),3);
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public TextView loop_name;
        public TextView loop_node;
        public TextView loop_state;
        public ImageView img_edit;
        public ImageView img_switch;
        public View baseview_device;

        public VH(View v) {
            super(v);
            img_switch = v.findViewById(R.id.img_switch);
            img_edit = v.findViewById(R.id.img_edit);
            loop_name = v.findViewById(R.id.loop_name);
            loop_node = v.findViewById(R.id.loop_node);
            loop_state = v.findViewById(R.id.loop_state);
            baseview_device = v.findViewById(R.id.baseview_device);
        }
    }

    public void refreshUI(int position) {
        notifyDataSetChanged();
    }

}
