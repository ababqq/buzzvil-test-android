package com.ababqq.buzzvil_test_android.feature.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ababqq.buzzvil_test_android.R;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}