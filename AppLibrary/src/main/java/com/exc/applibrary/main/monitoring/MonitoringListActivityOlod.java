package com.exc.applibrary.main.monitoring;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.exc.applibrary.databinding.ActivityMonitoringListBinding;
import com.exc.applibrary.main.HttpRequestLi;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.utils.CommonUtils;
import com.exc.applibrary.main.utils.ToastUtils;
import com.hikvision.open.hikvideoplayer.HikVideoPlayer;
import com.hikvision.open.hikvideoplayer.HikVideoPlayerFactory;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.StringUtil;


public class MonitoringListActivityOlod extends BaseActivity implements OnHttpResponseListener {

    private ActivityMonitoringListBinding binding;
    private MonitoringListActivityOlod instance;
    private MonitoringListAdapter monitoringListAdapter;
    private int pageNo = 1;
    private final int MONITORING_LIST_REQUEST_CODE = 1;
    private CustomDialog mLoadingDialog;
    private List<MonitoringListData.DataBean.RecordsBean> mListData;
    private HikVideoPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonitoringListBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        instance = this;
        mLoadingDialog = new CustomDialog(instance);
        mPlayer = HikVideoPlayerFactory.provideHikVideoPlayer();

        initView();
        initData();
        initEvent();

    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(instance);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mRecyclerViewShowList.setLayoutManager(linearLayoutManager);
        monitoringListAdapter = new MonitoringListAdapter();
        binding.mRecyclerViewShowList.setAdapter(monitoringListAdapter);
        binding.refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNo++;
                initData();
            }
        });

    }

    @Override
    public void initData() {
        mLoadingDialog.show();
        HttpRequestLi.Companion.getMonitoringList(pageNo, MONITORING_LIST_REQUEST_CODE, instance);



    }

    @Override
    public void initEvent() {

        binding.mHeaderLeftImg.setOnClickListener(v -> {
            finish();
        });


        binding.frameLayout.setOnClickListener(new PlayWindowContainer.OnClickListener() {
            @Override
            public void onSingleClick() {
                if (binding.autoHideView.isVisible()) {
                    binding.autoHideView.hide();
                } else {
                    binding.autoHideView.show();
                }
            }
        });
//        binding.frameLayout.setOnDigitalListener(new PlayWindowContainer.OnDigitalZoomListener() {
//            @Override
//            public void onDigitalZoomOpen() {
//                executeDigitalZoom();
//            }
//        });


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        mLoadingDialog.dismiss();
        if (StringUtil.isEmpty(resultJson)) {
            ToastUtils.showErrorToast(instance, "数据异常");
        }
        switch (requestCode) {
            case MONITORING_LIST_REQUEST_CODE:
                MonitoringListData monitoringListData = JsonUtils.parseObject(resultJson, MonitoringListData.class);
                CommonUtils.exitLogin(monitoringListData.getCode(), instance);
                if (monitoringListData.getCode() != 200) {
                    if (pageNo > 1) {
                        binding.refreshLayout.finishLoadmore();
                    }
                    ToastUtils.showErrorToast(instance, monitoringListData.getMessage());
                    return;
                }
                if (pageNo == 1) {
                    mListData = monitoringListData.getData().getRecords();
                    monitoringListAdapter.setList(mListData);
                    return;
                }
                mListData.addAll(monitoringListData.getData().getRecords());
                monitoringListAdapter.addData(monitoringListData.getData().getRecords());

                break;
        }
    }
}
