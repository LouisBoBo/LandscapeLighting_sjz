package com.exc.applibrary.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.adapter.SearchResultAdapter;
import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.PatitionFindListModel;
import com.exc.applibrary.main.model.SelectBuildModel;
import com.exc.applibrary.main.ui.activity.MoreBuilddingActivity;
import com.exc.applibrary.main.ui.activity.SearchBuildingActivity;
import com.exc.applibrary.main.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

public class SearchResultFragment extends Fragment {
    private RecyclerView fenqu_RecyclerView;
    private RecyclerView zhandian_RecyclerView;
    private RecyclerView build_RecyclerView;
    private RecyclerView node_RecyclerView;
    private SearchResultAdapter fenqu_adapter;
    private SearchResultAdapter zhandian_adapter;
    private SearchResultAdapter build_adapter;
    private SearchResultAdapter node_adapter;
    private View fenqu_headview;
    private View zhandian_headview;
    private View build_headview;
    private View node_headview;
    private TextView fenqutext;
    private TextView zhandiantext;
    private TextView buildtext;
    private TextView nodetext;
    private SelectBuildModel partition_selectBuildModel;
    private SelectBuildModel site_selectBuildModel;
    private SelectBuildModel build_selectBuildModel;
    private SelectBuildModel node_selectBuildModel;
    private boolean partition_select;
    private boolean site_select;
    private boolean build_select;

    private List<PatitionFindListModel.DataBean.PartitionListBean> partitionListBeanList;
    private List<PatitionFindListModel.DataBean.SiteListBean> siteListBeanList;
    private List<PatitionFindListModel.DataBean.BuildingListBean> buildingListBeanList;
    private List<ElectricityNodeModel.DataBean.ListBean> electricityListBeanList;

