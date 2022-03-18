package com.ababqq.buzzvil_test_android.feature.viewpager;

import com.ababqq.buzzvil_test_android.entity.Response;

public interface OnCampaignFetchedListener {
    void fetchedCampaign(Response response);

    void fetchedFailCampaign(Throwable error);
}
