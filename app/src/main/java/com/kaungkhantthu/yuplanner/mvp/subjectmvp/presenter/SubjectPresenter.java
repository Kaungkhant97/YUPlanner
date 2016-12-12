package com.kaungkhantthu.yuplanner.mvp.subjectmvp.presenter;

import com.kaungkhantthu.yuplanner.mvp.mvpPresenter;

import java.util.Calendar;

/**
 * Created by kaungkhantthu on 12/12/16.
 */

public interface SubjectPresenter extends mvpPresenter {

    @Override
    void onDateChange(Calendar c);
}
