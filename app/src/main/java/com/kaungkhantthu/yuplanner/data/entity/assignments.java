package com.kaungkhantthu.yuplanner.data.entity;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 11/30/16.
 */

public class assignments extends RealmObject{
    private String name;
    private Date dueDate;
    private String description ;


}
