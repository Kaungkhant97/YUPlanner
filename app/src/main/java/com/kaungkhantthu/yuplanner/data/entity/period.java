package com.kaungkhantthu.yuplanner.data.entity;

import io.realm.RealmObject;

/**
 * Created by kaungkhantthu on 12/11/16.
 */

public class period extends RealmObject {
    private int p;

    public period() {
    }

    public period(int p) {
        this.p = p;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
}
