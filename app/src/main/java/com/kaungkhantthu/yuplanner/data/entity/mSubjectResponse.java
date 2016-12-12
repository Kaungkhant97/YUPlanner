
package com.kaungkhantthu.yuplanner.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class mSubjectResponse extends RealmObject {
    @SerializedName("data")
    @Expose
    private RealmList<Subject> subjects;

    public mSubjectResponse() {
    }



    /**
     * 
     * @return
     *     The subjects
     */
    public RealmList<Subject> getSubjects() {
        return subjects;
    }

    /**
     * 
     * @param subjects
     *     The subjects
     */
    public void setSubjects(RealmList<Subject> subjects) {
        this.subjects = subjects;
    }

}
