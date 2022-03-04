package com.exc.applibrary.main.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.exc.applibrary.R;
import com.exc.applibrary.databinding.ActivityProjectionControlBinding;
import com.exc.applibrary.main.adapter.TabFragmentPagerAdapter;
import com.exc.applibrary.main.ui.fragment.DeviceFragment;
import com.exc.applibrary.main.ui.fragment.StrategyFragment;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;


public class ProjectionControlActivity extends BaseActivity implements OnHttpResponseListener, View.OnClickListener {
    private ActivityProjectionControlBinding binding;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProjectionControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {

    }

    @Override
    public void initView() {

        list = new ArrayList<>();
        list.add(new DeviceFragment());
        list.add(new StrategyFragment());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(),list);
        binding.myViewPager.setAdapter(adapter);
        binding.myViewPager.setCurrentItem(0);
        binding.myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position ==0){
                    binding.btnDevice.setBackgroundResource(R.drawable.background_btn_left_select);
                    binding.btnStrategy.setBackgroundResource(R.drawable.background_btn_right);
                    binding.btnDevice.setTextColor(Color.WHITE);
                    binding.btnStrategy.setTextColor(Color.GRAY);
                }else if(position ==1){
                    binding.btnStrategy.setBackgroundResource(R.drawable.background_btn_right_select);
                    binding.btnDevice.setBackgroundResource(R.drawable.background_btn_left);
                    binding.btnDevice.setTextColor(Color.GRAY);
                    binding.btnStrategy.setTextColor(Color.WHITE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.btnDevice.setBackgroundResource(R.drawable.background_btn_left_select);
        binding.btnStrategy.setBackgroundResource(R.drawable.background_btn_right);
        binding.btnDevice.setTextColor(Color.WHITE);
        binding.btnStrategy.setTextColor(Color.GRAY);
        
        binding.btnDevice.setOnClickListener(this::onClick);
        binding.btnStrategy.setOnClickListener(this::onClick);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        if(v == binding.btnDevice){
            binding.myViewPager.setCurrentItem(0);
            binding.btnDevice.setBackgroundResource(R.drawable.background_btn_left_select);
            binding.btnStrategy.setBackgroundResource(R.drawable.background_btn_right);
            binding.btnDevice.setTextColor(Color.WHITE);
            binding.btnStrategy.setTextColor(Color.GRAY);
        }else if(v == binding.btnStrategy){
            binding.myViewPager.setCurrentItem(1);
            binding.btnStrategy.setBackgroundResource(R.drawable.background_btn_right_select);
            binding.btnDevice.setBackgroundResource(R.drawable.background_btn_left);
            binding.btnDevice.setTextColor(Color.GRAY);
            binding.btnStrategy.setTextColor(Color.WHITE);
        }
    }
}
