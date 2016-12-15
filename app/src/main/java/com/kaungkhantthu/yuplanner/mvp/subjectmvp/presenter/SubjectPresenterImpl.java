package com.kaungkhantthu.yuplanner.mvp.subjectmvp.presenter;

import android.util.Log;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.data.entity.Timetable;
import com.kaungkhantthu.yuplanner.data.entity.period;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModel;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModelImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.View.SubjectView;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.RealmList;
import retrofit2.http.Path;

/**
 * Created by kaungkhantthu on 12/12/16.
 */

public class SubjectPresenterImpl implements SubjectPresenter {
    private static SubjectPresenterImpl subjectPresenter;
    private static SubjectView subjectView;
    private SubjectModel subjectModel;

    private SubjectPresenterImpl() {
    }

    public static SubjectPresenterImpl getInstance(SubjectView s) {
        if (subjectPresenter == null) {
            subjectPresenter = new SubjectPresenterImpl();
            subjectView = s;

        }
        return subjectPresenter;
    }

    public void init() {
        this.subjectModel = SubjectModelImpl.getInstance();
        Calendar calendar = DateChangeNotifier.getInstance().getcurrentSelectedDate();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        List<Subject> subjectList = subjectModel.getSubjectListFromCache(calendar.get(Calendar.DAY_OF_WEEK));
        subjectList = formatList(subjectList, day);
        if (subjectList == null || subjectList.size() < 1) {
            subjectView.showErrorView();

        } else {
            subjectView.showsubjects(subjectList);
        }
    }


    @Override
    public void onDateChange(Calendar c) {
        int day = c.get(Calendar.DAY_OF_WEEK);
        List<Subject> subjects = subjectModel.getSubjectListFromCache(day);

        subjects = formatList(subjects, day);

        if (subjects == null) {
            subjectView.showErrorView();
        } else {
            subjectView.showsubjects(subjects);
        }
    }

    private List<Subject> formatList(List<Subject> subjects, int day) {
        subjects = removeUnecessaryDays(subjects, day);

        subjects = sortsubjectByperiod(subjects);
        return subjects;
    }

    private List<Subject> sortsubjectByperiod(List<Subject> subjects) {
        ArrayList<Subject> msubjectlist = new ArrayList<Subject>();

        int count = 0;
        for (int i = 0; i < 7; i++) {
            Subject s = getperoid(subjects, i);
            if (s != null) {
                msubjectlist.add(s);
            }
        }

        return msubjectlist;
    }

    private Subject getperoid(List<Subject> subjects, int i) {
        Subject rightsubject = null;
        for (Subject s : subjects) {
            for (Timetable t : s.getTimetable()) {
                for (period per : t.getPeriod()) {
                    if (per.getP() == i) {
                        rightsubject = s;
                        break;
                    }
                }
            }
        }
        return rightsubject;
    }


    private List<Subject> removeUnecessaryDays(List<Subject> subjects, int day) {
        ArrayList<Subject> templist = new ArrayList<>();
       for(int g=0;g<subjects.size();g++){
           templist.add(new Subject(subjects.get(g)));
       }

        for (int i=0;i<subjects.size();i++) {
            List<Timetable> timetablelist = subjects.get(i).getTimetable();
           int size = timetablelist.size();
            for (int j=0;j<size;j++) {
                if (timetablelist.get(j).getDay() != day) {
                    List<Timetable> temptimetable = templist.get(i).getTimetable();

                    temptimetable.remove(timetablelist.get(j));
                }
            }
        }
        return templist;
    }

    public void requestsubjects() {
        subjectModel.getSubjectList("CS", "4", "", new SubjectModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Subject> sbjs) {
                subjectModel.clearSubject();
                subjectModel.saveSubject(sbjs);
                subjectView.showsubjects(sbjs);

            }

            @Override
            public void onError() {
                subjectView.showErrorView();
            }
        });
    }
}
