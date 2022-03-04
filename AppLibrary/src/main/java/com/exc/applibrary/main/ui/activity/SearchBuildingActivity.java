package com.exc.applibrary.main.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.MainActivity;
import com.exc.applibrary.main.adapter.BuildRecycleAdapter;
import com.exc.applibrary.main.adapter.FenquRecycleAdapter;
import com.exc.applibrary.main.db.BuildingDao;
import com.exc.applibrary.main.db.ElectricityDao;
import com.exc.applibrary.main.db.PartitionDao;
import com.exc.applibrary.main.db.SiteDao;
import com.exc.applibrary.main.db.WhAppDb;
import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.PartitionDetailModel;
import com.exc.applibrary.main.model.PartitionList;
import com.exc.applibrary.main.model.PartitionModel;
import com.exc.applibrary.main.model.PatitionFindListModel;
import com.exc.applibrary.main.model.SelectBuildModel;
import com.exc.applibrary.main.model.SelectBuildModelScene;
import com.exc.applibrary.main.model.SelectBuildModelSceneRightDetail;
import com.exc.applibrary.main.ui.fragment.SearchResultFragment;
import com.exc.applibrary.main.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;

@SuppressLint("ClickableViewAccessibility")
public class SearchBuildingActivity extends BaseActivity implements OnHttpResponseListener {
    private ImageView backview;
    private TextView build_content;
    private TextView tv_confirm;
    private EditText et_search;
    private RecyclerView fenqu_recycleview;
    private RecyclerView site_recycleview;
    private FenquRecycleAdapter fenqu_adapter;
    private BuildRecycleAdapter build_adapter;
    private SearchResultFragment searchResultFragment;
    private FrameLayout searchresult_view;
    private SearchBuildingActivity searchBuildingActivity;

    private List<PatitionFindListModel.DataBean.BuildingListBean> buildingListBeanList;
    private List<PatitionFindListModel.DataBean.SiteListBean> siteListBeanList;
    private List<PatitionFindListModel.DataBean.PartitionListBean> partitionListBeanList;
    private List<ElectricityNodeModel.DataBean.ListBean> electyicityListBeanList;
    private PatitionFindListModel.DataBean.BuildingListBean selectbuildingListBean;

    private BuildingDao buildingDao;
    private SiteDao siteDao;
    private PartitionDao partitionDao;
    private ElectricityDao electricityDao;

    private PartitionModel partitionModel;
    private SelectBuildModel selectBuildModel;
    private SelectBuildModel loop_selectBuildModel;

    private String partitiontext;
    private String sitetext;
    private String buildtext;
    private String nodetext;
    private final int PARTITION_REQUEST_CODE = 1;
    private static final int REQUEST_PARTITION_CODE = 1002;
    private final int BACK_SITE_CODE = 100;
    private final int BACK_BUILD_CODE = 101;
    private final int BACK_PARTITION_CODE = 102;
    private boolean fromScene;
    private boolean fromSceneRightDetail;
    private boolean fromLoopControlRight;


