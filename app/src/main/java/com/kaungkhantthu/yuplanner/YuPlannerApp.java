package com.kaungkhantthu.yuplanner;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.firebase.crash.FirebaseCrash;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;

/**
 * Created by kaungkhantthu on 12/3/16.
 */

public class YuPlannerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        if(BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
        }
        if(!BuildConfig.DEBUG) {
            //this code handle error in app
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override public void uncaughtException(Thread paramThread, Throwable paramThrowable) {

                    FirebaseCrash.report(paramThrowable);

                    paramThrowable.printStackTrace();
                    System.exit(0);
                }
            });
        }
    }
}
