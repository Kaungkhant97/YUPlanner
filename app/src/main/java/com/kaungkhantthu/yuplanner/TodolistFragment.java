package com.kaungkhantthu.yuplanner;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistPresenter;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistPresenterImpl;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistView;
import com.kaungkhantthu.yuplanner.recyclerView.ToDoAdapter;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodolistFragment extends Fragment implements TodolistView, AddTodolistDialogFragment.submitButtonClickListener {


    private TodolistPresenter presenter;
    private FloatingActionButton fab;
    private RecyclerView recyclerTodolist;
    private ToDoAdapter adapter;

    public TodolistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_todolist, container, false);

        fab = ((MainActivity) getActivity()).fab;
        fab.show();
        recyclerTodolist = (RecyclerView) v.findViewById(R.id.recycler_todo);
        initRecycler();
        init();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onFabClick();
            }
        });

        return v;
    }

    private void initRecycler() {
        recyclerTodolist.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ToDoAdapter(new ArrayList<TodoTask>());
        recyclerTodolist.setAdapter(adapter);

    }

    private void init() {
        DateChangeNotifier.getInstance().addNotifyView(this);
        presenter = TodolistPresenterImpl.getInstance(this);
        presenter.init();
    }

    @Override
    public void onDateChange(Calendar c) {
        presenter.onDateChange(c);
    }

    @Override
    public void showtodoList(List<TodoTask> todoTasks) {
        adapter.clearToDoTasks();
        adapter.addAllToDoTasks(todoTasks);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showDialog() {
        AddTodolistDialogFragment d = new AddTodolistDialogFragment();
        d.setListener(this);
        d.show(getChildFragmentManager(), "g");
    }

    @Override
    public void onSubmit() {
        presenter.refreshList();
    }
}
