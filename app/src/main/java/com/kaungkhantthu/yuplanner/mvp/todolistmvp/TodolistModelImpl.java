package com.kaungkhantthu.yuplanner.mvp.todolistmvp;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by kaungkhantthu on 12/13/16.
 */

public class TodolistModelImpl implements TodolistModel {


    private static TodolistModelImpl todolistModel;
    private static Realm realm;


    private TodolistModelImpl() {

    }

    ;

    public static TodolistModelImpl getInstance() {
        if (todolistModel == null) {
            todolistModel = new TodolistModelImpl();
            realm = Realm.getDefaultInstance();
        }
        return todolistModel;
    }

    @Override
    public void saveTask(TodoTask task) {
        realm.beginTransaction();
        realm.copyToRealm(task);
        realm.commitTransaction();
    }

    @Override
    public void saveTask(List<TodoTask> tasks) {
        realm.beginTransaction();
        realm.copyToRealm(tasks);
        realm.commitTransaction();
    }

    @Override
    public List<TodoTask> getTasks(Date fromDate) {
        realm.beginTransaction();
        Calendar c = Calendar.getInstance();
        String mdate = Utils.formatDATEIntoYearMonthDay(fromDate);
        List<TodoTask> todoTasks = new RealmList<>();
        c.set(fromDate.getYear(), fromDate.getMonth(), fromDate.getDate(), 23, 55, 50);
        Date toDate = c.getTime();

        RealmResults<TodoTask> todolist = realm.where(TodoTask.class).findAll();
        for (TodoTask t : todolist) {
            //TODO check this shit
            /*Date d =e.getFormattedDate();
            if(d.before(toDate) && d.after(fromDate)){
                mevents.add(e);
            }*/
            if (Utils.formatDATEIntoYearMonthDay(t.getFormattedDate()).equals(mdate)) {
                todoTasks.add(t);
            }
        }

        todoTasks = realm.copyFromRealm(todoTasks);

        realm.commitTransaction();

        return todoTasks;


    }

    public void deleteToDoTask(TodoTask todoTask) {

        RealmResults<TodoTask> result = Realm.getDefaultInstance().where(TodoTask.class).equalTo("id", todoTask.getId()).findAll();
        realm.beginTransaction();
        result.deleteAllFromRealm();
        realm.commitTransaction();

    }
}
