package com.exc.applibrary.main.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.exc.applibrary.R;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.adapter.CommandAdapter;
import com.exc.applibrary.main.adapter.ProjectionListAdapter;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.model.CommandHttpModel;
import com.exc.applibrary.main.model.CommandModel;
import com.exc.applibrary.main.model.CommandStartModel;
import com.exc.applibrary.main.model.DeviceListModel;
import com.exc.applibrary.main.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;

public class DeviceFragment extends BaseFragment implements View.OnClickListener, OnHttpResponseListener {

    private ProjectionListAdapter adapter;
    private CommandAdapter commandAdapter;
    private RecyclerView myRecyclerview;
    private RecyclerView headRecyclerview;
    private List<DeviceListModel.DataBean.ListBean> deviceBeans;
    private List<CommandModel.DataBean.ListBean> commandBeans;
    private List<CommandHttpModel> commandHttpModelList;
    private List<TextView> textViewList;
    private TextView tvalldevice;
    private TextView tvservice;
    private TextView tvbootcard;
    private TextView tvproject;
    private TextView tvplay;
    private TextView tvsuspend;
    private TextView tvstop;
    private LinearLayout selectheadview;
    private SmartRefreshLayout refreshLayout;
    private CustomDialog customDialog;
    private DeviceListModel deviceListModel;

    private int typeId;//设备类型
    private int deviceTypeId;//设备类型id
    private int deviceId;//设备id
    private int device_page;

    private final int DEVICE_REQUEST_CODE = 1;
    private final int COMMAND_REQUEST_CODE = 2;
    private final int COMMANDSTART_REQUEST_CODE = 3;

    Handler myHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 456:
                    for (DeviceListModel.DataBean.ListBean listBean : deviceListModel.getData().getList()) {
                        deviceBeans.add(listBean);
                    }

