package com.exc.applibrary.main;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.fragment.app.Fragment;

import com.exc.applibrary.R;
import com.exc.applibrary.main.ui.fragment.DashboardFragment;
import com.exc.applibrary.main.ui.fragment.MyFragment;
import com.exc.applibrary.main.ui.fragment.ShouYeFragment;
import com.exc.applibrary.main.ui.fragment.TaiYaunDashboardFragment;
import com.exc.applibrary.main.utils.PackageNameList;
import com.exc.applibrary.main.utils.SystemUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import zuo.biao.library.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private ArrayList<Fragment> mFragmentList;
//    private MKOfflineMap mOffline;
    private int cityID;
    com.alifei.bottombar.BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        copyToDevice(this);
//        initOffLineMap();

        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {
        mBottomBar = findView(R.id.bottom_bar);

    }

    @Override
    public void initData() {
        mFragmentList = new ArrayList<>();
//        mFragmentList.add(new MapFragment());

        if (SystemUtils.getAppProcessName(getActivity()).equals(PackageNameList.TaiYaun)) {
            mFragmentList.add(new TaiYaunDashboardFragment());
        } else if (SystemUtils.getAppProcessName(getActivity()).equals(PackageNameList.WuHan)) {
            mFragmentList.add(new DashboardFragment());

        }

        mFragmentList.add(new MyFragment());
        mBottomBar.setSelectedIcon(33f, 41f,
//                R.mipmap.mapdj,
                R.mipmap.workdj,
                R.mipmap.mydj
        );
        mBottomBar.setUnSelectedIcon(33f, 40f,
//                R.mipmap.mapbase,
                R.mipmap.workbase,
                R.mipmap.mybase
        );


        mBottomBar.setTextsize(0f);
//        mBottomBar.setTitle(R.color.home_tab_select, R.color.home_tab_un_select,
//                "地图", "工作台", "我的");
        mBottomBar.showContent(getSupportFragmentManager(), R.id.fl_main, mFragmentList);


    }


    @Override
    public void initEvent() {

    }


    //构建离线地图
//    public void initOffLineMap() {
//
//        mOffline = new MKOfflineMap();
//        MKOfflineMapListener offlineMaplistener = new MKOfflineMapListener() {
//            @Override
//            public void onGetOfflineMapState(int i, int i1) {
//
//            }
//        };
//        // 传入MKOfflineMapListener，离线地图状态发生改变时会触发该回调
//        mOffline.init(offlineMaplistener);
//
//    }

    //将离线文件写入设备
    public void copyToDevice(Context context) {
        try {

            // 这个数组用来存放离线地图文件的名称
            String fileName[] = {
                    "wuhan_218.dat",
                    "quanguogailue.dat",
                    "DVUserdat.cfg",
                    // 如果有其他的就加进来，因为是确定的所以说这样写尽管不合适，但是也行...
            };
            // 这个是百度地图在没有网络的时候读取离线文件的目录，也就是说我们要把APP assets文件夹下的百度离线地图包放到这个文件夹下。
            String path = dirSelect(context);
            // 每个文件的路径，也是个数组
            String filepath[] = {
                    path + "/" + fileName[0],
                    path + "/" + fileName[1],
                    path + "/" + fileName[2],

                    // 也是，fileName数组有几个就写几个。。。
            };
            File file = new File(path);
            if (!file.exists()) { // 判断一下这个路径有没有，没有的话就创建一下
                file.mkdir();
            }
            // 循环我们的地图文件
            for (int i = 0; i < fileName.length; i++) {
                // 如果这个地图文件没有
                if (!(new File(filepath[i])).exists()) {
                    // 如果手机内存没有这个文件就去创建一个文件
                    new File(filepath[i]).createNewFile();
                    // 一个流操作，把APP assets文件下对应的文件放进去，循环完成就OK了。
                    InputStream is = context.getAssets().open("baidu/" + fileName[i]);
                    FileOutputStream fos = new FileOutputStream(filepath[i]);
                    byte[] buffer = new byte[8192];
                    int count = 0;
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                }
            }

//            initOffLineMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String dirSelect(Context context) {
        //安卓10做适配
        final String BAIDU = "/BaiduMapSDKNew/vmp";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return context.getExternalFilesDir(BAIDU).getPath();
        } else {
            return Environment.getExternalStorageDirectory() + BAIDU;
        }
    }

}