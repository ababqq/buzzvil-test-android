package com.ababqq.buzzvil_test_android.viewmodels;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.ViewModel;

import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.feature.splash.OnConfigFetchedListener;
import com.ababqq.buzzvil_test_android.feature.splash.SplashRepository;
import com.ababqq.buzzvil_test_android.models.AppDatabase;

public class SplashViewModel extends ViewModel {
    private final String TAG = SplashViewModel.class.getSimpleName();
    private SplashRepository mRepository = new SplashRepository();

    public SplashViewModel() {
    }

    public void loadViewPagerConfig(OnConfigFetchedListener onConfigFetchedListener) {
        mRepository.requestViewPagerConfig(onConfigFetchedListener);
    }
}