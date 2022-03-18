package com.ababqq.buzzvil_test_android.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.feature.splash.SplashRepository;
import com.ababqq.buzzvil_test_android.feature.viewpager.OnCampaignFetchedListener;
import com.ababqq.buzzvil_test_android.feature.viewpager.ViewPagerRepository;
import com.ababqq.buzzvil_test_android.models.CampaignVO;
import com.ababqq.buzzvil_test_android.mvvm.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerViewModel extends ViewModel {
    private final String TAG = ViewPagerViewModel.class.getSimpleName();
    private ViewPagerRepository mRepository = new ViewPagerRepository();

    private SingleLiveEvent<List<CampaignVO>> mCampaignList = new SingleLiveEvent<>();

    public SingleLiveEvent<List<CampaignVO>> getCampaignList() {
        if (mCampaignList.getValue() == null)
            mCampaignList.setValue(new ArrayList<>());
        return mCampaignList;
    }

    public void loadAdCampaigns(OnCampaignFetchedListener listener) {
        mRepository.requestAdCampaigns(listener);
    }

    public void setCampaignItems(Response response) {
        mCampaignList.setValue(response.campaignVOList);
    }
}