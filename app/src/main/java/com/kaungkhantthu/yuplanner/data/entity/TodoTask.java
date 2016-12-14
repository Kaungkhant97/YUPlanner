package com.kaungkhantthu.yuplanner.data.entity;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 12/13/16.
 */

public class TodoTask extends RealmObject {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    private String name;
    private String time;
    private String date;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;
    private Date formattedDate;

    public TodoTask() {
    }

    public TodoTask(String name, String time, String date, String note, Date formattedDate, String id) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.note = note;
        this.formattedDate = formattedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(Date formattedDate) {
        this.formattedDate = formattedDate;
    }
}
