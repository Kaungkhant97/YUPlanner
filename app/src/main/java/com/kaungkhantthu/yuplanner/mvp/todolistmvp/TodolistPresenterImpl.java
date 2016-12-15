package com.kaungkhantthu.yuplanner.mvp.todolistmvp;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.presenter.EventPresenterImpl;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.views.EventView;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kaungkhantthu on 12/13/16.
 */

public class TodolistPresenterImpl implements TodolistPresenter {

    private static TodolistView todolistView;
    private static TodolistPresenterImpl todolistPresenter;
    private TodolistModelImpl todolistModel;


    public static TodolistPresenterImpl getInstance(TodolistView v) {
        if (todolistPresenter == null) {
            todolistPresenter = new TodolistPresenterImpl();
            todolistView = v;

        }
        return todolistPresenter;
    }

    @Override
    public void init() {
        this.todolistModel = TodolistModelImpl.getInstance();
        Calendar calendar = DateChangeNotifier.getInstance().getcurrentSelectedDate();
        List<TodoTask> taskList = new ArrayList<>();
        taskList = todolistModel.getTasks(calendar.getTime());
        if (taskList == null || taskList.size() < 1) {
            todolistView.showErrorView();

        } else {
            todolistView.showtodoList(taskList);
        }
    }


    @Override
    public void onDateChange(Calendar c) {
        List<TodoTask> t = todolistModel.getTasks(c.getTime());
        if (t == null || t.size() < 1) {
            todolistView.showErrorView();
        } else {
            todolistView.showtodoList(t);
        }
    }

    @Override
    public void createEvent(TodoTask t) {
        todolistModel.saveTask(t);
    }


    @Override
    public void onFabClick() {
        todolistView.showDialog();
    }

    @Override
    public void refreshList() {
        Date date = DateChangeNotifier.getInstance().getcurrentSelectedDate().getTime();
        List<TodoTask> todolist = todolistModel.getTasks(date);
        todolistView.showtodoList(todolist);
    }
}
