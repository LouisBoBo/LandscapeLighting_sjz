package com.exc.applibrary.main.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.databinding.ActivitySearchLoopBinding;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.adapter.FlexboxLayoutAdapter;
import com.exc.applibrary.main.adapter.LoopDeviceAdapter;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.model.NodeTypeModel;
import com.exc.applibrary.main.model.PartitionList;
import com.exc.applibrary.main.model.SelectBuildModel;
import com.exc.applibrary.main.model.SiteList;
import com.exc.applibrary.main.model.TypeRealyModel;
import com.exc.applibrary.main.utils.CommonUtils;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.var;
import lombok.val;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;

import static com.xuexiang.xui.XUI.getContext;

public class SearchLoopActivity extends BaseActivity  implements OnHttpResponseListener, View.OnClickListener {
    private ActivitySearchLoopBinding binding;
    private CustomDialog customDialog;
    private FlexboxLayoutAdapter adapter;
    private FlexboxLayoutAdapter siteadapter;
    private LoopDeviceAdapter deviceAdapter;
    private TypeRealyModel typeRealyModel;
    private PartitionList partitionListModel;
    private SiteList siteListModel;
    private List<TypeRealyModel.DataBean> loopTypeList;
    private List<PartitionList.DataBean> partitionList;
    private List<SiteList.DataBean.ListBean> siteList;

    private int selectLoopTypeId;
    private int partitionId;
    private int siteId;
    private int offline = 999;
    private int status = 999;

    private static final int REQUEST_TYPEREALY_CODE = 1001;
    private static final int REQUEST_PARTITION_CODE = 1002;
    private static final int REQUEST_SITE_CODE = 1003;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchLoopBinding.inflate(inflater);
        setContentView(binding.getRoot());


        initView();
        initData();
    }

    @Override
    public void initView() {
        customDialog = new CustomDialog(getActivity());

        //在线
        binding.rbNodeOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offline == 0){
                    offline = 999;
                    binding.rgNodeStatus.clearCheck();
                }else{
                    offline = 0;
                }
            }
        });

        //离线
        binding.rbNodeOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offline == 1){
                    offline = 999;
                    binding.rgNodeStatus.clearCheck();
                }else{
                    offline = 1;
                }
            }
        });

        //开启
        binding.rbLoopOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == 1){
                    status = 999;
                    binding.rgLoopStatus.clearCheck();
                }else{
                    status = 1;
                }
            }
        });

        //关闭
        binding.rbLoopClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == 0){
                    status = 999;
                    binding.rgLoopStatus.clearCheck();
                }else{
                    status = 0;
                }
            }
        });

        GridLayoutManager device_manager = new GridLayoutManager(getActivity(),1);
        binding.leftRecycleview.setLayoutManager(device_manager);
        deviceAdapter = new LoopDeviceAdapter();

        deviceAdapter.setOnItemClickListener(new LoopDeviceAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int id) {
                partitionId = id;
                HttpRequest.getSitesList(String.valueOf(id),REQUEST_SITE_CODE,SearchLoopActivity.this::onHttpResponse);
            }
        });

        binding.headerRightImg.setOnClickListener(this::onClick);
        binding.headerLeftImg.setOnClickListener(this::onClick);
    }

    @Override
    public void initData() {
        //查询回路类型列表
        customDialog.show();
        HttpRequest.channel_type_relayHttp(REQUEST_TYPEREALY_CODE, this);
        HttpRequest.getPartitionsList(REQUEST_PARTITION_CODE,this);
    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if(requestCode == REQUEST_TYPEREALY_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }
            typeRealyModel = JsonUtils.parseObject(resultJson, TypeRealyModel.class);
            loopTypeList = typeRealyModel.getData();
            CommonUtils.exitLogin(typeRealyModel.getCode(), getActivity());
            if (typeRealyModel.getCode() != 200) {
                showShortToast(typeRealyModel.getMessage());
                return;
            }

            initLoopTypeList();
        }else if(requestCode == REQUEST_PARTITION_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            partitionListModel = JsonUtils.parseObject(resultJson, PartitionList.class);
            partitionList = partitionListModel.getData();
            CommonUtils.exitLogin(partitionListModel.getCode(), getActivity());
            int partition_id = 0;
            if(partitionListModel.getCode() == 200){
                binding.leftRecycleview.setAdapter(deviceAdapter);
                deviceAdapter.setPartitionDatas(partitionListModel);
                partition_id = partitionList.get(0).getId();
            }

            if( partitionListModel != null && partitionListModel.getData().size()>0){
                binding.topLine.setVisibility(View.VISIBLE);
            }else {
                binding.topLine.setVisibility(View.GONE);
            }

            HttpRequest.getSitesList(String.valueOf(partition_id),REQUEST_SITE_CODE,this);

        }else if(requestCode == REQUEST_SITE_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            siteListModel = JsonUtils.parseObject(resultJson, SiteList.class);
            siteList = siteListModel.data.list;
            CommonUtils.exitLogin(siteListModel.code, getActivity());
            if (siteListModel.code != 200) {
                showShortToast(siteListModel.message);
                return;
            }

            initSiteList();
        }
    }


    //填充回路类型
    private void initLoopTypeList() {
        List<String> names = new ArrayList<>();
        for (TypeRealyModel.DataBean dataBean : loopTypeList) {
            names.add(dataBean.getName());
        }

        binding.recyclerLoopType.setLayoutManager(getFlexboxLayoutManager());
        adapter = new FlexboxLayoutAdapter(names);
        binding.recyclerLoopType.setAdapter(adapter);
        adapter.setCancelable(true);
        adapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, String item, int position) {
                if(adapter.select(position)){
                    selectLoopTypeId = selectLoopTypeId==loopTypeList.get(position).getId()?0:loopTypeList.get(position).getId();
                }
            }
        });
    }

    //填充站点数据
    private void initSiteList() {
        List<String> names = new ArrayList<>();
        for (SiteList.DataBean.ListBean listBean : siteList) {
            names.add(listBean.name);
        }

        binding.rightRecycleview.setLayoutManager(getFlexboxLayoutManager());
        siteadapter = new FlexboxLayoutAdapter(names);
        binding.rightRecycleview.setAdapter(siteadapter);
        siteadapter.setCancelable(true);
        siteadapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, String item, int position) {
                if(siteadapter.select(position)){
                    siteId = siteId == siteList.get(position).id ? 0:siteList.get(position).id;
                    partitionId = partitionId > 0?partitionId:partitionList.get(0).getId();
                }
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        if(v == binding.headerRightImg){
            SelectBuildModel model = new SelectBuildModel();
            model.setBuild_name(binding.etBuildName.getText().toString());
            model.setChannelTypeId(selectLoopTypeId);
            model.setPartition_id(partitionId);
            model.setSite_id(siteId);
            model.setOffline(offline);
            model.setStatus(status);
            EventBus.getDefault().post(model);
            getActivity().finish();
        }else if(v == binding.headerLeftImg){
            getActivity().finish();
        }
    }

    private FlexboxLayoutManager getFlexboxLayoutManager() {
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity());
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        return flexboxLayoutManager;
    }
}
