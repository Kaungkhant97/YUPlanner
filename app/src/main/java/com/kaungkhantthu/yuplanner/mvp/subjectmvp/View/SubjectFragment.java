package com.kaungkhantthu.yuplanner.mvp.subjectmvp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.kaungkhantthu.yuplanner.MainActivity;
import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.data.entity.Timetable;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.presenter.EventPresenterImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModelImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.View.SubjectView;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.presenter.SubjectPresenterImpl;
import com.kaungkhantthu.yuplanner.recyclerView.SubjectAdapter;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public class SubjectFragment extends Fragment implements SubjectView {

    private static final String TAG = SubjectFragment.class.getName();
    private RecyclerView recyler_subjects;
    private SubjectPresenterImpl subjectPresenter;
    private SubjectAdapter adapter;
    private FrameLayout errorlayout;
    private Button errorbtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        recyler_subjects = (RecyclerView) view.findViewById(R.id.recycler_subjects);
        errorlayout = (FrameLayout) view.findViewById(R.id.errorLayout);
        errorbtn = (Button) view.findViewById(R.id.btn_error);
        FloatingActionButton fab = ((MainActivity) getActivity()).fab;
        fab.hide();
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
        Log.e(TAG, "onDateChange: ");

        subjectPresenter.onDateChange(c);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void showsubjects(List<Subject> subjectList) {
        Log.e(TAG, "showsubjects: ");
        errorlayout.setVisibility(View.GONE);
        recyler_subjects.setVisibility(View.VISIBLE);
        adapter.clearSubjects();
        adapter.addallSubjects(subjectList);

    }

    @Override
    public void showErrorView() {
        Log.e("showErrorView: ", "error in event");
        errorlayout.setVisibility(View.VISIBLE);
        recyler_subjects.setVisibility(View.GONE);
        errorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectPresenter.requestsubjects();
            }
        });
    }
}