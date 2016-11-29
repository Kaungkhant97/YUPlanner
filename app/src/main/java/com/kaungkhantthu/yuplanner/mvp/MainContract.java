package com.kaungkhantthu.yuplanner.mvp;

import android.content.Context;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public interface MainContract {

    interface view{
        void showEvent();
        void showAssignment();
        void showTimetable();
    }
    interface presenter{
        void initPresenter(Context context);
    }
}
