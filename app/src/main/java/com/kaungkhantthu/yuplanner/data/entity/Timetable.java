package com.kaungkhantthu.yuplanner.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 11/29/16.
 */

public class Timetable extends RealmObject{
    private String major;
    private String year;
    private RealmList<Subject> subjects;
}
