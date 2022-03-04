package com.exc.applibrary.main.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.exc.applibrary.R;
import com.exc.applibrary.databinding.ActivityNewDataCenterBinding;
import com.exc.applibrary.main.adapter.TabFragmentPagerAdapter;
import com.exc.applibrary.main.customview.PinchImageView;
import com.exc.applibrary.main.ui.fragment.StrongCurrentFragment;
import com.exc.applibrary.main.ui.fragment.WeakCurrentFragment;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;

public class NewDataCenterActivity extends BaseActivity implements View.OnClickListener {
    private ActivityNewDataCenterBinding binding;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;

    // 滚动条初始偏移量
    private int offset = 0;
    // 滚动条宽度
    private int bmpW;
    //一倍滚动量
    private int one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewDataCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView() {

        list = new ArrayList<>();
        list.add(new StrongCurrentFragment());
        list.add(new WeakCurrentFragment());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(),list);
        binding.myViewPager.setAdapter(adapter);
        binding.myViewPager.setCurrentItem(0);
        binding.myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {
                Animation animation = null;
                if(position ==0){
                    binding.btnStrong.setTextColor(getResources().getColor(R.color.blue_btn));
                    binding.btnWeak.setTextColor(getResources().getColor(R.color.black));
                    animation = new TranslateAnimation(one, 0, 0, 0);

                }else if(position ==1){
                    binding.btnStrong.setTextColor(getResources().getColor(R.color.black));
                    binding.btnWeak.setTextColor(getResources().getColor(R.color.blue_btn));
                    animation = new TranslateAnimation(offset, one, 0, 0);
                }

                // 将此属性设置为true可以使得图片停在动画结束时的位置
                animation.setFillAfter(true);
                //动画持续时间，单位为毫秒
                animation.setDuration(200);
                //滚动条开始动画
                binding.scrollBar.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.btnStrong.setTextColor(getResources().getColor(R.color.blue_btn));
        binding.btnWeak.setTextColor(getResources().getColor(R.color.black));

        binding.btnStrong.setOnClickListener(this::onClick);
        binding.btnWeak.setOnClickListener(this::onClick);

        // 获取滚动条的宽度
        bmpW = binding.scrollBar.getWidth();
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //得到屏幕的宽度
        int screenW = displayMetrics.widthPixels;
        //计算出滚动条初始的偏移量
        offset = (screenW / 2 - bmpW) / 2;
        //计算出切换一个界面时，滚动条的位移量
        one = offset * 2 + bmpW;
        android.graphics.Matrix matrix = PinchImageView.MathUtils.matrixTake();
        matrix.postTranslate(offset, 0);
        //将滚动条的初始位置设置成与左边界间隔一个offset
        binding.scrollBar.setImageMatrix(matrix);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        if(v == binding.btnStrong){
            binding.myViewPager.setCurrentItem(0);
            binding.btnStrong.setTextColor(getResources().getColor(R.color.blue_btn));
            binding.btnWeak.setTextColor(getResources().getColor(R.color.black));
        }else if(v == binding.btnWeak){
            binding.myViewPager.setCurrentItem(1);
            binding.btnStrong.setTextColor(getResources().getColor(R.color.black));
            binding.btnWeak.setTextColor(getResources().getColor(R.color.blue_btn));
        }
    }
}
