package com.kaungkhantthu.yuplanner.mvp;

import android.content.Context;

import com.kaungkhantthu.yuplanner.data.assignments;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Timetable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public interface MainContract {

    interface Eventview{
        void showEvent(ArrayList<Event> eventArrayList);

    }
    interface Timetableview{

        void showTimetable(ArrayList<Timetable> timetableArrayList);
    }
    interface Assignmentview{

        void showAssignment(ArrayList<assignments> assignmentsArrayList);
    }
    interface presenter{
        void initPresenter(Context context);
        void DateChange(Date d);
    }
}
