package com.exc.applibrary.main.ui.fragment;

import androidx.fragment.app.Fragment;


public class MapFragment extends Fragment
//        implements OnHttpResponseListener, AlertDialog.OnDialogButtonClickListener, MKOfflineMapListener

{
//    private com.baidu.mapapi.map.MapView mMapView;
//    private LocationClient mLocationClient;
//    private UiSettings mUiSettings;
//    private CheckBox mAllGesturesCB;
//    private CheckBox mZoomCB;
//    private CheckBox mOverlookCB;
//    private CheckBox mRotateCB;
//    private CheckBox mScrollCB;
//    private CheckBox mDoublezoomCB;
//    private BaiduMap mBaiduMap;
//    private OverlayOptions ooGround;
//    private DistrictSearch mDistrictSearch;
//    private GeoCoder mCoder;
//    private MyLocationListener myLocationListener;
//    private MKOfflineMapListener offlineMaplistener;
//    private ClusterManager<MyItem> mClusterManager;
//
//    private View root;
//    private View search_view;
//    private View buildbottom_mview;
//    private View sitebottom_view;
//    private View sitebottombar_view;
//    private View basetipview;
//    private View bottom_bar;
//    private ImageView image_search;
//    private TextView edit_search;
//    private BottomSheetDialog bottomSheetDialog;
//
//    //底视图相关
//    private View base_contentview;
//    private View base_swhitchview;
//    private View base_stopview;
//    private View base_changeview;
//    private View build_baseview;
//    private View bottom_buildinfo;
//    private View patition_baseview;
//    private View bottom_partitioninfo;
//    private TextView base_search;
//    private TextView tv_nametext;
//    private TextView tv_title;
//    private TextView tv_styletext;
//    private TextView tv_buildnum;
//    private TextView tv_onlinenum;
//    private TextView tv_offlinenum;
//    private TextView btn_open;
//    private TextView btn_close;
//    private TextView btn_videostop;
//    private TextView btn_videochange;
//    private CustomDialog customDialog;
//    private TextView btn_buildopen;
//    private TextView btn_buildclose;
//    private RecyclerView bottom_recycleView;
//    private VidelChannelAdapter videlChannelAdapter;
//    private TextView bottom_direction;
//    private ImageView bottom_img_direction;
//    private boolean bottom_direction_ishide;
//
//    private final int PARTITION_FINDLIST_CODE = 1;
//    private final int SITE_REQUEST_CODE = 2;
//    private final int SITE_BUILDING_CODE = 3;
//    private final int SITE_OPEN_CODE = 4;
//    private final int SITE_CLOSE_CODE = 5;
//    private final int SITE_STOP_CODE = 6;
//    private final int SITE_CHANGE_CODE = 7;
//    private final int BUILD_GET_CODE = 8;
//    private final int BUILD_OPEN_CODE = 9;
//    private final int BUILD_CLOSE_CODE = 10;
//    private final int PARTITION_OPEN_CODE = 11;
//    private final int PARTITION_CLOSE_CODE = 12;
//    private final int PARTITION_GET_CODE = 13;
//    private final int BUILDING_GET_CODR = 14;
//    private final int TS3_SCRIPTS_CODE = 15;
//    private final int STRATEGY_INTER_CODE = 16;
//    private final int CONTROL_STRATEGY_CODE = 17;
//    private final int CONTROL_PRGM_CODE = 18;
//    private final int LIGHT_CONTROL_CODE = 19;
//    private final int TS3_FAST_CODE = 20;
//    private final int ELECTRICITY_NODE_CODE = 21;
//    private final int DIALOG_SITE_OPEN_CODE = 100;
//    private final int DIALOG_SITE_CLOSE_CODE = 101;
//    private final int DIALOG_SITE_STOP_CODE = 102;
//    private final int DIALOG_PARTITION_OPEN_CODE = 103;
//    private final int DIALOG_PARTITION_CLOSE_CODE = 104;
//    private final int DIALOG_PARTITION_STOP_CODE = 105;
//
//
//    private BuildingModel buildingModel;
//    private BuildingDetailModel buildingDetailModel;
//    private SiteModel siteModel;
//    private PartitionDetailModel partitionDetailModel;
//    private PatitionFindListModel patitionFindListModel;
//    private VideoSropModel videoStopModel;
//    private StrategyInterModel strategyInterModel;
//    private ElectricityNodeModel electricityNodeModel;
//    private boolean isFirstComming;
//    private String toastMessage;
//
//    private BuildingDao buildingDao;
//    private SiteDao siteDao;
//    private PartitionDao partitionDao;
//    private ElectricityDao electricityDao;
//
//    //收到消息回主UI刷新界面
//    Handler myHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 111:
//                    customDialog.dismiss();
//                    showShortToast(getContext(),toastMessage);
//                    break;
//                case 222:
//                    siteDao.insert(patitionFindListModel.getData().getSiteList());
//                    buildingDao.insert(patitionFindListModel.getData().getBuildingList());
//                    partitionDao.insert(patitionFindListModel.getData().getPartitionList());
//
//                    break;
//                case 333:
//                    electricityDao.insert(electricityNodeModel.getData().getList());
//                    break;
//                default:
//                    break;
//            }
//
//            super.handleMessage(msg);
//        }
//
//    };
//
//    @Override
//    public void onGetOfflineMapState(int i, int i1) {
//
//    }
//
//    //ClusterItem接口的实现类
//    public class MyItem implements ClusterItem {
//        private final LatLng mPosition;//点
//        private Bundle buns;//00000000000000000000000000额外信息
//        private BuildingModel.DataBean.ListBean building;//建筑信息
//
//        public BuildingModel.DataBean.ListBean getBuilding() {
//            return building;
//        }
//
//        public MyItem(LatLng latLng, BuildingModel.DataBean.ListBean build) {
//            mPosition = latLng;
//            building = build;
//        }
//
//        @Override
//        public LatLng getPosition() {
//            return mPosition;
//        }
//
//        @Override
//        public Bundle getExtraInfo() {
//            return buns;
//        }
//
//        @Override
//        public BitmapDescriptor getBitmapDescriptor() {//点图标
//            int image = R.mipmap.newbuildlogo ;
//            return BitmapDescriptorFactory.fromResource(image);
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(BuildingModel model){
//        Log.e("通知", "刷新地图");
//        if(model != null){
//            markerList(model.getData().getList());
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(SelectBuildModel model){
//
//        basetipview = getActivity().findViewById(R.id.search_text);
//        base_search = getActivity().findViewById(R.id.base_search);
//        basetipview.setVisibility(View.INVISIBLE);
//        base_search.setVisibility(View.INVISIBLE);
//
//        if(!bottom_direction_ishide){
//            bottom_direction = getActivity().findViewById(R.id.bottom_direction);
//            bottom_img_direction = getActivity().findViewById(R.id.bottom_img_Direction);
//            bottom_direction.setVisibility(View.VISIBLE);
//            bottom_img_direction.setVisibility(View.VISIBLE);
//            bottom_direction_ishide = true;
//        }
//
//        if(model.getResultCode() == 100){//站点返回
//
//            HttpRequest.siteGet(String.valueOf(model.getSite_id()),SITE_REQUEST_CODE,this);
//            HttpRequest.getSiteBuilding("",String.valueOf(model.getSite_id()),0,0,SITE_BUILDING_CODE,this);
//
//        }else if(model.getResultCode() == 101){//建筑返回
//
//            HttpRequest.buildingGetHttp(String.valueOf(model.getBuild_id()),String.valueOf(model.getBuild_name()),BUILDING_GET_CODR,this);
//
//        }else if(model.getResultCode() == 102){//分区返回
//            HttpRequest.strategyinterHttp("1",model.getPartition_id(),STRATEGY_INTER_CODE,this);
//            HttpRequest.partitionGet(String.valueOf(model.getPartition_id()),PARTITION_GET_CODE,this);
//            HttpRequest.getSiteBuilding(String.valueOf(model.getPartition_id()),String.valueOf(model.getSite_id()),0,0,SITE_BUILDING_CODE,this);
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(String click){
//        if(click.length() >0){
//            bottom_direction = getActivity().findViewById(R.id.bottom_direction);
//            bottom_img_direction = getActivity().findViewById(R.id.bottom_img_Direction);
//            bottom_direction.setVisibility(View.INVISIBLE);
//            bottom_img_direction.setVisibility(View.INVISIBLE);
//        }
//    }
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        root = inflater.inflate(R.layout.fragment_map, container, false);
//        EventBus.getDefault().register(this);
//        isFirstComming = true;
//
//        initMapView();
//        initMainView();
//        initSiteBottomView();
//        initData();
//
//        return root;
//    }
//
//    public void initData(){
//        customDialogShow();
//        //数据库操作
//        partitionDao = WhAppDb.getInstance(getActivity()).getPartitionDao();
//        siteDao = WhAppDb.getInstance(getActivity()).getSiteDao();
//        buildingDao = WhAppDb.getInstance(getActivity()).getBuildingDao();
//        electricityDao = WhAppDb.getInstance(getActivity()).getElectricityDao();
//        HttpRequest.getPartitionFindlist(PARTITION_FINDLIST_CODE,this::onHttpResponse);
//        HttpRequest.electricitynodeHttp(ELECTRICITY_NODE_CODE,this::onHttpResponse);
//        HttpRequest.getSiteBuilding("","",0,0,SITE_BUILDING_CODE,this);
//    }
//
//    public void initMainView(){
//        customDialog = new CustomDialog(getActivity());
//    }
//
//
//    //构建地图
//    public void initMapView() {
//        mMapView = root.findViewById(R.id.bmapView);
//        mBaiduMap = mMapView.getMap();
//
//        mMapView.showScaleControl(false); // 标尺
//        mMapView.showZoomControls(false);//缩放控件
//
//        //初始化点聚合管理类
//        mClusterManager = new ClusterManager<MyItem>(getActivity(), mBaiduMap);
//
//        // 构建地图状态
//        MapStatus.Builder builder = new MapStatus.Builder();
//        // 默认 天安门
//        LatLng center = new LatLng(30.52, 114.31);
//        // 默认 11级
//        float zoom = 10.0f;
//
//        builder.target(center).zoom(zoom);
//        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(builder.build());
//
//        // 设置地图状态
//        mBaiduMap.setMapStatus(mapStatusUpdate);
//
//        BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
//            /**
//             * 地图单击事件回调函数
//             *
//             * @param point 点击的地理坐标
//             */
//            @Override
//            public void onMapClick(LatLng point) {
////                mBaiduMap.clear();
//            }
//
//            /**
//             * 地图内 Poi 单击事件回调函数
//             *
//             * @param mapPoi 点击的 poi 信息
//             */
//            @Override
//            public void onMapPoiClick(MapPoi mapPoi) {
//
//            }
//
//        };
//
//        BaiduMap.OnMapStatusChangeListener listener1 = new BaiduMap.OnMapStatusChangeListener() {
//            /**
//             * 手势操作地图，设置地图状态等操作导致地图状态开始改变。
//             *
//             * @param status 地图状态改变开始时的地图状态
//             */
//            @Override
//            public void onMapStatusChangeStart(MapStatus status) {
//                Log.i("sjfasj", "状态开始改变");
//            }
//
//            /**
//             * 手势操作地图，设置地图状态等操作导致地图状态开始改变。
//             *
//             * @param status 地图状态改变开始时的地图状态
//             *
//             * @param reason 地图状态改变的原因
//             */
//
//            //用户手势触发导致的地图状态改变,比如双击、拖拽、滑动底图
//            //int REASON_GESTURE = 1;
//            //SDK导致的地图状态改变, 比如点击缩放控件、指南针图标
//            //int REASON_API_ANIMATION = 2;
//            //开发者调用,导致的地图状态改变
//            //int REASON_DEVELOPER_ANIMATION = 3;
//            @Override
//            public void onMapStatusChangeStart(MapStatus status, int reason) {
//                Log.i("sjfasj", "ok");
//            }
//
//            /**
//             * 地图状态变化中
//             *
//             * @param status 当前地图状态
//             */
//            @Override
//            public void onMapStatusChange(MapStatus status) {
//                Log.i("sjfasj", "变化中");
//            }
//
//            /**
//             * 地图状态改变结束
//             *
//             * @param status 地图状态改变结束后的地图状态
//             */
//            @Override
//            public void onMapStatusChangeFinish(MapStatus status) {
//                Log.i("sjfasj", "改变结束");
//            }
//        };
//        //设置地图状态监听
//        mBaiduMap.setOnMapStatusChangeListener(listener1);
//
//
//        //设置地图单击事件监听
//        mBaiduMap.setOnMapClickListener(listener);
//
//        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
//        // 设置maker点击时的响应
//        mBaiduMap.setOnMarkerClickListener(mClusterManager);
//
//        //点击建筑物数量
//        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
//            @Override
//            public boolean onClusterClick(Cluster<MyItem> cluster) {
//                Toast.makeText(getContext(),
//                        "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();
//
//                LatLng latLng = new LatLng(cluster.getPosition().latitude, cluster.getPosition().longitude);
//                performZoomIn(latLng);
//
//                List<LatLng> points = new ArrayList<>();
//                for (MyItem item : cluster.getItems()) {
//                    //多边形顶点位置
//                    points.add(new LatLng(item.getPosition().latitude, item.getPosition().longitude));
//                }
//                //构造PolygonOptions
//                PolygonOptions mPolygonOptions = new PolygonOptions()
//                        .points(points)
//                        .fillColor(0xAAFFFF00) //填充颜色
//                        .stroke(new Stroke(5, 0xAA00FF00)); //边框宽度和颜色
//
//                return false;
//            }
//        });
//
//        //点击建筑物
//        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
//            @Override
//            public boolean onClusterItemClick(MyItem item) {
//                basetipview = getActivity().findViewById(R.id.search_text);
//                base_search = getActivity().findViewById(R.id.base_search);
//                basetipview.setVisibility(View.INVISIBLE);
//                base_search.setVisibility(View.INVISIBLE);
//
//                bottom_direction = getActivity().findViewById(R.id.bottom_direction);
//                bottom_img_direction = getActivity().findViewById(R.id.bottom_img_Direction);
//                bottom_direction.setVisibility(View.INVISIBLE);
//                bottom_img_direction.setVisibility(View.INVISIBLE);
//                bottom_direction_ishide = true;
//
//                HttpRequest.buildingGetHttp(String.valueOf(item.building.getId()),String.valueOf(item.building.getName()),BUILD_GET_CODE,MapFragment.this::onHttpResponse);
//                return false;
//            }
//        });
//
//    }
//
//    //定位当前位置
//    public class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //mapView 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null){
//                return;
//            }
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(location.getDirection()).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
//
//            //切换到当前坐标
//            MapStatus.Builder builder =new MapStatus.Builder();
//            MapStatus s = builder.target(new LatLng(locData.latitude,locData.longitude)).zoom(16).build();
//            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(s),1000);
//
//            //注销监听
//            mLocationClient.unRegisterLocationListener(myLocationListener);
//
//        }
//    }
//
//    //标记建筑位置
//    private void marker(List<LatLng> points, LatLngBounds.Builder builder1,List<BuildingModel.DataBean.ListBean> buildinglist) {
//        mClusterManager.mRenderer.setMinClusterSize(4);
//        mClusterManager.clearItems();
//
//        List<MyItem> items = new ArrayList<>();
//        for (int i = 0; i < points.size(); i++) {
//            BuildingModel.DataBean.ListBean build = buildinglist.get(i);
//            items.add(new MyItem(points.get(i), build));
//        }
//
//        mClusterManager.addItems(items);
//        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
//        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
//        // 设置maker点击时的响应
//        mBaiduMap.setOnMarkerClickListener(mClusterManager);
//
//        LatLngBounds latlngBounds = builder1.build();
//        MapStatusUpdate us = MapStatusUpdateFactory.newLatLngBounds(builder1.build());
//        MapStatusUpdateFactory.newLatLngBounds(latlngBounds, mMapView.getWidth(), mMapView.getHeight());
//        mBaiduMap.animateMapStatus(us);
//
//        if(isFirstComming){
//            LatLng latLng = new LatLng(30.52, 114.31);
//            MapStatus.Builder builder = new MapStatus.Builder();
//            builder.target(latLng).zoom(10.001f);
//            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//            isFirstComming = false;
//        }else{
//            LatLng latLng = new LatLng(points.get(0).latitude, points.get(0).longitude);
//            MapStatus.Builder builder = new MapStatus.Builder();
//            builder.target(latLng).zoom(12.001f);
//            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//        }
//    }
//
//    //获取建筑群坐标
//    public void markerList(List<BuildingModel.DataBean.ListBean> buildinglist) {
//
//        final List<LatLng> points = new ArrayList<>();
//        if (buildinglist != null && buildinglist.size() > 0) {
//            for (int i = 0; i < buildinglist.size(); i++) {
//                LatLng markerLatlng = new LatLng(buildinglist.get(i).getLatitude(), buildinglist.get(i).getLongitude());
//
//                CoordinateConverter converter = new CoordinateConverter()
//                        .from(CoordinateConverter.CoordType.COMMON)
//                        .coord(markerLatlng);
//                //转换坐标P
//                LatLng desLatLng = converter.convert();
//                points.add(desLatLng);
//            }
//            //将所有点的经纬度放在一个集合中。
//            LatLngBounds.Builder builder1 = new LatLngBounds.Builder();
//            for (LatLng p : points) {
//                builder1 = builder1.include(p);
//            }
//
//            marker(points, builder1 ,buildinglist);
//        }
//    }
//
//    //处理地图放大;
//    public void performZoomIn(LatLng latLng) {
//
//        float zoomMaxLevel = mBaiduMap.getMaxZoomLevel();
//        float zoomLevel = mBaiduMap.getMapStatus().zoom;
//
//        zoomLevel += 2;
//        if (zoomLevel > zoomMaxLevel) {
//            zoomLevel = zoomMaxLevel;
//        }
//
//        MapStatus.Builder builder = new MapStatus.Builder();
//        builder.target(latLng).zoom(zoomLevel);
//        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//    }
//
//    //构建botttom
//    public void initSiteBottomView(){
//        View search_test = root.findViewById(R.id.search_text);
//        search_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SearchBuildingActivity.class);
//                getActivity().startActivityForResult(intent,1);
//            }
//        });
//
//        sitebottom_view = View.inflate(getContext(), R.layout.activity_bottom_siteinfo, null);
//        //绑定activity_main布局文件中的布局项,其中R.id.lenearlay_1为布局文件中设置的id
//        LinearLayout linear=(LinearLayout)root.findViewById(R.id.layout_price_detail);
//        //添加文本,this代表当前项目
//        linear.addView(sitebottom_view);
//
//        image_search = sitebottom_view.findViewById(R.id.img_search);
//        image_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SearchBuildingActivity.class);
//                getActivity().startActivityForResult(intent,1);
//            }
//        });
//
//    };
//
//    //刷新建筑信息底视图
//    public void refreshBuildBottomUI(BuildingDetailModel model){
//
//        build_baseview = getActivity().findViewById(R.id.build_baseview);
//        bottom_buildinfo = getActivity().findViewById(R.id.bottom_buildinfo);
//        build_baseview.setVisibility(View.VISIBLE);
//        bottom_buildinfo.setVisibility(View.VISIBLE);
//
//        patition_baseview = getActivity().findViewById(R.id.partition_baseview);
//        bottom_partitioninfo = getActivity().findViewById(R.id.bottom_partitioninfo);
//        patition_baseview.setVisibility(View.INVISIBLE);
//        bottom_partitioninfo.setVisibility(View.INVISIBLE);
//
//        if(model.getData().getNodeList().size() >0){
//
//            GridLayoutManager bottom_manager = new GridLayoutManager(getActivity(),1);
//            bottom_recycleView = getActivity().findViewById(R.id.video_channel_recycleview);
//            bottom_recycleView.setLayoutManager(bottom_manager);
//            videlChannelAdapter = new VidelChannelAdapter();
//            videlChannelAdapter.setmDatas(model);
//            bottom_recycleView.setAdapter(videlChannelAdapter);
//
//            videlChannelAdapter.setOnItemClickListener(new VidelChannelAdapter.OnItemClickListener() {
//                @Override
//                public void onItemSiteClick(BuildingDetailModel.DataBean.NodeListBean bean, int video_style) {
//                    if(video_style == 1){
//                        Log.d("",bean.getName() + video_style);
//                        customDialogShow();
//                        HttpRequest.controlPrgmHttp(model.getData().getBuilding().getNum(),CONTROL_PRGM_CODE,MapFragment.this::onHttpResponse);
//                    }else if(video_style == 2){
//                        Log.d("",bean.getName() + video_style);
//                        buildshoePickView(bean,model);
//                    }else if(video_style == 3){
//                        buildshoePickView(bean,model);
//                        Log.d("",bean.getName() + video_style);
//                    }
//                }
//            });
//        }
//
//        tv_title = getActivity().findViewById(R.id.tv_title);
//        tv_nametext = getActivity().findViewById(R.id.build_nametext);
//        btn_buildopen = getActivity().findViewById(R.id.btn_buildopen);
//        btn_buildclose = getActivity().findViewById(R.id.btn_buildclose);
//
//        tv_title.setText("建筑信息");
//        tv_nametext.setText(model.getData().getBuilding().getName());
//
//        btn_buildopen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                customDialogShow();
//                HttpRequest.buildingChannelHttp(model.getData().getBuilding().getId(),0,1,BUILD_OPEN_CODE,MapFragment.this::onHttpResponse);
//            }
//        });
//
//        btn_buildclose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                customDialogShow();
//                HttpRequest.buildingChannelHttp(model.getData().getBuilding().getId(),0,0,BUILD_CLOSE_CODE,MapFragment.this::onHttpResponse);
//            }
//        });
//    }
//
//    //刷新站点底视图
//    public void refreshSiteBottomUI(SiteModel model){
//        siteModel = model;
//
//        build_baseview = getActivity().findViewById(R.id.build_baseview);
//        bottom_buildinfo = getActivity().findViewById(R.id.bottom_buildinfo);
//        build_baseview.setVisibility(View.INVISIBLE);
//        bottom_buildinfo.setVisibility(View.INVISIBLE);
//
//        patition_baseview = getActivity().findViewById(R.id.partition_baseview);
//        bottom_partitioninfo = getActivity().findViewById(R.id.bottom_partitioninfo);
//        patition_baseview.setVisibility(View.INVISIBLE);
//        bottom_partitioninfo.setVisibility(View.INVISIBLE);
//
//        base_contentview = getActivity().findViewById(R.id.baseview_content);
//        base_swhitchview = getActivity().findViewById(R.id.baseview_switch);
//        base_stopview = getActivity().findViewById(R.id.baseview_stop);
//        base_changeview = getActivity().findViewById(R.id.baseview_change);
//        tv_nametext = getActivity().findViewById(R.id.tv_nametext);
//        tv_styletext = getActivity().findViewById(R.id.tv_styletext);
//        tv_buildnum = getActivity().findViewById(R.id.tv_buildnum);
//        tv_onlinenum = getActivity().findViewById(R.id.tv_onlinenum);
//        tv_offlinenum = getActivity().findViewById(R.id.tv_offlinenum);
//        btn_open = getActivity().findViewById(R.id.btn_open);
//        btn_close = getActivity().findViewById(R.id.btn_close);
//        btn_videostop = getActivity().findViewById(R.id.btn_videostop);
//        btn_videochange = getActivity().findViewById(R.id.btn_videochange);
//        tv_title = getActivity().findViewById(R.id.tv_title);
//
//        tv_title.setText("站点信息");
//        tv_nametext.setText(model.getData().getSite().getName());
//        tv_styletext.setText(model.getData().getSite().getTypeName());
//        tv_buildnum.setText(String.valueOf(model.getData().getSite().getOnlineNum() + model.getData().getSite().getOfflineNum()));
//        tv_onlinenum.setText(String.valueOf(model.getData().getSite().getOnlineNum()));
//        tv_offlinenum.setText(String.valueOf(model.getData().getSite().getOfflineNum()));
//
//        btn_open.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog dialog = new AlertDialog(getContext(), "操作提示", "确定进行开关操作？", true, DIALOG_SITE_OPEN_CODE, MapFragment.this::onDialogButtonClick);
//                dialog.show();
//            }
//        });
//        btn_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog dialog = new AlertDialog(getContext(), "操作提示", "确定进行开关操作？", true, DIALOG_SITE_OPEN_CODE, MapFragment.this::onDialogButtonClick);
//                dialog.show();
//            }
//        });
//
//        btn_videostop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                customDialogShow();
//                HttpRequest.getStopSite(model.getData().getSite().getId(),SITE_STOP_CODE,MapFragment.this::onHttpResponse);
//            }
//        });
//
//        btn_videochange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                siteshoePickView(model);
//            }
//        });
//
//    }
//
//    //刷新分区信息
//    public void refreshPartitionBottomUI(PartitionDetailModel model){
//        partitionDetailModel = model;
//
//        build_baseview = getActivity().findViewById(R.id.build_baseview);
//        bottom_buildinfo = getActivity().findViewById(R.id.bottom_buildinfo);
//        build_baseview.setVisibility(View.INVISIBLE);
//        bottom_buildinfo.setVisibility(View.INVISIBLE);
//
//        patition_baseview = getActivity().findViewById(R.id.partition_baseview);
//        bottom_partitioninfo = getActivity().findViewById(R.id.bottom_partitioninfo);
//        patition_baseview.setVisibility(View.VISIBLE);
//        bottom_partitioninfo.setVisibility(View.VISIBLE);
//
//        tv_title = getActivity().findViewById(R.id.tv_title);
//        tv_nametext = getActivity().findViewById(R.id.tv_partitionnametext);
//        tv_buildnum = getActivity().findViewById(R.id.tv_partition_buildnum);
//        tv_onlinenum = getActivity().findViewById(R.id.tv_partition_onlinenum);
//        tv_offlinenum = getActivity().findViewById(R.id.tv_partition_offlinenum);
//        btn_open = getActivity().findViewById(R.id.btn_partition_open);
//        btn_close = getActivity().findViewById(R.id.btn_partition_close);
//        btn_videostop = getActivity().findViewById(R.id.btn_partition_videostop);
//        btn_videochange = getActivity().findViewById(R.id.btn_partition_videochange);
//
//        tv_title.setText("分区信息");
//        tv_nametext.setText(model.getData().getPartition().getName());
//        tv_buildnum.setText(String.valueOf(model.getData().getPartition().getBuildingCount()));
//        tv_onlinenum.setText(String.valueOf(model.getData().getPartition().getOnlineNum()));
//        tv_offlinenum.setText(String.valueOf(model.getData().getPartition().getOfflineNum()));
//
//        btn_open.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog dialog = new AlertDialog(getContext(), "操作提示", "确定进行开关操作？", true, DIALOG_PARTITION_OPEN_CODE, MapFragment.this::onDialogButtonClick);
//                dialog.show();
//            }
//        });
//        btn_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog dialog = new AlertDialog(getContext(), "操作提示", "确定进行开关操作？", true, DIALOG_PARTITION_CLOSE_CODE, MapFragment.this::onDialogButtonClick);
//                dialog.show();
//            }
//        });
//
//        btn_videostop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                partitionshoePickView(true);
//            }
//        });
//
//        btn_videochange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                partitionshoePickView(false);
//            }
//        });
//    }
//
//    //弹框
//    public void onDialogButtonClick(int requestCode, boolean isPositive) {
//        if (! isPositive) {
//            return;
//        }
//
//        switch (requestCode) {
//            case DIALOG_SITE_OPEN_CODE:
//                customDialogShow();
//                HttpRequest.getSiteChannel(0,siteModel.getData().getSite().getId(),1,SITE_OPEN_CODE,this::onHttpResponse);
//                break;
//
//            case DIALOG_SITE_CLOSE_CODE:
//                customDialogShow();
//                HttpRequest.getSiteChannel(0,siteModel.getData().getSite().getId(),0,SITE_CLOSE_CODE,this::onHttpResponse);
//                break;
//            case DIALOG_PARTITION_OPEN_CODE:
//                customDialogShow();
//                HttpRequest.partitionChannelHttp(0,partitionDetailModel.getData().getPartition().getId(),1,PARTITION_OPEN_CODE,this::onHttpResponse);
//                break;
//            case DIALOG_PARTITION_CLOSE_CODE:
//                customDialogShow();
//                HttpRequest.partitionChannelHttp(0,partitionDetailModel.getData().getPartition().getId(),0,PARTITION_CLOSE_CODE,this::onHttpResponse);
//                break;
//            default:
//        }
//    }
//
//    //建筑选择框
//    public void buildshoePickView(BuildingDetailModel.DataBean.NodeListBean bean ,BuildingDetailModel model){
//        ArrayList<String> list = new ArrayList<>();
//        for (BuildingDetailModel.DataBean.NodeListBean.VideoListBean videoListBean : bean.getVideoList()) {
//            list.add(videoListBean.getVidName());
//        }
//        if(list.size()<=0){
//            showShortToast(getContext(),"该建筑暂无策略");
//            return;
//        }
//
//        SinglePicker picker = new SinglePicker(getActivity(), list);
//        picker.setCanLoop(false); //不禁用循环
//        picker.setTopBackgroundColor(-0x111112);
//        picker.setTopHeight(50);
//        picker.setGravity(Gravity.BOTTOM);
//        picker.setTitleText("请选择" );
//        picker.setTitleTextColor(-0x666667);
//        picker.setTitleTextSize(14);
//        picker.setCancelTextColor(-0xcc4a1b);
//        picker.setCancelTextSize(15);
//        picker.setSubmitTextColor(-0xcc4a1b);
//        picker.setSubmitTextSize(15);
//        picker.setSelectedTextColor(-0x120000);
//        picker.setUnSelectedTextColor(-0x666667);
//        LineConfig config = new LineConfig();
//        config.setColor(Color.GRAY);  //线颜色
//        config.setAlpha(120); //线透明度
//        config.setVisible(true);
//
////        config.setRatio(1);//线比率
//        //        config.setRatio(1);//线比率
//        picker.setLineConfig(config);
//        picker.setItemWidth(300);
//        picker.setBackgroundColor(-0x1e1e1f);
//        picker.setSelectedIndex(0);
//
//        picker.setOnItemPickListener(new OnItemPickListener() {
//            @Override
//            public void onItemPicked(int index, Object item) {
//
//                String frame = String.valueOf(bean.getVideoList().get(index).getFrameNumber());
//                String durations[] = bean.getVideoList().get(index).getDuration().split(":");
//                String videoName = bean.getVideoList().get(index).getVidName();
//                String duration = String.valueOf(getDuration(durations));
//
//                String content = getxmldata(frame,duration,videoName,"0");
//                String partition_id = String.valueOf(model.getData().getBuilding().getPartitionId());
//                String sns = String.valueOf(bean.getNum());
//                customDialogShow();
//                HttpRequest.iotapifilests3scripts(content,"快速策略",partition_id,"",sns,TS3_SCRIPTS_CODE,MapFragment.this::onHttpResponse);
//            }
//        });
//        picker.show();
//    }
//
//    public void siteshoePickView(SiteModel siteModel){
//        ArrayList<String> list = new ArrayList<>();
//        for (SiteModel.DataBean.VideolistBean videolistBean : siteModel.getData().getVideolist()) {
//            list.add(videolistBean.getName());
//        }
//        if(list.size()<=0){
//            showShortToast(getContext(),"该分区暂无策略");
//            return;
//        }
//
//        SinglePicker picker = new SinglePicker(getActivity(), list);
//        picker.setCanLoop(false); //不禁用循环
//        picker.setTopBackgroundColor(-0x111112);
//        picker.setTopHeight(50);
//        picker.setGravity(Gravity.BOTTOM);
//        picker.setTitleText("请选择" );
//        picker.setTitleTextColor(-0x666667);
//        picker.setTitleTextSize(14);
//        picker.setCancelTextColor(-0xcc4a1b);
//        picker.setCancelTextSize(15);
//        picker.setSubmitTextColor(-0xcc4a1b);
//        picker.setSubmitTextSize(15);
//        picker.setSelectedTextColor(-0x120000);
//        picker.setUnSelectedTextColor(-0x666667);
//        LineConfig config = new LineConfig();
//        config.setColor(Color.GRAY);  //线颜色
//        config.setAlpha(120); //线透明度
//        config.setVisible(true);
//
//        picker.setLineConfig(config);
//        picker.setItemWidth(300);
//        picker.setBackgroundColor(-0x1e1e1f);
//        picker.setSelectedIndex(0);
//
//        picker.setOnItemPickListener(new OnItemPickListener() {
//            @Override
//            public void onItemPicked(int index, Object item) {
//                customDialogShow();
//
//                String frame = String.valueOf(siteModel.getData().getVideolist().get(index).getFrameNumber());
//                String duration = siteModel.getData().getVideolist().get(index).getDuration();
//                String videoName = siteModel.getData().getVideolist().get(index).getName();
//
//                String content = getxmldata(frame,duration,videoName,"1");
//                String partition_id = String.valueOf(siteModel.getData().getSite().getPartitionId());
//                String sns = String.valueOf(siteModel.getData().getNode());
//                String site_id = String.valueOf(siteModel.getData().getSite().getId());
//                HttpRequest.ts3ScriptsFastHttp(content,"快速策略",partition_id,site_id,sns,TS3_FAST_CODE,MapFragment.this::onHttpResponse);
//            }
//        });
//        picker.show();
//    }
//    //分区选择框
//    public void partitionshoePickView(boolean is_sttop){
//        ArrayList<String> list = new ArrayList<>();
//        for (StrategyInterModel.DataBean.ListBean listBean : strategyInterModel.getData().getList()) {
//            list.add(listBean.getStrategyName());
//        }
//        if(list.size()<=0){
//            showShortToast(getContext(),"该分区暂无策略");
//            return;
//        }
//
//        SinglePicker picker = new SinglePicker(getActivity(), list);
//        picker.setCanLoop(false); //不禁用循环
//        picker.setTopBackgroundColor(-0x111112);
//        picker.setTopHeight(50);
//        picker.setGravity(Gravity.BOTTOM);
//        picker.setTitleText("请选择" );
//        picker.setTitleTextColor(-0x666667);
//        picker.setTitleTextSize(14);
//        picker.setCancelTextColor(-0xcc4a1b);
//        picker.setCancelTextSize(15);
//        picker.setSubmitTextColor(-0xcc4a1b);
//        picker.setSubmitTextSize(15);
//        picker.setSelectedTextColor(-0x120000);
//        picker.setUnSelectedTextColor(-0x666667);
//        LineConfig config = new LineConfig();
//        config.setColor(Color.GRAY);  //线颜色
//        config.setAlpha(120); //线透明度
//        config.setVisible(true);
//
//        picker.setLineConfig(config);
//        picker.setItemWidth(300);
//        picker.setBackgroundColor(-0x1e1e1f);
//        picker.setSelectedIndex(0);
//
//        picker.setOnItemPickListener(new OnItemPickListener() {
//            @Override
//            public void onItemPicked(int index, Object item) {
//                customDialogShow();
//                if(is_sttop){
//                    HttpRequest.lightcontrolHttp(String.valueOf(strategyInterModel.getData().getList().get(index).getId()),"1",LIGHT_CONTROL_CODE,MapFragment.this::onHttpResponse);
//                }else {
//                    HttpRequest.controlstrategyHttp(strategyInterModel.getData().getList().get(index).getStrategyNum(),strategyInterModel.getData().getList().get(index).getStrategyType(),CONTROL_STRATEGY_CODE,MapFragment.this::onHttpResponse);
//                }
//            }
//        });
//        picker.show();
//    }
//    public String getxmldata(String frame , String duration , String videoName , String IsFile){
//
//        String y_str = gettimeData(true);
//        String h_str = gettimeData(false);
//
//        XmlSerializer serializer = Xml.newSerializer();
//        OutputStream out = new ByteArrayOutputStream();
//        String xml = "";
//        try {
//            serializer.setOutput(out,"UTF-8");
//
//            serializer.startTag(null,"Programs");
//            serializer.startTag(null,"Program");
//
//            insertXMl(serializer,"ID","111");
//            insertXMl(serializer,"Name","FastProgram");
//            insertXMl(serializer,"IsTimer","1");
//
//            serializer.startTag(null, "Files");
//            serializer.startTag(null, "File");
//
//            insertXMl(serializer,"Name",videoName);
//            insertXMl(serializer,"Duration",duration);
//            insertXMl(serializer,"Frames",frame);
//            insertXMl(serializer,"Audio","0");
//            insertXMl(serializer,"IsFile",IsFile);
//            insertXMl(serializer,"XAxis","0");
//            insertXMl(serializer,"YAxis","0");
//            insertXMl(serializer,"Width","0");
//            insertXMl(serializer,"Height","0");
//            insertXMl(serializer,"Transparent","0");
//            insertXMl(serializer,"Volume","0");
//            insertXMl(serializer,"PlayRate","0");
//            insertXMl(serializer,"StartFrame","0");
//            insertXMl(serializer,"StopFrame","0");
//            insertXMl(serializer,"FrameAdvance","0");
//            insertXMl(serializer,"TopCut","0");
//            insertXMl(serializer,"LeftCut","0");
//            insertXMl(serializer,"RightCut","0");
//            insertXMl(serializer,"BottomCut","0");
//
//            serializer.endTag(null, "File");
//            serializer.endTag(null, "Files");
//
//
//
//            serializer.startTag(null, "Timers");
//            serializer.startTag(null, "Timer");
//
//            insertXMl(serializer,"IsImmediatePlay","1");
//            insertXMl(serializer,"ByYear","1");
//            insertXMl(serializer,"ByMonth","1");
//            insertXMl(serializer,"ByDay","1");
//            insertXMl(serializer,"ByTime","1");
//            insertXMl(serializer,"StartDate",y_str);
//            insertXMl(serializer,"StopDate",y_str);
//            insertXMl(serializer,"StartTime",h_str);
//            insertXMl(serializer,"StopTime","23:59:59");
//            insertXMl(serializer,"ByWeek","0");
//            insertXMl(serializer,"BySunday","0");
//            insertXMl(serializer,"ByMonday","0");
//            insertXMl(serializer,"ByTuesday","0");
//            insertXMl(serializer,"ByWednesday","0");
//            insertXMl(serializer,"ByThursday","0");
//            insertXMl(serializer,"ByFriday","0");
//            insertXMl(serializer,"BySaturday","0");
//
//            serializer.startTag(null, "Festivals");
//            serializer.startTag(null, "Festival");
//            serializer.startTag(null, "Type");
//            serializer.endTag(null, "Type");
//
//            insertXMl(serializer,"AppointTime","0");
//            insertXMl(serializer,"BeginTime","00:00:00");
//            insertXMl(serializer,"EndTime","00:00:00");
//
//            serializer.endTag(null, "Festival");
//            serializer.endTag(null, "Festivals");
//
//            serializer.endTag(null, "Timer");
//            serializer.endTag(null, "Timers");
//
//            serializer.endTag(null,"Program");
//            serializer.endTag(null,"Programs");
//
//            serializer.endDocument();
//
//            xml = out.toString();
//            Log.d("8888",xml);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return xml;
//    }
//
//    public double getDuration(String durations[]){
//        double duration = 0;
//        if(durations.length ==3){
//            String h = durations[0];
//            String m = durations[1];
//            String s = durations[2];
//
//            double hh = convertToDouble(h,2015);
//            double mm = convertToDouble(m,2015);
//            double ss = convertToDouble(s,2015);
//
//            duration = hh*60*60*1000 + mm*60*1000 + ss*1000;
//        }
//        return duration;
//    }
//
//    //获取时间
//    public String gettimeData(boolean is_year){
//
//        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd"); //设置时间格式
//        formatter.setTimeZone(TimeZone.getTimeZone("GMT+08")); //设置时区
//        Date curDate = new Date(System.currentTimeMillis()); //获取当前时间
//        String y_createDate = formatter.format(curDate);   //格式转换
//
//        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss"); //设置时间格式
//        formatter1.setTimeZone(TimeZone.getTimeZone("GMT+08")); //设置时区
//        Date curDate1 = new Date(System.currentTimeMillis()); //获取当前时间
//        String h_createDate = formatter1.format(curDate1);   //格式转换
//
//        return is_year?y_createDate:h_createDate;
//    }
//
//    //把String转化为double
//    public static double convertToDouble(String number, double defaultValue) {
//        if (TextUtils.isEmpty(number)) {
//            return defaultValue;
//        }
//        try {
//            return Double.parseDouble(number);
//        } catch (Exception e) {
//            return defaultValue;
//        }
//    }
//
//    public void insertXMl(XmlSerializer serializer , String name ,String text){
//        try {
//            serializer.startTag(null,name);
//            serializer.text(text);
//            serializer.endTag(null,name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
//
//        if (null == resultJson) {
//            showShortToast(getContext(),"请求异常，请检查网络");
//            return;
//        }
//
//        if(requestCode == PARTITION_FINDLIST_CODE){
//            if (null == resultJson) {
//                showShortToast(getContext(),"请求异常，请检查网络");
//                return;
//            }else {
//                patitionFindListModel = JsonUtils.parseObject(resultJson, PatitionFindListModel.class);
//                if(patitionFindListModel.getCode() == 200){
//                    myHandler.sendEmptyMessage(222);
//                }else {
//                    showShortToast(getContext(),patitionFindListModel.getMessage());
//                }
//            }
//
//        }else if (requestCode == SITE_BUILDING_CODE){
//           customDialog.dismiss();
//            if (null == resultJson) {
//
//                return;
//            }else {
//                buildingModel = JsonUtils.parseObject(resultJson , BuildingModel.class);
//                if(buildingModel.getCode() == 200){
//                    markerList(buildingModel.getData().getList());
//                }else {
//                    showShortToast(getContext(),buildingModel.getMessage());
//                }
//            }
//
//        }else if(requestCode == BUILD_GET_CODE){
//           customDialog.dismiss();
//           if (null == resultJson) {
//
//               return;
//           }else {
//               buildingDetailModel = JsonUtils.parseObject(resultJson , BuildingDetailModel.class);
//               if(buildingDetailModel.getCode() == 200){
//                   BottomDrawerLayout layout =  getActivity().findViewById(R.id.bottom_drawer_layout);;
//                   layout.maximize();
//                   refreshBuildBottomUI(buildingDetailModel);
//               }else {
//
//               }
//           }
//       }else if(requestCode == BUILD_OPEN_CODE || requestCode == BUILD_CLOSE_CODE){
//           if (null == resultJson) {
//               return;
//           }else {
//               JSONObject jsonObj = JSONObject.parseObject(resultJson);
//               String code = jsonObj.getString("code");
//               String message = jsonObj.getString("message");
//               if(code.equals("200")){
//                   toastMessage = (requestCode == BUILD_OPEN_CODE)?"开启成功":"关闭成功";
//               }else {
//                   toastMessage = message;
//               }
//
//               myHandler.sendEmptyMessage(111);
//           }
//       }else if (requestCode == SITE_OPEN_CODE){
//           if (null == resultJson) {
//               toastMessage = "请求异常，请检查网络";
//               myHandler.sendEmptyMessage(SITE_OPEN_CODE);
//               return;
//           }else {
//               JSONObject jsonObj = JSONObject.parseObject(resultJson);
//               String code = jsonObj.getString("code");
//               String message = jsonObj.getString("message");
//               if(code.toString() == "200"){
//                   toastMessage = "开启成功";
//               }else {
//                   toastMessage = message;
//               }
//
//               myHandler.sendEmptyMessage(111);
//           }
//       }else if (requestCode == SITE_CLOSE_CODE){
//           if (null == resultJson) {
//               toastMessage = "请求异常，请检查网络";
//               myHandler.sendEmptyMessage(SITE_CLOSE_CODE);
//               return;
//
//           }else {
//               JSONObject jsonObj = JSONObject.parseObject(resultJson);
//               String code = jsonObj.getString("code");
//               String message = jsonObj.getString("message");
//               if(code.toString() == "200"){
//                   toastMessage = "关闭成功";
//               }else {
//                   toastMessage = message;
//               }
//
//               myHandler.sendEmptyMessage(111);
//           }
//       }else if (requestCode == SITE_STOP_CODE){
//           customDialog.dismiss();
//           if (null == resultJson) {
//               showShortToast(getContext(),"请求异常，请检查网络");
//               return;
//           }else {
//               videoStopModel = JsonUtils.parseObject(resultJson,VideoSropModel.class);
//
//               if(videoStopModel != null && videoStopModel.getData() != null){
//                   int code = videoStopModel.getCode();
//                   String str0 = videoStopModel.getReturnMsg().getErrDesc();
//                   String str1 = "站点下节点数：" + videoStopModel.getData().getDefaultNum();
//                   String str2 = "成功节点数：" + videoStopModel.getData().getSuccessNum();
//
//                   if(code == 1){
//                       AlertDialog dialog = new AlertDialog(getContext(), "节目关闭", str0+"\n"+str1+"\n"+str2+"\n", false, DIALOG_SITE_STOP_CODE, this::onDialogButtonClick);
//                       dialog.show();
//
//                   }else {
//                       showShortToast(getContext(),str0);
//                   }
//               }else {
//                   showShortToast(getContext(),"数据异常");
//               }
//           }
//       }else if(requestCode == PARTITION_OPEN_CODE || requestCode == PARTITION_CLOSE_CODE){
//           if (null == resultJson) {
//               return;
//           }else {
//               JSONObject jsonObj = JSONObject.parseObject(resultJson);
//               String code = jsonObj.getString("code");
//               String message = jsonObj.getString("message");
//               if(code.toString() == "200"){
//                   toastMessage = (requestCode == PARTITION_OPEN_CODE)?"开启成功":"关闭成功";
//               }else {
//                   toastMessage = (requestCode == PARTITION_OPEN_CODE)?"开启失败":"关闭失败";
//               }
//
//               myHandler.sendEmptyMessage(111);
//           }
//       }else if(requestCode == PARTITION_GET_CODE){
//           customDialog.dismiss();
//           if (null == resultJson) {
//               showShortToast(getContext(),"请求异常，请检查网络");
//               return;
//           }else {
//               PartitionDetailModel partitionDetailModel = JsonUtils.parseObject(resultJson , PartitionDetailModel.class);
//               if(partitionDetailModel.getCode() == 200){
//
//                   refreshPartitionBottomUI(partitionDetailModel);
//               }else {
//
//               }
//           }
//       }else if(requestCode == SITE_REQUEST_CODE){
//           if (null == resultJson) {
//               showShortToast(getContext(),"请求异常，请检查网络");
//               return;
//           }else {
//               SiteModel siteModel = JsonUtils.parseObject(resultJson , SiteModel.class);
//               if(siteModel.getCode() == 200){
//
//                   refreshSiteBottomUI(siteModel);
//               }else {
//                   showShortToast(getContext(),siteModel.getMessage());
//               }
//           }
//       }else if(requestCode == BUILDING_GET_CODR){
//           customDialog.dismiss();
//           if (null == resultJson) {
//               showShortToast(getContext(),"请求异常，请检查网络");
//               return;
//           }else {
//               BuildingDetailModel buildingDetailModel = JsonUtils.parseObject(resultJson , BuildingDetailModel.class);
//               if(buildingDetailModel.getCode() == 200){
//
//                   if(buildingDetailModel != null){
//                       refreshBuildBottomUI(buildingDetailModel);
//                       List<BuildingModel.DataBean.ListBean> buildinglist = new ArrayList<>();
//                       BuildingModel.DataBean.ListBean bean = new BuildingModel.DataBean.ListBean();
//                       bean.setId(buildingDetailModel.getData().getBuilding().getId());
//                       bean.setLatitude(buildingDetailModel.getData().getBuilding().getLatitude());
//                       bean.setLongitude(buildingDetailModel.getData().getBuilding().getLongitude());
//                       bean.setName(buildingDetailModel.getData().getBuilding().getName());
//                       buildinglist.add(bean);
//                       markerList(buildinglist);
//                   }
//               }else {
//                   showShortToast(getContext(),buildingDetailModel.getMessage());
//               }
//           }
//       }else if(requestCode == TS3_SCRIPTS_CODE || requestCode == TS3_FAST_CODE){
////            ScriptModel scriptModel = JsonUtils.parseObject(resultJson,ScriptModel.class);
////            if(scriptModel.getReturnCode() == 1){
////                toastMessage = "下发失败";
////            }else {
////                toastMessage = "下发成功";
////            }
//
//            toastMessage = "下发失败";
//            myHandler.sendEmptyMessage(111);
//        }else if(requestCode == STRATEGY_INTER_CODE){
//            strategyInterModel = JsonUtils.parseObject(resultJson , StrategyInterModel.class);
//            if(strategyInterModel.getCode() == 200){
//
//            }else {
//
//            }
//        }else if(requestCode == CONTROL_STRATEGY_CODE){
//            JSONObject jsonObj = JSONObject.parseObject(resultJson);
//            String code = jsonObj.getString("code");
//            String message = jsonObj.getString("message");
//            if(code.equals("200")){
//                toastMessage = "下发成功";
//            }else {
//                toastMessage = message;
//            }
//
//            myHandler.sendEmptyMessage(111);
//        }else if(requestCode == CONTROL_PRGM_CODE){
//           customDialog.dismiss();
//
//            toastMessage = "停止失败";
//            myHandler.sendEmptyMessage(111);
//        }else if(requestCode == LIGHT_CONTROL_CODE){
//            if (null == resultJson) {
//                showShortToast(getContext(),"请求异常，请检查网络");
//                return;
//            }else {
//                JSONObject jsonObj = JSONObject.parseObject(resultJson);
//                String code = jsonObj.getString("code");
//                String message = jsonObj.getString("message");
//                if(code.equals("200")){
//                    toastMessage = "停止成功";
//                }else {
//                    toastMessage = message;
//                }
//
//                myHandler.sendEmptyMessage(111);
//            }
//        }else if(requestCode == ELECTRICITY_NODE_CODE){
//            electricityNodeModel = JsonUtils.parseObject(resultJson , ElectricityNodeModel.class);
//            if(electricityNodeModel.getCode() == 200){
//                myHandler.sendEmptyMessage(333);
//            }else {
//
//            }
//        }
//    }
//
//    //地图生命周期三个方法
//    @Override
//    public void onResume() {
//        super.onResume();
//        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
////        mMapView.onResume();
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
////        mMapView.onPause();
//    }
//    @Override
//    public void onDestroy() {
//
//        super.onDestroy();
//    }
//
//    //加载提示
//    public void customDialogShow(){
//        if (null != customDialog) {
//            customDialog.dismiss();
//            customDialog = new CustomDialog(getContext());
//            customDialog.show();
//        }
//    }
}