    private boolean fromScene;
    private boolean fromSceneRightDetail;
    private boolean fromLoopControlRight;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_search_result, container, false);

        fromScene = getActivity().getIntent().getBooleanExtra("fromScene", false);
        fromSceneRightDetail = getActivity(). getIntent().getBooleanExtra("fromSceneRightDetail", false);
        fromLoopControlRight = getActivity().getIntent().getBooleanExtra("fromLoopControlRight",false);
        partition_select = false;site_select = false;build_select = false;
        initMainView(root);
        initData();
        return root;
    }

    public void initData() {

    }

    public void initMainView(View rootView) {

        fenqu_adapter = new SearchResultAdapter();
        zhandian_adapter = new SearchResultAdapter();
        build_adapter = new SearchResultAdapter();
        node_adapter = new SearchResultAdapter();

        GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 1);
        GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        GridLayoutManager manager4 = new GridLayoutManager(getActivity(), 1);

        fenqu_RecyclerView = rootView.findViewById(R.id.fenqu_result_recycleview);
        fenqu_RecyclerView.setLayoutManager(manager1);
        fenqu_RecyclerView.setAdapter(fenqu_adapter);

        zhandian_RecyclerView = rootView.findViewById(R.id.zhandian_result_recycleview);
        zhandian_RecyclerView.setLayoutManager(manager2);
        zhandian_RecyclerView.setAdapter(zhandian_adapter);

        build_RecyclerView = rootView.findViewById(R.id.build_result_recycleview);
        build_RecyclerView.setLayoutManager(manager3);
        build_RecyclerView.setAdapter(build_adapter);

        node_RecyclerView = rootView.findViewById(R.id.node_result_recycleview);
        node_RecyclerView.setLayoutManager(manager4);
        node_RecyclerView.setAdapter(node_adapter);

        fenqu_headview = rootView.findViewById(R.id.fenqu_header);
        fenqu_headview.findViewById(R.id.fenqu_header_img).setBackgroundResource(R.mipmap.partition);
        fenqutext = fenqu_headview.findViewById(R.id.fenqu_content);
        fenqutext.setText("分区");

        zhandian_headview = rootView.findViewById(R.id.zhandian_header);
        zhandian_headview.findViewById(R.id.fenqu_header_img).setBackgroundResource(R.mipmap.site);
        zhandiantext = zhandian_headview.findViewById(R.id.fenqu_content);
        zhandiantext.setText("站点");

        build_headview = rootView.findViewById(R.id.build_header);
        build_headview.findViewById(R.id.fenqu_header_img).setBackgroundResource(R.mipmap.building);
        buildtext = build_headview.findViewById(R.id.fenqu_content);
        buildtext.setText("建筑");

        node_headview = rootView.findViewById(R.id.node_header);
        node_headview.findViewById(R.id.fenqu_header_img).setBackgroundResource(R.mipmap.node);
        nodetext = node_headview.findViewById(R.id.fenqu_content);
        nodetext.setText("节点");

        if(fromScene || fromSceneRightDetail){
            build_headview.setVisibility(View.GONE);
            build_RecyclerView.setVisibility(View.GONE);
        }

        if(fromLoopControlRight){
            node_headview.setVisibility(View.VISIBLE);
            node_RecyclerView.setVisibility(View.VISIBLE);
        }

        fenqu_headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(partitionListBeanList != null && partitionListBeanList.size() >3){
                    Intent intent = new Intent(getActivity(), MoreBuilddingActivity.class);
                    intent.putExtra("morepartitiondata", (Serializable)partitionListBeanList);
                    getActivity().startActivityForResult(intent,3);

                }else {
                    ToastUtils.showToast(getActivity(),"没有更多数据", false);
                }
            }
        });

        zhandian_headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(siteListBeanList !=null &&siteListBeanList.size() >3){
                    Intent intent = new Intent(getActivity(), MoreBuilddingActivity.class);
                    intent.putExtra("moresitedata", (Serializable)siteListBeanList);
                    getActivity().startActivityForResult(intent,2);

                }else {
                    ToastUtils.showToast(getActivity(),"没有更多数据", false);
                }
            }
        });


        build_headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buildingListBeanList!=null && buildingListBeanList.size() >3){
                    Intent intent = new Intent(getActivity(), MoreBuilddingActivity.class);
                    intent.putExtra("morebuilddata", (Serializable) buildingListBeanList);

                    getActivity().startActivityForResult(intent, 1);
                }else {
                    ToastUtils.showToast(getActivity(),"没有更多数据", false);
                }
            }
        });


        node_headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(electricityListBeanList!=null && electricityListBeanList.size() >3){
                    Intent intent = new Intent(getActivity(), MoreBuilddingActivity.class);
                    intent.putExtra("morenodedata", (Serializable) electricityListBeanList);

                    getActivity().startActivityForResult(intent, 4);
                }else {
                    ToastUtils.showToast(getActivity(),"没有更多数据", false);
                }
            }
        });

        //分区列表item点击
        fenqu_adapter.setOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text , int select_index) {
                partition_select = true;

                partition_selectBuildModel = new SelectBuildModel();
                partition_selectBuildModel.setPartition_id(partitionListBeanList.get(select_index).getId());
                partition_selectBuildModel.setPartition_name(select_text);
                if(build_select){
                    partition_selectBuildModel.setBuild_id(build_selectBuildModel.getBuild_id());
                    partition_selectBuildModel.setBuild_name(build_selectBuildModel.getBuild_name());
                }else if(site_select){
                    partition_selectBuildModel.setSite_id(site_selectBuildModel.getSite_id());
                    partition_selectBuildModel.setSite_name(site_selectBuildModel.getSite_name());
                }

                ((SearchBuildingActivity)getActivity()).changeSelectData(partition_selectBuildModel);
                ((SearchBuildingActivity)getActivity()).sendSelectData(partition_selectBuildModel,site_selectBuildModel,build_selectBuildModel,node_selectBuildModel);

            }
        });

        //站点列表item点击
        zhandian_adapter.setOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text , int select_index) {
                site_select = true;

                site_selectBuildModel = new SelectBuildModel();
                site_selectBuildModel.setPartition_id(siteListBeanList.get(select_index).getPartitionId());
                site_selectBuildModel.setSite_id(siteListBeanList.get(select_index).getId());
                site_selectBuildModel.setSite_name(siteListBeanList.get(select_index).getName());
                if(build_select){
                    site_selectBuildModel.setBuild_id(build_selectBuildModel.getBuild_id());
                    site_selectBuildModel.setBuild_name(build_selectBuildModel.getBuild_name());
                }
                ((SearchBuildingActivity)getActivity()).changeSelectData(site_selectBuildModel);
                ((SearchBuildingActivity)getActivity()).sendSelectData(partition_selectBuildModel,site_selectBuildModel,build_selectBuildModel,node_selectBuildModel);
            }
        });

        //建筑列表item点击
        build_adapter.setOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text , int select_index) {
                build_select = true;

                build_selectBuildModel = new SelectBuildModel();
                build_selectBuildModel.setPartition_id(buildingListBeanList.get(select_index).getPartitionId());
                build_selectBuildModel.setBuild_id(buildingListBeanList.get(select_index).getId());
                build_selectBuildModel.setBuild_name(buildingListBeanList.get(select_index).getName());
                ((SearchBuildingActivity)getActivity()).changeSelectData(build_selectBuildModel);
                ((SearchBuildingActivity)getActivity()).sendSelectData(partition_selectBuildModel,site_selectBuildModel,build_selectBuildModel,node_selectBuildModel);

            }
        });

        //节点列表item点击
        node_adapter.setOnItemClickListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int select_index) {
                node_selectBuildModel = new SelectBuildModel();
                node_selectBuildModel.setNode_id(electricityListBeanList.get(select_index).getId());
                node_selectBuildModel.setNode_name(select_text);
                ((SearchBuildingActivity)getActivity()).changeSelectData(node_selectBuildModel);
                ((SearchBuildingActivity)getActivity()).sendSelectData(partition_selectBuildModel,site_selectBuildModel,build_selectBuildModel,node_selectBuildModel);
            }
        });
    }

    public void refreshUI(List<PatitionFindListModel.DataBean.PartitionListBean> partitionListBeans, List<PatitionFindListModel.DataBean.SiteListBean> siteListBeans,List<PatitionFindListModel.DataBean.BuildingListBean> buildingListBeans,List<ElectricityNodeModel.DataBean.ListBean> electricityListBeans) {
        if(partitionListBeans.size() >0){
            partitionListBeanList = partitionListBeans;
            fenqu_adapter.refreshPartitionUI(partitionListBeans);
        }
        if(siteListBeans.size() >0){
            siteListBeanList = siteListBeans;
            zhandian_adapter.refreshSiteUI(siteListBeans);
        }
        if (buildingListBeans.size() >0){
            buildingListBeanList = buildingListBeans;
            build_adapter.refreshBindingUI(buildingListBeans);
        }
        if(electricityListBeans.size() >0){
            electricityListBeanList = electricityListBeans;
            node_adapter.refreshNodeUI(electricityListBeans);
        }
    }

}
