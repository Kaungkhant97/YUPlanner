package com.kaungkhantthu.yuplanner.mvp.mainmvp;

import android.content.Context;

import com.kaungkhantthu.yuplanner.mvp.mvpPresenter;

import java.util.Calendar;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public interface MainPresenter  extends mvpPresenter {
    @Override
    void onDateChange(Calendar c);

    void init(Context c);
}
