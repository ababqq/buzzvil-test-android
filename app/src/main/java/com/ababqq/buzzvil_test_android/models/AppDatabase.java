package com.ababqq.buzzvil_test_android.models;

import static com.ababqq.buzzvil_test_android.models.CommonCode.PREFERENCE_FIRST_RATIO;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ababqq.buzzvil_test_android.entity.ConfigResponse;
import com.ababqq.buzzvil_test_android.entity.Response;

public class AppDatabase {
    private static final String TAG = AppDatabase.class.getSimpleName();

    public static void saveViewPagerConfig(Context context, Response response) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFERENCE_FIRST_RATIO, ((ConfigResponse)response).firstAdRatio);
        editor.commit();
        editor.apply();
    }
}
