package com.kaungkhantthu.yuplanner.mvp.presenter;

import java.util.Calendar;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public interface EventPresenter extends mvpPresenter {
    @Override
    void onDateChange(Calendar c);
}
