package com.kaungkhantthu.yuplanner.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by HeinHtetOo on 05/11/2016.
 */

public class Event extends RealmObject {
    public Event() {
    }

    private String tags = "sdasdasdasdasd";


    private Boolean starred = false;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("Place")
    @Expose
    private String place;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("byUser")
    @Expose
    private String username;

    @SerializedName("byUserid")
    @Expose
    private String userid;

    private Date formattedDate;

    public Date getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(Date formattedDate) {
        this.formattedDate = formattedDate;
    }

    public Event(String tags, String title, String place, String time, String date, String publishedDate, String description, String username, String userid) {
        this.tags = tags;
        this.title = title;
        this.place = place;
        this.time = time;
        this.date = date;
        this.publishedDate = publishedDate;
        this.description = description;
        this.username = username;
        this.userid = userid;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStarred() {
        return starred;
    }

    public Event(String place, String title, String time, String date, String publishedDate, String description, Boolean starred) {

        this.place = place;
        this.title = title;
        this.time = time;
        this.date = date;
        this.publishedDate = publishedDate;
        this.description = description;
        this.starred = starred;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
