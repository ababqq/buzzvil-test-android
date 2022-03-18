package com.ababqq.buzzvil_test_android.feature.splash;

import com.ababqq.buzzvil_test_android.entity.Response;

public interface OnConfigFetchedListener {
    void fetchedViewPagerConfig(Response response);

    void fetchedFailViewPagerConfig(Throwable error);
}
