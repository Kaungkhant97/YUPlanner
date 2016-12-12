package com.kaungkhantthu.yuplanner.mvp.subjectmvp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.presenter.EventPresenterImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.View.SubjectView;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.presenter.SubjectPresenterImpl;
import com.kaungkhantthu.yuplanner.recyclerView.SubjectAdapter;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public class SubjectFragment extends Fragment implements SubjectView {

    private RecyclerView recyler_subjects;
    private SubjectPresenterImpl subjectPresenter;
    private SubjectAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        recyler_subjects = (RecyclerView) view.findViewById(R.id.recycler_subjects);

        initRecycler();
        init();
        return view;

    }

    private void initRecycler() {
        recyler_subjects.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SubjectAdapter(new ArrayList<Subject>());
        recyler_subjects.setAdapter(adapter);

    }

    private void init() {
        subjectPresenter = SubjectPresenterImpl.getInstance(this);
        subjectPresenter.init();
        DateChangeNotifier.getInstance().addNotifyView(this);
    }

    @Override
    public void onDateChange(Calendar c) {

        subjectPresenter.onDateChange(c);
    }

    @Override
    public void showsubjects(List<Subject> subjectList) {
        adapter.clearSubjects();
        adapter.addallSubjects(subjectList);
    }

    @Override
    public void showErrorView() {

    }
}