                    adapter.setmDatas(deviceBeans);

                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);

        myRecyclerview = view.findViewById(R.id.rightRecycleview);
        tvalldevice = view.findViewById(R.id.alldevice);
        tvservice = view.findViewById(R.id.service);
        tvbootcard = view.findViewById(R.id.bootcard);
        tvproject = view.findViewById(R.id.projection);
        selectheadview = view.findViewById(R.id.select_headview);
        headRecyclerview = view.findViewById(R.id.headRecycleview);

        initView();
        initData();

        return view;
    }

    public void initView() {
        customDialog = new CustomDialog(getActivity());
        textViewList = new ArrayList<>();
        textViewList.add(tvalldevice);
        textViewList.add(tvservice);
        textViewList.add(tvbootcard);
        textViewList.add(tvproject);

        for (TextView view : textViewList) {
            if (view == tvalldevice) {
                view.setBackgroundResource(R.drawable.background_tv_select);
            } else {
                view.setBackgroundResource(R.drawable.background_tv_unselect);
            }
        }

        tvalldevice.setOnClickListener(this::onClick);
        tvservice.setOnClickListener(this::onClick);
        tvbootcard.setOnClickListener(this::onClick);
        tvproject.setOnClickListener(this::onClick);

        GridLayoutManager control_manager = new GridLayoutManager(getActivity(), 1);
        myRecyclerview.setLayoutManager(control_manager);
        adapter = new ProjectionListAdapter();
        myRecyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProjectionListAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int index, boolean is_select) {
                if (is_select) {
                    deviceTypeId = deviceBeans.get(index).getDeviceTypeId();
                    deviceId = deviceBeans.get(index).getId();
                    HttpRequest.getcommandHttp(deviceTypeId, COMMAND_REQUEST_CODE, DeviceFragment.this::onHttpResponse);
                } else {
                    selectheadview.setVisibility(View.GONE);
                }
            }
        });

        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        headRecyclerview.setLayoutManager(mLayoutManager);
        commandAdapter = new CommandAdapter();
        headRecyclerview.setAdapter(commandAdapter);

        commandAdapter.setOnItemClickListener(new CommandAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int index, boolean is_select) {
                CommandHttpModel commandHttpModel = new CommandHttpModel();
                commandHttpModel.setCommandId(commandBeans.get(index).getId());
                commandHttpModel.setDeviceId(deviceId);

                commandHttpModelList = new ArrayList<>();
                commandHttpModelList.add(commandHttpModel);

                customDialogShow();
                HttpRequest.commandStartHttp(commandHttpModelList, COMMANDSTART_REQUEST_CODE, DeviceFragment.this::onHttpResponse);
            }
        });

        selectheadview.setVisibility(View.GONE);

        //当列表滑动到底部时加载更多
        myRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //当前RecyclerView显示出来的最后一个的item的position
                int lastPosition = -1;

                //当前状态为停止滑动状态SCROLL_STATE_IDLE时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        //通过LayoutManager找到当前显示的最后的item的position
                        lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                        //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                        int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                        lastPosition = findMax(lastPositions);
                    }

                    //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                    //如果相等则说明已经滑动到最后了
                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                        HttpRequest.getdevicesHttp(typeId, device_page, DEVICE_REQUEST_CODE, DeviceFragment.this::onHttpResponse);
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }

        });

    }

    //找到数组中的最大值
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void initData() {
        typeId = 0;
        device_page = 1;
        deviceBeans = new ArrayList<>();
        commandBeans = new ArrayList<>();

        HttpRequest.getdevicesHttp(typeId, device_page, DEVICE_REQUEST_CODE, this::onHttpResponse);
    }

    @Override
    public void initEvent() {

    }

    //加载提示
    public void customDialogShow() {
        if (null != customDialog) {
            customDialog.dismiss();
            customDialog = new CustomDialog(getActivity());
            customDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == tvplay) {
            ToastUtils.showToast(getActivity(), tvplay.getText().toString(), false);
        } else if (v == tvsuspend) {
            ToastUtils.showToast(getActivity(), tvsuspend.getText().toString(), false);
        } else if (v == tvstop) {
            ToastUtils.showToast(getActivity(), tvstop.getText().toString(), false);
        } else {
            for (TextView view : textViewList) {
                if (view == v) {
                    view.setBackgroundResource(R.drawable.background_tv_select);
                } else {
                    view.setBackgroundResource(R.drawable.background_tv_unselect);
                }

                if (v == tvalldevice) {
                    typeId = 0;
                } else if (v == tvservice) {
                    typeId = 1;
                } else if (v == tvbootcard) {
                    typeId = 2;
                } else if (v == tvproject) {
                    typeId = 3;
                }
            }

            device_page = 1;
            deviceBeans = new ArrayList<>();
            selectheadview.setVisibility(View.GONE);
            HttpRequest.getdevicesHttp(typeId, device_page, DEVICE_REQUEST_CODE, this::onHttpResponse);
        }
    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if (requestCode == DEVICE_REQUEST_CODE) {

            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }
            deviceListModel = JsonUtils.parseObject(resultJson, DeviceListModel.class);
            if (deviceListModel != null && deviceListModel.getCode() == 200) {
                device_page++;
                if (deviceListModel.getData().getList().size() > 0) {
                    myHandler.sendEmptyMessage(456);
                }
            }

        } else if (requestCode == COMMAND_REQUEST_CODE) {
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            CommandModel commandModel = JsonUtils.parseObject(resultJson, CommandModel.class);
            if (commandModel.getCode() == 200) {
                if (commandModel.getData().getList().size() > 0) {
                    commandBeans = commandModel.getData().getList();

                    selectheadview.setVisibility(View.VISIBLE);
                    commandAdapter.setmDatas(commandBeans);
                }
            }
        } else if (requestCode == COMMANDSTART_REQUEST_CODE) {
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            CommandStartModel commandStartModel = JsonUtils.parseObject(resultJson, CommandStartModel.class);
            if (commandStartModel.getCode() == 200) {
                ToastUtils.showToast(getActivity(), "下发成功", false);
            } else {
                ToastUtils.showToast(getActivity(), commandStartModel.getMessage(), false);
            }
        }
    }
}
