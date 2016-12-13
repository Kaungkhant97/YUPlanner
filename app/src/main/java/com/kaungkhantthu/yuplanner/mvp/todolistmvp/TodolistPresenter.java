package com.kaungkhantthu.yuplanner.mvp.todolistmvp;

import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.mvpPresenter;

import java.util.Calendar;

/**
 * Created by kaungkhantthu on 12/13/16.
 */

public interface TodolistPresenter extends mvpPresenter {
    @Override
    void onDateChange(Calendar c);

    void createEvent(TodoTask t);
    void onFabClick();
    void refreshList();

}
