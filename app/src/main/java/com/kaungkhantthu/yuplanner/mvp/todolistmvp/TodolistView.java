package com.kaungkhantthu.yuplanner.mvp.todolistmvp;

import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.mvpView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kaungkhantthu on 12/13/16.
 */

public interface TodolistView extends mvpView {
    @Override
    void onDateChange(Calendar c);

    void showtodoList(List<TodoTask> todoTasks);
    void showErrorView();

    void showDialog();
}
