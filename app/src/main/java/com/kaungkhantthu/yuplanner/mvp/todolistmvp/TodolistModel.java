package com.kaungkhantthu.yuplanner.mvp.todolistmvp;

import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.mvpView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kaungkhantthu on 12/13/16.
 */

public interface TodolistModel {

    void saveTask(TodoTask task);
    void saveTask(List<TodoTask> tasks);
    List<TodoTask> getTasks(Date d);


}
