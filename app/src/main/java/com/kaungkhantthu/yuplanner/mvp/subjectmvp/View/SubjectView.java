package com.kaungkhantthu.yuplanner.mvp.subjectmvp.View;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.mvp.mvpView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kaungkhantthu on 12/12/16.
 */

public interface SubjectView extends mvpView {
    @Override
    void onDateChange(Calendar c);

    void showsubjects(List<Subject> eventList);

    void showErrorView();
}
