package com.yhbc.base.common.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建时间： 2018/5/22
 * 作者：yangxd
 * 描述：tab Adapter
 **/
public class TabPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private List<Fragment> views;

    public TabPagerAdapter(FragmentManager fm, List<Fragment> views) {
        super(fm);
        this.views = views;
    }

    public TabPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.views = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
