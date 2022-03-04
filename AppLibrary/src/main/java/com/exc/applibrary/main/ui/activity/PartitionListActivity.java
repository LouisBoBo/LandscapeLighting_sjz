package com.exc.applibrary.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.databinding.ActivityPartitionListBinding;
import com.exc.applibrary.main.adapter.BuildMoreRecycleAdapter;
import com.exc.applibrary.main.customview.IndexView;
import com.exc.applibrary.main.db.PartitionDao;
import com.exc.applibrary.main.db.WhAppDb;
import com.exc.applibrary.main.engine.DataEngine;
import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.IndexModel;
import com.exc.applibrary.main.model.PatitionFindListModel;

import java.io.Serializable;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGADivider;
import cn.bingoogolapple.baseadapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.baseadapter.BGARVVerticalScrollHelper;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;


public class PartitionListActivity extends BaseActivity implements OnHttpResponseListener {
    private ActivityPartitionListBinding binding;
    private List<PatitionFindListModel.DataBean.PartitionListBean> partitionListBeanList;
    private PartitionListActivity partitionListActivity;
    private PartitionDao partitionDao;
    private BuildMoreRecycleAdapter mAdapter;
    private List<IndexModel> adapter_data;
    private PatitionFindListModel.DataBean.PartitionListBean partitionListBean;
    private BGARVVerticalScrollHelper mRecyclerViewScrollHelper;

    private final int BACK_PARTITION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPartitionListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        setListener();
        initData();
        initEvent();
    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {

    }

    @Override
    public void initView() {
        partitionListActivity = this;
        binding.ivStickyIndex.setTipTv(binding.tvStickyTip);
        //输入框文本改变
        binding.etSearch.addTextChangedListener(new TextWatcher() {
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
                    partitionDao = WhAppDb.getInstance(partitionListActivity).getPartitionDao();
                    partitionListBeanList = partitionDao.query(s.toString());

                    if(partitionListBeanList.size() >0){
                        adapter_data = DataEngine.loadPartitionIndexModelData(partitionListBeanList);
                        mAdapter.setData(adapter_data);
                        binding.buildRecycleview.setAdapter(mAdapter);
                    }
                }else {
                    initData();
                }
            }

            @Override
            //文本改变之后执行
            public void afterTextChanged(Editable s) {
                android.util.Log.i("after", "ok");
            }

        });
        //输入框点击
        binding.etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        //确认按钮点击
        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据是使用Intent返回
                Intent intent = new Intent(PartitionListActivity.this,DataCenterActivity.class);
                //把返回数据存入Intent
                if(partitionListBean != null){
                    intent.putExtra("result" , (Serializable)partitionListBean);
                    //设置返回数据
                    setResult(BACK_PARTITION_CODE, intent);
                    //关闭Activity
                    PartitionListActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void initData() {
        partitionDao = WhAppDb.getInstance(partitionListActivity).getPartitionDao();
        partitionListBeanList = partitionDao.query();

        if(partitionListBeanList.size() >0){
            adapter_data = DataEngine.loadPartitionIndexModelData(partitionListBeanList);
            mAdapter.setData(adapter_data);
            binding.buildRecycleview.setAdapter(mAdapter);
        }
    }

    protected void setListener() {
        mAdapter = new BuildMoreRecycleAdapter(binding.buildRecycleview);
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
                        }
                    }else {
                        adapter_data.get(i).is_select = false;
                    }
                }

                mAdapter.setData(adapter_data);
            }
        });

        initStickyDivider();

        binding.ivStickyIndex.setDelegate(new IndexView.Delegate() {
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

        binding.buildRecycleview.addItemDecoration(BGADivider.newDrawableDivider(R.drawable.shape_tip)
                .setStartSkipCount(0)
                .setMarginLeftResource(R.dimen.size_level3)
                .setMarginRightResource(R.dimen.size_level9)
                .setDelegate(stickyDelegate));

        mRecyclerViewScrollHelper = BGARVVerticalScrollHelper.newInstance(binding.buildRecycleview, new BGARVVerticalScrollHelper.SimpleDelegate() {
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
