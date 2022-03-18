package com.ababqq.buzzvil_test_android.utilities;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Logger {

    public static void v(String TAG, String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.v(message, args);
    }

    public static void d(String TAG, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.d(args);
    }

    public static void d(String TAG, String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.d(message, args);
    }

    public static void i(String TAG, String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.i(message, args);
    }

    public static void w(String TAG, String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.w(message, args);
    }

    public static void e(String TAG, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.d(args);
    }
    public static void e(String TAG, String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.e(message, args);
    }

    public static void wtf(String TAG, String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.wtf(message, args);
    }
    public static void json(String TAG, String message) {
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.json(message);
    }

    public static void json(String TAG, Object arg) {
        Gson gson = new Gson();
        JsonObject json = gson.toJsonTree(arg).getAsJsonObject();
        com.orhanobut.logger.Logger.t(TAG);
        com.orhanobut.logger.Logger.d(json);
    }
}
