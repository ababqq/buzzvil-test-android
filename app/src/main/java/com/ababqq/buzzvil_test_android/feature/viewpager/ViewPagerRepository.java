package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.annotation.SuppressLint;
import android.util.Log;

import com.ababqq.buzzvil_test_android.api.TestApi;
import com.ababqq.buzzvil_test_android.entity.AdsResponse;
import com.ababqq.buzzvil_test_android.entity.ConfigResponse;
import com.ababqq.buzzvil_test_android.feature.splash.OnConfigFetchedListener;
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

    @SuppressLint("CheckResult")
    public void requestAdCampaigns(OnCampaignFetchedListener listener) {
        getAdCampaigns()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        adsResponse -> {
                            Logger.json(TAG, adsResponse);
                            listener.fetchedCampaign(adsResponse);
                        },
                        error -> {
                            Log.d("requestViewPagerConfig", Objects.requireNonNull(error.getMessage()));
                            listener.fetchedFailCampaign(error);
                        }
                );
    }
}
