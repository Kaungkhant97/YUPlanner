
package com.kaungkhantthu.yuplanner.data.entity;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Timetable extends RealmObject{

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("period")
    @Expose
    private RealmList<period> period = null;

    public Timetable() {
    }

    public Timetable(Integer day, String id, RealmList<period>  period) {
        this.day = day;
        this.id = id;
        this.period = period;
    }

    /**
     * 
     * @return
     *     The day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * 
     * @param day
     *     The day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The period
     */
    public RealmList<period>  getPeriod() {
        return period;
    }

    /**
     * 
     * @param period
     *     The period
     */
    public void setPeriod(RealmList<period>  period) {
        this.period = period;
    }

}
