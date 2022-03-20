package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ScreenOnOffReceiver extends BroadcastReceiver {
    private final String TAG = ScreenOnOffReceiver.class.getSimpleName();
    private ViewPagerViewModel mViewModel;

    public ScreenOnOffReceiver(ViewPagerViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case Intent.ACTION_SCREEN_ON:
                Log.e(TAG, "ON");
                mViewModel.loadDataFromNetwork();
                break;
            case Intent.ACTION_SCREEN_OFF:
                Log.e(TAG, "OFF");
                break;
        }
    }
}
