
package com.kaungkhantthu.yuplanner.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class EventResponse extends RealmObject {

    @SerializedName("data")
    @Expose
    private RealmList<Event> data ;

    public RealmList<Event> getData() {
        return data;
    }

    public void setData(RealmList<Event> data) {
        this.data = data;
    }
}
