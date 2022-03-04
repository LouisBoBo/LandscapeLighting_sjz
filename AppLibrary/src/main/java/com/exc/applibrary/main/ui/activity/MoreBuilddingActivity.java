package com.exc.applibrary.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.adapter.BuildMoreRecycleAdapter;
import com.exc.applibrary.main.customview.IndexView;
import com.exc.applibrary.main.engine.DataEngine;
import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.IndexModel;
import com.exc.applibrary.main.model.PatitionFindListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGADivider;
import cn.bingoogolapple.baseadapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.baseadapter.BGARVVerticalScrollHelper;
import zuo.biao.library.base.BaseActivity;

public class MoreBuilddingActivity extends BaseActivity {

    private BuildMoreRecycleAdapter mAdapter;
    private RecyclerView build_recycleview;
    private IndexView mIndexView;
    private TextView mTipTv;
    private ImageView left_img;
    private TextView right_confirm;
    private BGARVVerticalScrollHelper mRecyclerViewScrollHelper;
    private List<IndexModel> adapter_data;
    private List<PatitionFindListModel.DataBean.SiteListBean> siteListBeanList;
    private List<PatitionFindListModel.DataBean.BuildingListBean> buildingListBeanList;
    private List<PatitionFindListModel.DataBean.PartitionListBean> partitionListBeanList;
    private List<ElectricityNodeModel.DataBean.ListBean> electricityListBeanList;
    private PatitionFindListModel.DataBean.SiteListBean siteListBean;
    private PatitionFindListModel.DataBean.BuildingListBean buildingListBean;
    private PatitionFindListModel.DataBean.PartitionListBean partitionListBean;
    private ElectricityNodeModel.DataBean.ListBean nodeListBean;

