package com.ababqq.buzzvil_test_android.feature.splash;

import android.annotation.SuppressLint;
import android.util.Log;

import com.ababqq.buzzvil_test_android.api.TestApi;
import com.ababqq.buzzvil_test_android.entity.ConfigResponse;
import com.ababqq.buzzvil_test_android.network.RetrofitInstance;
import com.ababqq.buzzvil_test_android.utilities.Logger;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashRepository {
    private static final String TAG = SplashRepository.class.getSimpleName();

    private Observable<ConfigResponse> getViewPagerConfig() {
        return RetrofitInstance.getInstance().getRetrofit().create(TestApi.class)
                .getConfig();
    }

    @SuppressLint("CheckResult")
    public void requestViewPagerConfig(OnConfigFetchedListener listener) {
        getViewPagerConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        configResponse -> {
                            Logger.json(TAG, configResponse);
                            listener.fetchedViewPagerConfig(configResponse);
                        },
                        error -> {
                            Log.d("requestViewPagerConfig", Objects.requireNonNull(error.getMessage()));
                            listener.fetchedFailViewPagerConfig(error);
                        }
                );
    }
}
