package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.annotation.SuppressLint;
import android.util.Log;

import com.ababqq.buzzvil_test_android.api.TestApi;
import com.ababqq.buzzvil_test_android.entity.AdsResponse;
import com.ababqq.buzzvil_test_android.entity.ArticlesResponse;
import com.ababqq.buzzvil_test_android.models.AdBean;
import com.ababqq.buzzvil_test_android.models.ArticleBean;
import com.ababqq.buzzvil_test_android.models.CampaignBean;
import com.ababqq.buzzvil_test_android.network.RetrofitInstance;
import com.ababqq.buzzvil_test_android.utilities.Logger;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ViewPagerRepository {
    private static final String TAG = ViewPagerRepository.class.getSimpleName();

    private Observable<AdsResponse> getAdCampaigns() {
        return RetrofitInstance.getInstance().getRetrofit().create(TestApi.class)
                .getAdCampaigns();
    }

    private Observable<ArticlesResponse> getArticleCampaigns() {
        return RetrofitInstance.getInstance().getRetrofit().create(TestApi.class)
                .getArticleCampaigns();
    }

    @SuppressLint("CheckResult")
    public void requestAdCampaigns(OnCampaignFetchedListener listener) {
        getAdCampaigns()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        adsResponse -> {
                            //Logger.json(TAG, adsResponse);
                            for (int i=0; i<adsResponse.campaignVOList.size();i++){
                                adsResponse.campaignVOList.get(i).setType(AdBean.class.getSimpleName());
                            }
                            listener.fetchedCampaign(adsResponse);
                        },
                        error -> {
                            Log.d("requestViewPagerConfig", Objects.requireNonNull(error.getMessage()));
                            listener.fetchedFailCampaign(error);
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void requestArticleCampaigns(OnCampaignFetchedListener listener) {
        getArticleCampaigns()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        articlesResponse -> {
                            //Logger.json(TAG, articlesResponse);
                            for (int i=0; i<articlesResponse.campaignVOList.size();i++){
                                articlesResponse.campaignVOList.get(i).setType(ArticleBean.class.getSimpleName());
                            }
                            listener.fetchedCampaign(articlesResponse);
                        },
                        error -> {
                            Log.d("requestViewPagerConfig", Objects.requireNonNull(error.getMessage()));
                            listener.fetchedFailCampaign(error);
                        }
                );
    }
}
