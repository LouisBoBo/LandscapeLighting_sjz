package com.exc.applibrary.main.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.exc.applibrary.R;
import com.exc.applibrary.databinding.FragmentShouyeBinding;
import com.exc.applibrary.main.MainActivity;
import com.exc.applibrary.main.show.SwitchShowActivity;
import com.exc.applibrary.main.ui.activity.DataCenterActivity;
import com.exc.applibrary.main.ui.activity.LoopControlActivity;
import com.exc.applibrary.main.ui.activity.ProjectionInfoActivity;
import com.exc.applibrary.main.ui.activity.SceneSwitchActivity;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseFragment;

public class ShouYeFragment extends BaseFragment implements View.OnClickListener , OnBannerListener {

    private FragmentShouyeBinding binding;
    private Activity mActivity;
    private TextView video_title;
    private TextView scene_title;
    private TextView control_title;
    private TextView data_title;
    private ImageView video_img;
    private ImageView scene_img;
    private ImageView control_img;
    private ImageView data_img;
    private ImageView edit_img;
    private Banner mBanner;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_shouye);
        mActivity = getActivity();
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.v_video){
            toActivity(new Intent(mActivity, SwitchShowActivity.class));
        }else if(v.getId() == R.id.v_scene){
            toActivity(new Intent(mActivity, SceneSwitchActivity.class));
        }else if(v.getId() == R.id.v_control){
            toActivity(new Intent(mActivity, LoopControlActivity.class));
        }else if(v.getId() == R.id.v_data){
            toActivity(new Intent(mActivity, DataCenterActivity.class));
        }else if(v.getId() == R.id.img_add){
            toActivity(new Intent(mActivity, ProjectionInfoActivity.class));
        }
    }

    @Override
    public void initView() {
        edit_img = findViewById(R.id.img_add);
        mBanner = findViewById(R.id.mBanner);

        video_img =  findViewById(R.id.v_video).findViewById(R.id.image);
        scene_img =  findViewById(R.id.v_scene).findViewById(R.id.image);
        control_img =  findViewById(R.id.v_control).findViewById(R.id.image);
        data_img =  findViewById(R.id.v_data).findViewById(R.id.image);

        video_title =  findViewById(R.id.v_video).findViewById(R.id.title);
        scene_title =  findViewById(R.id.v_scene).findViewById(R.id.title);
        control_title =  findViewById(R.id.v_control).findViewById(R.id.title);
        data_title =  findViewById(R.id.v_data).findViewById(R.id.title);

        video_title.setText("????????????");
        scene_title.setText("????????????");
        control_title.setText("????????????");
        data_title.setText("????????????");

        edit_img.setOnClickListener(this::onClick);

        findView(R.id.v_video,this::onClick);
        findView(R.id.v_scene,this::onClick);
        findView(R.id.v_control,this::onClick);
        findView(R.id.v_data,this::onClick);

        initBanner();
    }

    public void initBanner(){
        //????????????
        int[] imageResourceID = new int[]{R.mipmap.qq_test, R.mipmap.qq_test, R.mipmap.qq_test, R.mipmap.qq_test};
        List<Integer> imgeList = new ArrayList<>();
        //????????????
        String[] mtitle = new String[]{"??????1", "??????2", "??????3", "??????4"};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//???????????????????????????list??????
            titleList.add(mtitle[i]);//????????????????????????????????????
            //??????????????????????????????Glide????????????
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getActivity()).load(path).into(imageView);
                }
            });
            //???????????????????????????,????????????????????????,?????????GitHub??????????????????
            mBanner.setBannerAnimation(Transformer.Default);
            mBanner.setImages(imgeList);//??????????????????
            mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);//??????banner????????????????????????????????????
            mBanner.setBannerTitles(titleList); //????????????????????????banner???????????????title??????
            //????????????????????????????????????????????????????????????
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(5000);//??????????????????3??????????????????
            mBanner.setOnBannerListener(this::OnBannerClick);//????????????
            mBanner.start();//????????????banner??????
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "???????????????" + (position + 1) + "????????????", Toast.LENGTH_SHORT).show();
    }
}
