package com.exc.applibrary.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;
public class CommonPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragmentList;
    private List<String> mPageTitleList;

    public CommonPagerAdapter(Context context,
                              FragmentManager fm,
                              List<Fragment> fragmentList,
                              List<String> pageTitleList
                                      ) {
        super(fm);
        this.mContext = context;
        this.mFragmentList = fragmentList;
        this.mPageTitleList = pageTitleList;
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
    public CharSequence getPageTitle(int position) {
        return mPageTitleList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
