package com.exc.applibrary.main.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.tabbar.vertical.ITabView;
import com.xuexiang.xui.widget.tabbar.vertical.TabAdapter;
import com.xuexiang.xui.widget.tabbar.vertical.TabView;

import java.util.List;

public class ControlTitlePagerAdapter extends FragmentStatePagerAdapter implements TabAdapter {
    private Context mContext;
    private List<Fragment> mFragmentList;
    private List<CommonLeftBean> mMenus;

    public ControlTitlePagerAdapter(Context context,
                                    FragmentManager fm,
                                    List<Fragment> fragmentList,
                                    List<CommonLeftBean> menus

    ) {
        super(fm);
        this.mContext = context;
        this.mFragmentList = fragmentList;
        this.mMenus = menus;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {
        return null;
    }

//    @Override
//    public ITabView.TabIcon getIcon(int position) {
//        return null;
//    }

    @Override
    public TabView.TabIcon getIcon(int position) {
        CommonLeftBean menu = mMenus.get(position);
        if (menu.mIconId == -1) {
            return null;
        }
        return new TabView.TabIcon.Builder()
                .setIcon(menu.mIconId, menu.mIconId)
                .setIconGravity(Gravity.START)
                .setIconMargin(DensityUtils.dp2px(5))
                .setIconSize(DensityUtils.dp2px(20), DensityUtils.dp2px(20))
                .build();
    }

    @Override
    public TabView.TabTitle getTitle(int position) {
        CommonLeftBean menu = mMenus.get(position);
        return new TabView.TabTitle.Builder()
                .setContent(menu.mTitle)
                .setTextColor(Color.parseColor("#3F51B5"), 0xFF757575)
                .build();
    }

    @Override
    public int getBackground(int position) {
        return -1;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }


    public static class CommonLeftBean {
        public String mTitle;
        public int mIconId;

        public CommonLeftBean(String mTitle, int iconId) {
            this.mTitle = mTitle;
            this.mIconId = iconId;
        }
    }
}
