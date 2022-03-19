package com.ababqq.buzzvil_test_android.api;

import com.ababqq.buzzvil_test_android.entity.AdsResponse;
import com.ababqq.buzzvil_test_android.entity.ArticlesResponse;
import com.ababqq.buzzvil_test_android.entity.ConfigResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TestApi {

    @GET("test_config.json")
    Observable<ConfigResponse> getConfig();

    @GET("test_ads.json")
    Observable<AdsResponse> getAdCampaigns();

    @GET("test_articles.json")
    Observable<ArticlesResponse> getArticleCampaigns();

}