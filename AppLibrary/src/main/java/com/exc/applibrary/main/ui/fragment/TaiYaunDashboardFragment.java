package com.exc.applibrary.main.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exc.applibrary.R;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.customview.YFHeaderView;
import com.exc.applibrary.main.message.MyMessageListActivity;
import com.exc.applibrary.main.model.BaseBean;
import com.exc.applibrary.main.model.LoopSearchEventBean;
import com.exc.applibrary.main.show.SwitchShowActivity;
import com.exc.applibrary.main.ui.activity.LoopControlActivity;
import com.exc.applibrary.main.ui.activity.LoopControlNewActivity;
import com.exc.applibrary.main.ui.activity.MyWorkOrderListActivity;
import com.exc.applibrary.main.ui.activity.NewDataCenterActivity;
import com.exc.applibrary.main.ui.activity.ProjectionControlActivity;
import com.exc.applibrary.main.ui.activity.SceneSwitchActivity;
import com.exc.applibrary.main.utils.CommonUtils;
import com.exc.applibrary.main.utils.ToastUtils;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;

public class TaiYaunDashboardFragment extends BaseFragment implements View.OnClickListener {
    private Activity mActivity;
    private YFHeaderView header_view;
    private CustomDialog oneKeyStartDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_dashboard_new_ty);
        mActivity = getActivity();
        oneKeyStartDialog = new CustomDialog(mActivity, "正在启动,请稍后", true);
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void initView() {
        header_view = findView(R.id.header_view);
        header_view.setImg_right(R.drawable.icon_message);
        header_view.img_right.setOnClickListener(v -> {
            toActivity(new Intent(mActivity, MyMessageListActivity.class));

        });
        header_view.img_right.setVisibility(View.GONE);


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
        if (id == R.id.bordy3) {//节目切换
            toActivity(new Intent(mActivity, SwitchShowActivity.class));
        } else if (id == R.id.bordy4) {//场景切换
            toActivity(new Intent(mActivity, SceneSwitchActivity.class));
        } else if (id == R.id.bordy5) {//回路控制
            toActivity(new Intent(mActivity, LoopControlActivity.class));
        } else if (id == R.id.bordy6) {//数据中心
            toActivity(new Intent(mActivity, NewDataCenterActivity.class));
        }
    }

    private void oneKeyStart() {

        oneKeyStartDialog.show();

        HttpRequest.getOneKeStart(1000, (requestCode, resultJson, e) -> {
            oneKeyStartDialog.dismiss();
            if (null == resultJson) {
                ToastUtils.showErrorToast(mActivity, "启动失败");
                return;
            }
            BaseBean baseBean = JsonUtils.parseObject(resultJson, BaseBean.class);

            CommonUtils.exitLogin(baseBean.getCode(),mActivity);

            if (baseBean.getCode() != 200) {
                ToastUtils.showErrorToast(mActivity, baseBean.getMessage());
                return;
            }
            ToastUtils.showToast(mActivity, "启动成功!",true);
        });


    }
}