package com.exc.applibrary.main.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import com.exc.applibrary.main.model.WorkOrderList;
import com.exc.applibrary.main.view.WorkOrderListItemView;

import zuo.biao.library.base.BaseAdapter;

public class WorkListAdapter extends BaseAdapter<WorkOrderList.Data.list.WorkOrderItem, WorkOrderListItemView> {


    public WorkListAdapter(Activity context) {
        super(context);
    }

    @Override
    public WorkOrderListItemView createView(int viewType, ViewGroup parent) {
        return new WorkOrderListItemView(context,parent);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
