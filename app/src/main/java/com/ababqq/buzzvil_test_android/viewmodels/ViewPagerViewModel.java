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

    private SingleLiveEvent<Integer> mCampaignViewer = new SingleLiveEvent<>();
    private SingleLiveEvent<List<CampaignVO>> mCampaignList = new SingleLiveEvent<>();

    public SingleLiveEvent<Integer> navigateToViewerWithPosition() {
        return mCampaignViewer;
    }

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

    public void setCampaignViewer(int position) {
        mCampaignViewer.setValue(position);
    }

    public CampaignVO getCampaignViewer() {
        return mCampaignList.getValue().get(mCampaignViewer.getValue());
    }

    public void removeCampaign() {
        Log.e(TAG, mCampaignList.getValue().size()+"a"+mCampaignViewer.getValue());
        mCampaignList.getValue().remove(mCampaignViewer.getValue().intValue());
        mCampaignList.setValue(mCampaignList.getValue());
        Log.e(TAG, mCampaignList.getValue().size()+"b"+mCampaignViewer.getValue());
    }
}