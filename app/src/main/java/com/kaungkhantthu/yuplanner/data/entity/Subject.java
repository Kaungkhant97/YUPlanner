
package com.kaungkhantthu.yuplanner.data.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Subject extends RealmObject{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("subjectname")
    @Expose
    private String subjectname;
    @SerializedName("teachername")
    @Expose
    private String teachername;
    @SerializedName("subjectId")
    @Expose
    private String subjectId;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("timetable")
    @Expose
    private RealmList<Timetable> timetable = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("_class")
    @Expose
    private Integer _class;
    @SerializedName("_mtype")
    @Expose
    private String mtype;

    public Subject() {
    }

    public Subject(String id, String major, Integer year, String subjectname, String teachername, String subjectId, Integer v, RealmList<Timetable> timetable, String description, Integer _class, String mtype) {
        this.id = id;
        this.major = major;
        this.year = year;
        this.subjectname = subjectname;
        this.teachername = teachername;
        this.subjectId = subjectId;
        this.v = v;
        this.timetable = timetable;
        this.description = description;
        this._class = _class;
        this.mtype = mtype;
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
     *     The major
     */
    public String getMajor() {
        return major;
    }

    /**
     * 
     * @param major
     *     The major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The subjectname
     */
    public String getSubjectname() {
        return subjectname;
    }

    /**
     * 
     * @param subjectname
     *     The subjectname
     */
    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    /**
     * 
     * @return
     *     The teachername
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * 
     * @param teachername
     *     The teachername
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    /**
     * 
     * @return
     *     The subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 
     * @param subjectId
     *     The subjectId
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 
     * @return
     *     The v
     */
    public Integer getV() {
        return v;
    }

    /**
     * 
     * @param v
     *     The __v
     */
    public void setV(Integer v) {
        this.v = v;
    }

    /**
     * 
     * @return
     *     The timetable
     */
    public List<Timetable> getTimetable() {
        return timetable;
    }

    /**
     * 
     * @param timetable
     *     The timetable
     */
    public void setTimetable(RealmList<Timetable> timetable) {
        this.timetable = timetable;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The _class
     */
    public Integer getClass_() {
        return _class;
    }

    /**
     * 
     * @param _class
     *     The _class
     */
    public void setClass_(Integer _class) {
        this._class = _class;
    }

    /**
     * 
     * @return
     *     The mtype
     */
    public String getMtype() {
        return mtype;
    }

    /**
     * 
     * @param mtype
     *     The _mtype
     */
    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

}