    private final int BACK_SITE_CODE = 100;
    private final int BACK_BUILD_CODE = 101;
    private final int BACK_PARTITION_CODE = 102;
    private final int BACK_NODE_CODE = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_more);
        initView();
        setListener();
        initData();
    }

    @Override
    public void initView() {
        build_recycleview = findViewById(R.id.build_Recycleview);
        mIndexView = findViewById(R.id.iv_sticky_index);
        mTipTv = findViewById(R.id.tv_sticky_tip);
        left_img = findViewById(R.id.header_left_img);
        right_confirm = findViewById(R.id.header_right_confirm);

        right_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据是使用Intent返回
                Intent intent = new Intent(MoreBuilddingActivity.this,SearchBuildingActivity.class);
                //把返回数据存入Intent
                if(partitionListBean != null){
                    intent.putExtra("result" , (Serializable)partitionListBean);
                    //设置返回数据
                    setResult(BACK_PARTITION_CODE, intent);
                }else if(siteListBeanList != null){
                    intent.putExtra("result" , (Serializable)siteListBean);
                    //设置返回数据
                    setResult(BACK_SITE_CODE, intent);
                }else if(buildingListBeanList != null) {
                    intent.putExtra("result" , (Serializable)buildingListBean);
                    //设置返回数据
                    setResult(BACK_BUILD_CODE, intent);
                }else if(electricityListBeanList != null){
                    intent.putExtra("result" , (Serializable)nodeListBean);
                    //设置返回数据
                    setResult(BACK_NODE_CODE, intent);
                }

                //关闭Activity
                MoreBuilddingActivity.this.finish();
            }
        });

    }

    @Override
    public void initData() {

        partitionListBeanList = (List<PatitionFindListModel.DataBean.PartitionListBean>) getIntent().getSerializableExtra("morepartitiondata");
        siteListBeanList = (List<PatitionFindListModel.DataBean.SiteListBean>) getIntent().getSerializableExtra("moresitedata");
        buildingListBeanList = (List<PatitionFindListModel.DataBean.BuildingListBean>) getIntent().getSerializableExtra("morebuilddata");
        electricityListBeanList = (List<ElectricityNodeModel.DataBean.ListBean>) getIntent().getSerializableExtra("morenodedata");

        mIndexView.setTipTv(mTipTv);
        if(partitionListBeanList != null){
            adapter_data = DataEngine.loadPartitionIndexModelData(partitionListBeanList);
        }else if(siteListBeanList != null){
            adapter_data = DataEngine.loadsiteIndexModelData(siteListBeanList);
        }else if(buildingListBeanList != null){
            adapter_data = DataEngine.loadIndexModelData(buildingListBeanList);
        }else if(electricityListBeanList != null){
            adapter_data = DataEngine.loadnodeIndexModelData(electricityListBeanList);
        }
        mAdapter.setData(adapter_data);
        build_recycleview.setAdapter(mAdapter);
    }

    protected void setListener() {
        mAdapter = new BuildMoreRecycleAdapter(build_recycleview);
        mAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                for(int i=0;i<adapter_data.size();i++){
                    if(i == position){
                        adapter_data.get(position).is_select = true;
                        if(partitionListBeanList != null){
                            partitionListBean = new PatitionFindListModel.DataBean.PartitionListBean();
                            partitionListBean.setName(adapter_data.get(position).name);
                            partitionListBean.setId(adapter_data.get(position).id);
                        }else if(siteListBeanList != null){
                            siteListBean = new PatitionFindListModel.DataBean.SiteListBean();
                            siteListBean.setName(adapter_data.get(position).name);
                            siteListBean.setId(adapter_data.get(position).id);
                            siteListBean.setPartitionId(adapter_data.get(position).partitionid);
                        }else if(buildingListBeanList !=null) {
                            buildingListBean = new PatitionFindListModel.DataBean.BuildingListBean();
                            buildingListBean.setName(adapter_data.get(position).name);
                            buildingListBean.setId(adapter_data.get(position).id);
                            buildingListBean.setPartitionId(adapter_data.get(position).partitionid);
                        }else if(electricityListBeanList != null){
                            nodeListBean = new ElectricityNodeModel.DataBean.ListBean();
                            nodeListBean.setId(adapter_data.get(position).id);
                            nodeListBean.setName(adapter_data.get(position).name);
                        }
                    }else {
                        adapter_data.get(i).is_select = false;
                    }
                }

                mAdapter.setData(adapter_data);
            }
        });

        initStickyDivider();

        mIndexView.setDelegate(new IndexView.Delegate() {
            @Override
            public void onIndexViewSelectedChanged(IndexView indexView, String text) {
                int position = mAdapter.getPositionForCategory(text.charAt(0));
                if (position != -1) {
                    mRecyclerViewScrollHelper.smoothScrollToPosition(position);
                    mRecyclerViewScrollHelper.scrollToPosition(position);
                }
            }
        });
    }

    private void initStickyDivider() {
        final BGADivider.StickyDelegate stickyDelegate = new BGADivider.StickyDelegate() {
            @Override
            public void initCategoryAttr() {

            }

            @Override
            protected boolean isCategoryFistItem(int position) {
                return mAdapter.isCategoryFistItem(position);
            }

            @Override
            protected String getCategoryName(int position) {
                return mAdapter.getItem(position).topc;
            }

            @Override
            protected int getFirstVisibleItemPosition() {
                return mRecyclerViewScrollHelper.findFirstVisibleItemPosition();
            }
        };

        build_recycleview.addItemDecoration(BGADivider.newDrawableDivider(R.drawable.shape_tip)
                .setStartSkipCount(0)
                .setMarginLeftResource(R.dimen.size_level3)
                .setMarginRightResource(R.dimen.size_level9)
                .setDelegate(stickyDelegate));

        mRecyclerViewScrollHelper = BGARVVerticalScrollHelper.newInstance(build_recycleview, new BGARVVerticalScrollHelper.SimpleDelegate() {
            @Override
            public int getCategoryHeight() {
                return stickyDelegate.getCategoryHeight();
            }
        });
    }

    @Override
    public void initEvent() {

    }
}
