package com.kaungkhantthu.yuplanner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kaungkhantthu.yuplanner.fragments.TimeTableFragment;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public class TabPagerAdapter extends FragmentPagerAdapter{
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TimeTableFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}