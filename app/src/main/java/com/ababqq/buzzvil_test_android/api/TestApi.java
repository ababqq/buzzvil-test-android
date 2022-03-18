package com.ababqq.buzzvil_test_android.api;

import com.ababqq.motov_test_android.entity.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestApi {

    @GET("test_config.json")
    Observable<WeatherResponse> getConfig();

    @GET("test_ads.json")
    Observable<WeatherResponse> getAds();

    @GET("test_articles.json")
    Observable<WeatherResponse> getArticles();
}