package com.kaungkhantthu.yuplanner;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by kaungkhantthu on 12/3/16.
 */

public class YuPlannerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
