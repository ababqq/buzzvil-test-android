package com.ababqq.buzzvil_test_android.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ababqq.buzzvil_test_android.entity.AdsResponse;
import com.ababqq.buzzvil_test_android.entity.ArticlesResponse;
import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.feature.viewpager.OnCampaignFetchedListener;
import com.ababqq.buzzvil_test_android.feature.viewpager.ViewPagerRepository;
import com.ababqq.buzzvil_test_android.models.CampaignVO;
import com.ababqq.buzzvil_test_android.mvvm.SingleLiveEvent;
import com.ababqq.buzzvil_test_android.utilities.DataCollection;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerViewModel extends ViewModel {
    private final String TAG = ViewPagerViewModel.class.getSimpleName();
    private ViewPagerRepository mRepository = new ViewPagerRepository();

    private SingleLiveEvent<Integer> mCampaignViewer = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mCampaignListEv = new SingleLiveEvent<>();
    private List<CampaignVO> mCampaignList = new ArrayList();
    private final int INIT_STATE = 3;
    private int state;

    public SingleLiveEvent<Integer> navigateToViewerWithPosition() {
        return mCampaignViewer;
    }

    public void loadData() {
        state = INIT_STATE;
        mCampaignList.clear();
        mRepository.requestAdCampaigns(new OnCampaignFetchedListener() {
            @Override
            public void fetchedCampaign(Response response) {
                setCampaignItems(response);
            }

            @Override
            public void fetchedFailCampaign(Throwable error) {

            }
        });
        mRepository.requestArticleCampaigns(new OnCampaignFetchedListener() {
            @Override
            public void fetchedCampaign(Response response) {
                setCampaignItems(response);
            }

            @Override
            public void fetchedFailCampaign(Throwable error) {

            }
        });
    }

    public List<CampaignVO> getCampaignList() {
        return mCampaignList;
    }

    public void setCampaignItems(Response response) {
        if (response instanceof AdsResponse) {
            mCampaignList.addAll(((AdsResponse) response).campaignVOList);
            state -= 1;
        } else if (response instanceof ArticlesResponse) {
            mCampaignList.addAll(((ArticlesResponse) response).campaignVOList);
            state -= 2;
        }
        if (state == 0) {
            CampaignVO firstItem = DataCollection.pickFirstCampaign(mCampaignList);
            mCampaignList.remove(firstItem);
            mCampaignList = DataCollection.shuffleCampaigns(mCampaignList);
            mCampaignList.add(0, firstItem);
            mCampaignListEv.call();
        }
    }

    public SingleLiveEvent<Integer> getCampaignListEv() {
        return mCampaignListEv;
    }

    public void setCampaignViewer(int position) {
        mCampaignViewer.setValue(position);
    }

    public CampaignVO getCampaignViewer() {
        return mCampaignList.get(mCampaignViewer.getValue());
    }

    public void removeCampaign() {
        Log.e(TAG, mCampaignList.size() + " before " + mCampaignViewer.getValue());
        mCampaignList.remove(mCampaignViewer.getValue().intValue());
        Log.e(TAG, mCampaignList.size() + " after " + mCampaignViewer.getValue());
    }

}