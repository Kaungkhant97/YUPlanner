package com.kaungkhantthu.yuplanner.recyclerView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kaungkhantthu.yuplanner.TodolistFragment;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.views.EventFragment;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.View.SubjectFragment;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public class TabPagerAdapter extends FragmentPagerAdapter{
    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    private int count;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 2){
            return  new EventFragment();
        }else if(position == 1){
            return new SubjectFragment();

        }else{
            return new TodolistFragment();
        }
    }

    @Override
    public int getCount() {
        return this.count;
    }
}