package com.exc.applibrary.main.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.model.CommandModel;
import com.exc.applibrary.main.model.DeviceListModel;
import com.exc.applibrary.main.model.TypeRealyModel;

import java.util.List;

public class CommandAdapter extends RecyclerView.Adapter<CommandAdapter.VH> {
    private List<CommandModel.DataBean.ListBean> dataBeanList;
    private TypeRealyModel typeRealyModel;
    private CommandAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemSiteClick(String select_text, int index, boolean is_select);
    }

    public void setOnItemClickListener(CommandAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public void setmDatas(List<CommandModel.DataBean.ListBean> listBeans) {

        for (CommandModel.DataBean.ListBean listBean : listBeans) {
            listBean.setIs_select(false);
        }
        dataBeanList = listBeans;
        typeRealyModel = typeRealyModel;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_command, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.command_name.setText(dataBeanList.get(position).getName());
        if(dataBeanList.get(holder.getAdapterPosition()).isIs_select()){
            holder.command_name.setBackgroundResource(R.drawable.background_solid_blue_shape);
        }else {
            holder.command_name.setBackgroundResource(R.drawable.background_control_base);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            //item 点击事件
            public void onClick(View v) {
                for (int i=0;i<dataBeanList.size();i++){
                    if(i == position){
                        boolean itemselect = dataBeanList.get(holder.getAdapterPosition()).isIs_select();
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

        onItemClickListener.onItemSiteClick(dataBeanList.get(holder.getAdapterPosition()).getName(),holder.getAdapterPosition(),dataBeanList.get(holder.getAdapterPosition()).isIs_select());
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
        public TextView command_name;

        public VH(View v) {
            super(v);

            command_name = v.findViewById(R.id.btn_name);

        }
    }

}