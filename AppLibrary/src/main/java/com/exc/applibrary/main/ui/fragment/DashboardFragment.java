package com.exc.applibrary.main.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exc.applibrary.R;
import com.exc.applibrary.main.customview.YFHeaderView;
import com.exc.applibrary.main.message.MyMessageListActivity;
import com.exc.applibrary.main.monitoring.MonitoringListActivity;
import com.exc.applibrary.main.ui.activity.DataCenterActivity;
import com.exc.applibrary.main.ui.activity.LoopControlActivity;
import com.exc.applibrary.main.ui.activity.MyWorkOrderListActivity;
import com.exc.applibrary.main.ui.activity.SceneSwitchActivity;
import com.exc.applibrary.main.show.SwitchShowActivity;

import zuo.biao.library.base.BaseFragment;

public class DashboardFragment extends BaseFragment implements View.OnClickListener {
    private Activity mActivity;
    private YFHeaderView header_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_dashboard_new);
        mActivity = getActivity();
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void initView() {
        header_view = findView(R.id.header_view);
//        header_view.setImg_right(R.drawable.icon_message);
//        header_view.img_right.setOnClickListener(v -> {
//            toActivity(new Intent(mActivity, MyMessageListActivity.class));
//
//        });
//        header_view.img_right.setVisibility(View.GONE);


        findView(R.id.bordy1, this);
        findView(R.id.bordy2, this);
        findView(R.id.bordy3, this);
        findView(R.id.bordy4, this);
        findView(R.id.bordy5, this);
        findView(R.id.bordy6, this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bordy1) { //节目切换
//            showShortToast("敬请期待~");
            toActivity(new Intent(mActivity, SwitchShowActivity.class));

        } else if (id == R.id.bordy2) {//场景切换
            toActivity(new Intent(mActivity, SceneSwitchActivity.class));
        } else if (id == R.id.bordy3) {//回路控制
            toActivity(new Intent(mActivity, LoopControlActivity.class));
//            showShortToast("敬请期待~");
        } else if (id == R.id.bordy4) {//工单管理
            toActivity(MyWorkOrderListActivity.createIntent(mActivity));
        } else if (id == R.id.bordy5) {//数据中心
            toActivity(new Intent(mActivity, DataCenterActivity.class));
//            showShortToast("敬请期待~");
        } else if (id == R.id.bordy6) {//智能监控
            toActivity(new Intent(mActivity, MonitoringListActivity.class));

        }
    }
}