    //收到消息回主UI刷新界面
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 123:
                    searchResultFragment.refreshUI(partitionListBeanList, siteListBeanList, buildingListBeanList,electyicityListBeanList);
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchBuildingActivity = this;
        loop_selectBuildModel = new SelectBuildModel();
        fromScene = getIntent().getBooleanExtra("fromScene", false);
        fromSceneRightDetail = getIntent().getBooleanExtra("fromSceneRightDetail", false);
        fromLoopControlRight = getIntent().getBooleanExtra("fromLoopControlRight",false);
        setContentView(R.layout.activity_building_search);
        initView();
        initData();
    }

    //选择的分区-站点-建筑数据
    public void changeSelectData(SelectBuildModel model) {
        selectBuildModel = model;

        if (model.getPartition_name() != null) {
            partitiontext = model.getPartition_name();
        } else if (model.getSite_name() != null) {
            sitetext = model.getSite_name();
        } else if (model.getBuild_name() != null) {
            buildtext = model.getBuild_name();
        } else if (model.getNode_name() !=null){
            nodetext = model.getNode_name();
        }
        build_content.setText(partitiontext + " " + sitetext + "" + buildtext + "" + nodetext);
    }

    public void sendSelectData(SelectBuildModel partition_model,SelectBuildModel site_model,SelectBuildModel build_model,SelectBuildModel node_model){
        if(partition_model != null){
            loop_selectBuildModel.setPartition_id(partition_model.getPartition_id());
            loop_selectBuildModel.setPartition_name(partition_model.getPartition_name());
        }
        if(site_model != null){
            loop_selectBuildModel.setSite_id(site_model.getSite_id());
            loop_selectBuildModel.setSite_name(site_model.getSite_name());
        }
        if(build_model != null){
            loop_selectBuildModel.setBuild_id(build_model.getBuild_id());
            loop_selectBuildModel.setBuild_name(build_model.getBuild_name());
        }
        if(node_model != null){
            loop_selectBuildModel.setNode_id(node_model.getNode_id());
            loop_selectBuildModel.setNode_name(node_model.getNode_name());
        }
    }

    @Override
    public void initView() {
        backview = findView(R.id.iv_back);
        et_search = findViewById(R.id.et_search);
        fenqu_recycleview = findViewById(R.id.fenqu_recycleview);
        site_recycleview = findViewById(R.id.build_recycleview);
        build_content = findView(R.id.tv_content);
        tv_confirm = findViewById(R.id.tv_confirm);
        searchresult_view = findViewById(R.id.searchresult_view);
        searchResultFragment = new SearchResultFragment();

        GridLayoutManager fenqu_manager = new GridLayoutManager(getActivity(), 1);
        fenqu_recycleview.setLayoutManager(fenqu_manager);
        fenqu_adapter = new FenquRecycleAdapter();

        GridLayoutManager build_manager = new GridLayoutManager(getActivity(), 2);
        site_recycleview.setLayoutManager(build_manager);
        build_adapter = new BuildRecycleAdapter();

        //分区列表建筑
        fenqu_adapter.setOnItemClickListener(new FenquRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int index) {
                //刷新列表数据
                if (partitionModel.getData().size() > 0) {
                    build_adapter.refreshUI(partitionModel.getData().get(index));
                }

                SelectBuildModel selectBuildModel = new SelectBuildModel();
                selectBuildModel.setPartition_id(partitionModel.getData().get(index).getId());
                selectBuildModel.setPartition_name(partitionModel.getData().get(index).getName());
                selectBuildModel.setSelect_data_style(1);
                changeSelectData(selectBuildModel);

                if(selectBuildModel != null && fromLoopControlRight){
                    loop_selectBuildModel.setPartition_id(selectBuildModel.getPartition_id());
                    loop_selectBuildModel.setPartition_name(selectBuildModel.getPartition_name());
                }

            }
        });
        //站点列表item点击
        build_adapter.setOnItemClickListener(new BuildRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int id,int partition_id) {

                SelectBuildModel selectBuildModel = new SelectBuildModel();
                selectBuildModel.setPartition_id(partition_id);
                selectBuildModel.setSite_id(id);

                selectBuildModel.setSite_name(select_text);
                selectBuildModel.setSelect_data_style(2);
                changeSelectData(selectBuildModel);

                if(selectBuildModel != null && fromLoopControlRight){
                    loop_selectBuildModel.setSite_id(selectBuildModel.getSite_id());
                    loop_selectBuildModel.setSite_name(selectBuildModel.getSite_name());
                }
            }
        });

        searchresult_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //输入框文本改变
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            //文本改变之前执行
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                android.util.Log.i("before", "ok");
            }

            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度不为0
                if (s.length() != 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.searchresult_view, searchResultFragment).commitAllowingStateLoss();
                    searchresult_view.setVisibility(View.VISIBLE);
                    partitiontext = "";
                    sitetext = "";
                    buildingDao = WhAppDb.getInstance(searchBuildingActivity).getBuildingDao();
                    buildingListBeanList = buildingDao.query(s.toString());

                    siteDao = WhAppDb.getInstance(searchBuildingActivity).getSiteDao();
                    siteListBeanList = siteDao.query(s.toString());

                    partitionDao = WhAppDb.getInstance(searchBuildingActivity).getPartitionDao();
                    partitionListBeanList = partitionDao.query(s.toString());

                    electricityDao = WhAppDb.getInstance(searchBuildingActivity).getElectricityDao();
                    electyicityListBeanList = electricityDao.query(s.toString());

                    myHandler.sendEmptyMessage(123);
                } else {
                    searchresult_view.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            //文本改变之后执行
            public void afterTextChanged(Editable s) {
                android.util.Log.i("after", "ok");
            }

        });
        //输入框点击
        et_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });




        //确定按钮
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromScene) {
                    if (selectBuildModel.getSite_id() > 0) {
                        SelectBuildModelScene selectBuildModelScene = new SelectBuildModelScene();
                        selectBuildModelScene.setPartition_id(selectBuildModel.getPartition_id());
                        selectBuildModelScene.setSite_id(selectBuildModel.getSite_id());
                        EventBus.getDefault().post(selectBuildModelScene);
                    }
                    searchBuildingActivity.finish();
                    return;
                }

                if (fromSceneRightDetail) {
                    if (selectBuildModel.getSite_id() > 0) {
                        SelectBuildModelSceneRightDetail selectBuildModelSceneRightDetail = new SelectBuildModelSceneRightDetail();
                        selectBuildModelSceneRightDetail.setPartition_id(selectBuildModel.getPartition_id());
                        selectBuildModelSceneRightDetail.setSite_id(selectBuildModel.getSite_id());
                        EventBus.getDefault().post(selectBuildModelSceneRightDetail);
                    }
                    searchBuildingActivity.finish();
                    return;
                }

                if(fromLoopControlRight){
                    EventBus.getDefault().post(loop_selectBuildModel);
                    searchBuildingActivity.finish();
                    return;
                }

                if (selectBuildModel.getBuild_id() > 0) {
                    selectBuildModel.setResultCode(101);
                } else if (selectBuildModel.getSite_id() > 0) {
                    selectBuildModel.setResultCode(100);
                } else if (selectBuildModel.getPartition_id() > 0) {
                    selectBuildModel.setResultCode(102);
                }
                EventBus.getDefault().post(selectBuildModel);

                //数据是使用Intent返回
                Intent intent = new Intent(searchBuildingActivity, MainActivity.class);
                searchBuildingActivity.finish();
            }
        });

        //返回键
        backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBuildingActivity.finish();
            }
        });
    }

    @Override
    public void initData() {
        partitiontext = "";
        sitetext = "";
        buildtext = "";
        nodetext = "";
        selectBuildModel = new SelectBuildModel();
        HttpRequest.getPartitionQuery("", "", PARTITION_REQUEST_CODE, searchBuildingActivity);
        HttpRequest.getPartitionsList(REQUEST_PARTITION_CODE,this);

//        buildingDao = WhAppDb.getInstance(this).getBuildingDao();
//        buildingListBeanList = buildingDao.query();
//
//        siteDao = WhAppDb.getInstance(this).getSiteDao();
//        siteListBeanList = siteDao.query();

        Log.d("sfas", "okok");
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if (requestCode == PARTITION_REQUEST_CODE) {
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            partitionModel = JsonUtils.parseObject(resultJson, PartitionModel.class);
            if (partitionModel.getCode() == 200) {
                fenqu_adapter.setmDatas(partitionModel);
                fenqu_recycleview.setAdapter(fenqu_adapter);

                if (partitionModel.getData().size() > 0) {

                    selectBuildModel = new SelectBuildModel();
                    selectBuildModel.setPartition_id(partitionModel.getData().get(0).getId());
                    selectBuildModel.setPartition_name(partitionModel.getData().get(0).getName());
                    changeSelectData(selectBuildModel);

                    build_adapter.setmDatas(partitionModel.getData().get(0));
                    site_recycleview.setAdapter(build_adapter);
                }
            }

        }else if(requestCode == REQUEST_PARTITION_CODE){

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (resultCode == 100) {//站点返回

                PatitionFindListModel.DataBean.SiteListBean bean = (PatitionFindListModel.DataBean.SiteListBean) data.getExtras().getSerializable("result");
                if (bean != null) {

                    SelectBuildModel selectBuildModel = new SelectBuildModel();
                    selectBuildModel.setPartition_id(bean.getPartitionId());
                    selectBuildModel.setSite_id(bean.getId());
                    selectBuildModel.setSite_name(bean.getName());
                    changeSelectData(selectBuildModel);

                    if(selectBuildModel != null && fromLoopControlRight){
                        loop_selectBuildModel.setSite_id(selectBuildModel.getSite_id());
                        loop_selectBuildModel.setSite_name(selectBuildModel.getSite_name());
                    }
                }
            } else if (resultCode == 101) {//建筑返回

                selectbuildingListBean = (PatitionFindListModel.DataBean.BuildingListBean) data.getExtras().getSerializable("result");
                if (selectbuildingListBean != null) {

                    SelectBuildModel selectBuildModel = new SelectBuildModel();
                    selectBuildModel.setPartition_id(selectbuildingListBean.getPartitionId());
                    selectBuildModel.setBuild_id(selectbuildingListBean.getId());
                    selectBuildModel.setBuild_name(selectbuildingListBean.getName());
                    changeSelectData(selectBuildModel);

                    if(selectBuildModel != null && fromLoopControlRight){
                        loop_selectBuildModel.setBuild_id(selectBuildModel.getBuild_id());
                        loop_selectBuildModel.setBuild_name(selectBuildModel.getBuild_name());
                    }
                }
            } else if (resultCode == 102) {//分区返回
                PatitionFindListModel.DataBean.PartitionListBean bean = (PatitionFindListModel.DataBean.PartitionListBean) data.getExtras().getSerializable("result");
                if (bean != null) {

                    SelectBuildModel selectBuildModel = new SelectBuildModel();
                    selectBuildModel.setPartition_id(bean.getId());
                    selectBuildModel.setSite_name(bean.getName());
                    changeSelectData(selectBuildModel);

                    if(selectBuildModel != null && fromLoopControlRight){
                        loop_selectBuildModel.setPartition_id(selectBuildModel.getPartition_id());
                        loop_selectBuildModel.setPartition_name(selectBuildModel.getPartition_name());
                    }
                }
            } else if(resultCode == 103) {//节点返回
                ElectricityNodeModel.DataBean.ListBean bean = (ElectricityNodeModel.DataBean.ListBean) data.getExtras().getSerializable("result");
                if (bean != null) {

                    selectBuildModel.setNode_id(bean.getId());
                    selectBuildModel.setNode_name(bean.getName());

                    loop_selectBuildModel.setNode_id(bean.getId());
                    loop_selectBuildModel.setNode_name(bean.getName());

                    nodetext = selectBuildModel.getNode_name();
                    build_content.setText(partitiontext + " " + sitetext + "" + buildtext + "" + nodetext);
                }
            }
        }
    }
}
