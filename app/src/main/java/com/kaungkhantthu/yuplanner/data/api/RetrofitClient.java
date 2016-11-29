package com.kaungkhantthu.yuplanner.data.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaungkhantthu.yuplanner.data.entity.serializer.EventResponseSerializer;
import com.kaungkhantthu.yuplanner.data.entity.serializer.EventSerializer;

import java.util.concurrent.TimeUnit;

import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    public static String URL = APIConfig.BASE_URL;
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