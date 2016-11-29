package com.kaungkhantthu.yuplanner.data.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

  public static final String API_BASE_URL = APIConfig.BASE_URL;

  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

  private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create());

  public static Retrofit retrofit = builder.client(httpClient.build()).build();

  public static <S> S createService(Class<S> serviceClass) {
    Retrofit retrofit = builder.client(httpClient.build()).build();
    return retrofit.create(serviceClass);
  }
}