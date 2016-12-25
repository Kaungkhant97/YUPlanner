package com.kaungkhantthu.yuplanner.mvp.todolistmvp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaungkhantthu.yuplanner.LauncherActivity;
import com.kaungkhantthu.yuplanner.MainActivity;
import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.data.models.UserInfoModel;
import com.kaungkhantthu.yuplanner.recyclerView.ToDoAdapter;
import com.kaungkhantthu.yuplanner.utils.Constants;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;
import com.kaungkhantthu.yuplanner.utils.SPrefHelper;
import com.kaungkhantthu.yuplanner.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodolistFragment extends Fragment implements TodolistView, AddTodolistDialogFragment.submitButtonClickListener {


    private static TodolistFragment todolistFragment;
    private TodolistPresenter presenter;
    private FloatingActionButton fab;
    private RecyclerView recyclerTodolist;
    private ToDoAdapter adapter;
    private FrameLayout errorLayout;
    private TextView errrorText;
    private LinearLayout pathDirectory;
    private TextView txt_major;
    private TextView txt_class;
    private TextView txt_year;

    public TodolistFragment() {

    }
    public static TodolistFragment getInstance(){
        if(todolistFragment == null){
            todolistFragment = new TodolistFragment();
        }
        return todolistFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_todolist, container, false);

        fab = ((MainActivity) getActivity()).fab;
        recyclerTodolist = (RecyclerView) v.findViewById(R.id.recycler_todo);
        errorLayout = (FrameLayout) v.findViewById(R.id.errorLayout);
        pathDirectory =(LinearLayout)v.findViewById(R.id.path_directory);
        errrorText = (TextView)v.findViewById(R.id.errorText);
        errorLayout.setVisibility(View.GONE);
        pathDirectory =(LinearLayout)v.findViewById(R.id.path_directory);
        pathDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPrefHelper.putBoolean(getContext(), Constants.FIRSTTIME,true);
                Intent i =new Intent(getActivity(),LauncherActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        initRecycler();
        init();
        pathDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPrefHelper.putBoolean(getContext(), Constants.FIRSTTIME,true);
                Intent i =new Intent(getActivity(),LauncherActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onFabClick();
            }
        });
       initTopBar(v);
        return v;
    }

    private void initTopBar(View v) {
        txt_major = (TextView) v.findViewById(R.id.tv_major);
        txt_class = (TextView) v.findViewById(R.id.tv_class);
        txt_year = (TextView) v.findViewById(R.id.tv_year);

        /*String major = SPrefHelper.getString(getContext(), Constants.MAJOR, "");
        String year = SPrefHelper.getString(getContext(), Constants.YEAR, "");
        String mClass = SPrefHelper.getString(getContext(), Constants.CLASS, "");

        mClass = Utils.classConverter(mClass, getActivity());
        year = Utils.yearConverter(year, getActivity());*/

        String major = UserInfoModel.getObjInstance().getTextMajor();
        String year = UserInfoModel.getObjInstance().getTextYear();
        String mClass = UserInfoModel.getObjInstance().getTextClass();


        txt_class.setText(mClass);
        txt_year.setText(year);
        txt_major.setText(major);
    }

    private void initRecycler() {
        recyclerTodolist.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ToDoAdapter(new ArrayList<TodoTask>());
        recyclerTodolist.setAdapter(adapter);

    }

    private void init() {
        DateChangeNotifier.getInstance().addNotifyView(this);
        presenter = new TodolistPresenterImpl(this);
        presenter.init();
    }

    @Override
    public void onDateChange(Calendar c) {
        presenter.onDateChange(c);
    }

    @Override
    public void showtodoList(List<TodoTask> todoTasks) {

        errorLayout.setVisibility(View.GONE);
        recyclerTodolist.setVisibility(View.VISIBLE);
        adapter.clearToDoTasks();
        adapter.addAllToDoTasks(todoTasks);
    }

    @Override
    public void showErrorView() {
        errorLayout.setVisibility(View.VISIBLE);
        recyclerTodolist.setVisibility(View.GONE);
        errrorText.setText("Press + To Create A Task");
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
