package com.exc.applibrary.main.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.exc.applibrary.R;
import com.exc.applibrary.main.ui.activity.AboutUsActivity;
import com.exc.applibrary.main.ui.activity.MyInfoActivity;
import com.exc.applibrary.main.ui.activity.PwdChangeActivity;

import java.util.ArrayList;
import java.util.List;

public class MineRecycleAdapter extends RecyclerView.Adapter<MineRecycleAdapter.VH> {
    private List<String> dataList;
    private List<Integer> imageList;
    private Fragment mfragment;

    public void setmDatas(Fragment fragment) {
        mfragment = fragment;

        dataList = new ArrayList<String>();
        dataList.add("个人信息");
        dataList.add("修改密码");
        dataList.add("关于我们");

        imageList = new ArrayList<Integer>();
        imageList.add(R.mipmap.userinfo);
        imageList.add(R.mipmap.editpass);
        imageList.add(R.mipmap.about);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mymine_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.headimg.setImageResource(imageList.get(position));
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
        switch (holder.getAdapterPosition()) {
            case 0://个人信息
                Intent intent = new Intent(mfragment.getActivity(), MyInfoActivity.class);
                mfragment.getActivity().startActivity(intent);

                break;
//            case 1://修改密码
//                Intent intent1 = new Intent(mfragment.getActivity(), PwdChangeActivity.class);
//                mfragment.getActivity().startActivity(intent1);
//                break;
            case 1://关于我们
                Intent intent2 = new Intent(mfragment.getActivity(), AboutUsActivity.class);
                mfragment.getActivity().startActivity(intent2);
                break;
            default:
        }
        Log.i("sjfasj", "点击了");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //② 创建ViewHolder 绑定item元素
    public static class VH extends RecyclerView.ViewHolder {
        public ImageView headimg;
        public TextView context;
        public ImageView bottomimg;

        public VH(View v) {
            super(v);
            headimg = v.findViewById(R.id.head_image);
            context = v.findViewById(R.id.head_title);
            bottomimg = v.findViewById(R.id.bottom_image);
        }
    }

    public void refreshUI(int position) {
        notifyDataSetChanged();
    }

}
