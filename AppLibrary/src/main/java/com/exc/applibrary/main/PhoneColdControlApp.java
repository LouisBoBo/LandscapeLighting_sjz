package com.exc.applibrary.main;

import com.blankj.utilcode.util.Utils;
import com.hikvision.open.hikvideoplayer.HikVideoPlayerFactory;

import zuo.biao.library.base.BaseApplication;

public class PhoneColdControlApp extends BaseApplication {
    public static PhoneColdControlApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        在使用SDK各组件之前初始化context信息，传入ApplicationContext
//        SDKInitializer.initialize(this);
        //TODO: enableLog：在debug模式下打开日志，release关闭日志
        //TODO: 现阶段 appKey 不需要，直接传 null
        HikVideoPlayerFactory.initLib(null, true);
        Utils.init(this);

        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
//        SDKInitializer.setCoordType(CoordType.GCJ02);
    }
}
