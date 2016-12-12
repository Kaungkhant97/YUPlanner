package com.kaungkhantthu.yuplanner.network.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kaungkhantthu.yuplanner.data.entity.eventserializer.EventResponseSerializer;
import com.kaungkhantthu.yuplanner.data.entity.eventserializer.EventSerializer;
import com.kaungkhantthu.yuplanner.data.entity.period;
import com.kaungkhantthu.yuplanner.data.entity.subjectserializer.SubjectResponseSerializer;
import com.kaungkhantthu.yuplanner.data.entity.subjectserializer.SubjectSerializer;
import com.kaungkhantthu.yuplanner.data.entity.subjectserializer.TimeTableSerializer;
import com.kaungkhantthu.yuplanner.data.entity.subjectserializer.periodSerializer;

import java.util.concurrent.TimeUnit;

import io.realm.RealmList;
import io.realm.RealmObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static String URL = APIConfig.BASE_URL;
    private static RetrofitClient mInstance;
    private RetrofitService mService;

    private RetrofitClient() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient httpClient;


        httpClient = new OkHttpClient().newBuilder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();


        //this code is to work both Realm and Gson
        Gson gson = null;
        try {
            gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }

                    }).registerTypeAdapter(Class.forName("io.realm.EventRealmProxy"), new EventSerializer())

                    .registerTypeAdapter(Class.forName("io.realm.EventResponseRealmProxy"), new EventResponseSerializer())
                    .registerTypeAdapter(Class.forName("io.realm.mSubjectResponseRealmProxy"), new SubjectResponseSerializer())
                    .registerTypeAdapter(Class.forName("io.realm.SubjectRealmProxy"), new SubjectSerializer())
                    .registerTypeAdapter(Class.forName("io.realm.TimetableRealmProxy"), new TimeTableSerializer())
                    .registerTypeAdapter(new TypeToken<RealmList<period>>() {}.getType(),
                            new periodSerializer())


                    .create();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        mService = retrofit.create(RetrofitService.class);


    }

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public RetrofitService getService() {
        return mService;
    }

}