package com.kaungkhantthu.yuplanner.mvp.subjectmvp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaungkhantthu.yuplanner.LauncherActivity;
import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.presenter.SubjectPresenterImpl;
import com.kaungkhantthu.yuplanner.recyclerView.SubjectAdapter;
import com.kaungkhantthu.yuplanner.utils.Constants;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;
import com.kaungkhantthu.yuplanner.utils.SPrefHelper;
import com.kaungkhantthu.yuplanner.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public class SubjectFragment extends Fragment implements SubjectView {

    private static final String TAG = SubjectFragment.class.getName();
    private static SubjectFragment subjectFragment;
    private RecyclerView recyler_subjects;
    private SubjectPresenterImpl subjectPresenter;
    private SubjectAdapter adapter;
    private FrameLayout errorlayout;
    private Button errorbtn;
    private TextView errotext;
    private TextView txt_major, txt_year, txt_class;
    private LinearLayout pathDirectory;

    public SubjectFragment() {

    }

    public static SubjectFragment getInstance() {
        if (subjectFragment == null) {
            subjectFragment = new SubjectFragment();
        }
        return subjectFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        recyler_subjects = (RecyclerView) view.findViewById(R.id.recycler_subjects);
        errorlayout = (FrameLayout) view.findViewById(R.id.errorLayout);

        errotext = (TextView) view.findViewById(R.id.errorText);
        errorbtn = (Button) view.findViewById(R.id.btn_error);

        initRecycler();
        init();
        pathDirectory =(LinearLayout)view.findViewById(R.id.path_directory);
        pathDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPrefHelper.putBoolean(getContext(), Constants.FIRSTTIME,true);
                Intent i =new Intent(getActivity(),LauncherActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
       initTopBar(view);
        return view;

    }
    private void initTopBar(View v) {
        txt_major = (TextView) v.findViewById(R.id.tv_major);
        txt_class = (TextView) v.findViewById(R.id.tv_class);
        txt_year = (TextView) v.findViewById(R.id.tv_year);

        String major = SPrefHelper.getString(getContext(), Constants.MAJOR, "");
        String year = SPrefHelper.getString(getContext(), Constants.YEAR, "");
        String mClass = SPrefHelper.getString(getContext(), Constants.CLASS, "");

        mClass = Utils.classConverter(mClass, getActivity());
        year = Utils.yearConverter(year, getActivity());


        txt_class.setText(mClass);
        txt_year.setText(year);
        txt_major.setText(major);
    }

    private void initRecycler() {
        recyler_subjects.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SubjectAdapter(new ArrayList<Subject>());
        recyler_subjects.setAdapter(adapter);

    }

    private void init() {

        subjectPresenter = new SubjectPresenterImpl(this);
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
        recyler_subjects.bringToFront();
        adapter.clearSubjects();
        adapter.addallSubjects(subjectList);

    }

    @Override
    public void showErrorView() {
        Log.e("showErrorView: ", "error in event");
        errorlayout.bringToFront();

        errorlayout.setVisibility(View.VISIBLE);
        recyler_subjects.setVisibility(View.GONE);
        errorbtn.setVisibility(View.VISIBLE);
        if(getContext() != null){
            if(!Utils.isNetworkAvailable(getContext())){
                errotext.setText("No internet Connection Please Try again");
            }
        }

        errorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectPresenter.requestsubjects(getContext());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!Utils.isNetworkAvailable(context)){
            errotext.setText("No internet Connection Please Try again");
        }
    }

    @Override
    public void showErrorView(String error) {
        Log.e(TAG, "showErrorView: btn hide");
        errorlayout.setVisibility(View.VISIBLE);
        recyler_subjects.setVisibility(View.GONE);

        errorbtn.setVisibility(View.GONE);
        errorlayout.bringToFront();
        errotext.setText(error);
        if(!Utils.isNetworkAvailable(getContext())){
            errotext.setText(error);
        }
    }
}