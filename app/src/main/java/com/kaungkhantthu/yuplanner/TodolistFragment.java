package com.kaungkhantthu.yuplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistView;

import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodolistFragment extends Fragment implements TodolistView{


    public TodolistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_todolist, container, false);
        init();
        return v;
    }

    private void init() {

    }

    @Override
    public void onDateChange(Calendar c) {

    }

    @Override
    public void showtodoList(List<TodoTask> todoTasks) {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showDialog() {

    }
}
