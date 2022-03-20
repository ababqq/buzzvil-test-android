package com.ababqq.buzzvil_test_android.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/";
    private static OkHttpClient mOkHttpClient = null;

    private RetrofitInstance() {
        initOkHttpClient();
    }

    public static RetrofitInstance getInstance() {
        return LazyHolder.INSTANCE;
    }

    private void initOkHttpClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(4000, TimeUnit.MILLISECONDS)
                .build();
    }

    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class LazyHolder {
        private static final RetrofitInstance INSTANCE = new RetrofitInstance();
    }
}