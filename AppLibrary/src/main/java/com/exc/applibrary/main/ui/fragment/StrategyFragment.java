package com.exc.applibrary.main.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSONObject;
import com.exc.applibrary.R;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.adapter.IndicatorExpandableListAdapter;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.model.StrategyDetailModel;
import com.exc.applibrary.main.model.StrategyModel;
import com.exc.applibrary.main.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;

public class StrategyFragment extends BaseFragment implements OnHttpResponseListener {
    private IndicatorExpandableListAdapter adapter;
    private ExpandableListView expandableListView;
    private List<StrategyModel.DataBean.ListBean> listBeans;
    private int status;//策略状态
    private String message;//提示信息
    private int strategy_page;
    private CustomDialog customDialog;
    private StrategyModel strategyModel;

    private final int STRATEGY_REQUEST_CODE = 1;
    private final int STRATEGY_DETAIL_REQUEST_CODE = 2;
    private final int STRATEGY_CONTROL_REQUEST_CODE = 3;

    //收到消息回主UI刷新界面
    Handler myHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 123:
                    ToastUtils.showToast(getActivity(),message,false);
                    break;
                case 456:
                    for (StrategyModel.DataBean.ListBean listBean : strategyModel.getData().getList()) {
                        listBeans.add(listBean);
                    }
                    if(strategy_page == 1){
                        initView();
                    }else {
                        adapter.refreshUI(listBeans);
                    }
                    strategy_page ++;
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if(requestCode == STRATEGY_REQUEST_CODE){
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }
            strategyModel = JsonUtils.parseObject(resultJson, StrategyModel.class);
            if(strategyModel !=null &&strategyModel.getCode() == 200){

                if(strategyModel.getData().getList().size() >0){
                    myHandler.sendEmptyMessage(456);
                }

            }
        }else if(requestCode == STRATEGY_DETAIL_REQUEST_CODE){
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            StrategyDetailModel strategyDetailModel = JsonUtils.parseObject(resultJson,StrategyDetailModel.class);
            if(strategyDetailModel.getCode() == 200){
                customDialogShow();
                HttpRequest.strategyControlHttp(strategyDetailModel,status,STRATEGY_CONTROL_REQUEST_CODE,this::onHttpResponse);
            }
        }else if(requestCode == STRATEGY_CONTROL_REQUEST_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            JSONObject jsonObj = JSONObject.parseObject(resultJson);
            String code = jsonObj.getString("code");
            message = jsonObj.getString("message");

            if(code.equals("200")){
                message = status == 0?"暂停成功":"播放成功";
            }

            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        myHandler.sendEmptyMessage(123);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                }
            }).start();

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater .inflate(R.layout.fragment_strategy ,container,false) ;
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandable_list);

        initData();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initView(){
        customDialog = new CustomDialog(getActivity());

        // 在设置适配器之前设置是否支持下拉刷新
        adapter = new IndicatorExpandableListAdapter(listBeans);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null);// 清除默认的 Indicator


        //  设置分组项的点击监听事件
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean groupExpanded = parent.isGroupExpanded(groupPosition);
                adapter.setIndicatorState(groupPosition, groupExpanded);
                // 请务必返回 false，否则分组不会展开
                return false;
            }
        });

        //  设置子选项点击监听事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return true;
            }
        });

        adapter.setOnPlayClickListener(new IndicatorExpandableListAdapter.OnPlayClickListener() {
            @Override
            public void onPlayClick(String select_text, int id, int style) {
                if(style == 0){
                    status = 1;
                }else {
                    status = 0;
                }
                HttpRequest.strategyDetailHttp(id,STRATEGY_DETAIL_REQUEST_CODE, StrategyFragment.this::onHttpResponse);
            }
        });


        //当列表滑动到底部时加载更多
        expandableListView.setOnScrollListener(new ExpandableListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            //TODO
                            Log.i("sfasjflaf","判断滚动到底部");
                            HttpRequest.getStrateryHttp("",strategy_page,STRATEGY_REQUEST_CODE, StrategyFragment.this::onHttpResponse);
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });
    }


    public void initData(){
        strategy_page = 1;
        listBeans = new ArrayList<>();
        HttpRequest.getStrateryHttp("",strategy_page,STRATEGY_REQUEST_CODE,this::onHttpResponse);
    }

    @Override
    public void initEvent() {

    }

    //加载提示
    public void customDialogShow(){
        if (null != customDialog) {
            customDialog.dismiss();
            customDialog = new CustomDialog(getActivity());
            customDialog.show();
        }
    }
}
