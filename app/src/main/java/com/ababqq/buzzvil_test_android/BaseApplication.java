package com.ababqq.buzzvil_test_android;

import android.app.Application;

import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    private static final String LOG_TAG = "LOG";
    @Override
    public void onCreate() {
        super.onCreate();
        initLogSystem();
    }

    private void initLogSystem() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(3) // 2
                .methodOffset(1) //0
                //.logStrategy()
                .tag(LOG_TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
