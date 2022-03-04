package com.exc.applibrary.main.monitoring;


import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.exc.applibrary.R;

import org.jetbrains.annotations.NotNull;

public class MonitoringListAdapter extends BaseQuickAdapter<MonitoringListData.DataBean.RecordsBean, BaseViewHolder> {


    public MonitoringListAdapter() {
        super(R.layout.item_monitor_list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MonitoringListData.DataBean.RecordsBean itemData) {

        int position = baseViewHolder.getAdapterPosition();
        TextView tv_camera_name = baseViewHolder.getView(R.id.tv_camera_name);
        TextView tv_camera_num = baseViewHolder.getView(R.id.tv_camera_num);
        RelativeLayout root = baseViewHolder.getView(R.id.root);
        View line = baseViewHolder.getView(R.id.line);
        tv_camera_name.setText(itemData.getCameraName() + "");

        String cameraIndexCode = itemData.getCameraIndexCode();
        tv_camera_num.setText("***" + cameraIndexCode.substring(cameraIndexCode.length() - 5));


        if (position > 0) {
            line.setVisibility(View.INVISIBLE);
        } else {
            line.setVisibility(View.VISIBLE);
        }

        if (itemData.isSelect()) {
            root.setBackgroundColor(Color.parseColor("#3F51B5"));
            tv_camera_name.setTextColor(Color.parseColor("#FFFFFF"));
            tv_camera_num.setTextColor(Color.parseColor("#FFFFFF"));
            return;
        } else {
            root.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tv_camera_name.setTextColor(Color.parseColor("#000000"));
            tv_camera_num.setTextColor(Color.parseColor("#000000"));
        }


    }
}






