package com.kaungkhantthu.yuplanner;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.google.firebase.crash.FirebaseCrash;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by kaungkhantthu on 12/3/16.
 */

public class YuPlannerApp extends Application {
    private RealmMigration realmMigration;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        Realm.init(this);
        setuprealmMigration();
        RealmConfiguration conf = new RealmConfiguration.Builder()
                                .schemaVersion(0)
                                .migration(realmMigration)
                                .build();
        Realm.setDefaultConfiguration(conf);
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


    private void setuprealmMigration(){

         realmMigration = new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                RealmSchema schema = realm.getSchema();

                Log.e( "migrate: ","old "+oldVersion+" "+" new"+ newVersion );
            }
        };

    }

    public static Context getContext() {
        return context;
    }
}
