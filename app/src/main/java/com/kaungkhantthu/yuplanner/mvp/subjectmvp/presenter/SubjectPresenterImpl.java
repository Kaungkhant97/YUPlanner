package com.kaungkhantthu.yuplanner.mvp.subjectmvp.presenter;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModel;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModelImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.View.SubjectView;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.Calendar;
import java.util.List;

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

    public void init(){
        this.subjectModel =  SubjectModelImpl.getInstance();
        Calendar calendar = DateChangeNotifier.getInstance().getcurrentSelectedDate();
        List<Subject> subjectList = subjectModel.getSubjectListFromCache(calendar.get(Calendar.DAY_OF_WEEK));
        if(subjectList == null){
            subjectView.showErrorView();

        }else{
            subjectView.showsubjects(subjectList);
        }
    }



    @Override
    public void onDateChange(Calendar c) {
        List<Subject> s= subjectModel.getSubjectListFromCache(c.get(Calendar.DAY_OF_WEEK));
        if(s == null ){
            subjectView.showErrorView();
        }else{
            subjectView.showsubjects(s);
        }
    }
}
