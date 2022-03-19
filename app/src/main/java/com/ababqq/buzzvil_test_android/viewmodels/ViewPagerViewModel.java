package com.ababqq.buzzvil_test_android.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ababqq.buzzvil_test_android.entity.AdsResponse;
import com.ababqq.buzzvil_test_android.entity.ArticlesResponse;
import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.feature.viewpager.CampaignRepository;
import com.ababqq.buzzvil_test_android.feature.viewpager.OnCampaignFetchedListener;
import com.ababqq.buzzvil_test_android.feature.viewpager.ViewPagerRepository;
import com.ababqq.buzzvil_test_android.models.CampaignBean;
import com.ababqq.buzzvil_test_android.mvvm.SingleLiveEvent;
import com.ababqq.buzzvil_test_android.utilities.DataCollection;
import com.ababqq.buzzvil_test_android.utilities.Logger;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerViewModel extends AndroidViewModel {
    private final String TAG = ViewPagerViewModel.class.getSimpleName();
    private ViewPagerRepository mRepository = new ViewPagerRepository();
    private CampaignRepository mCampaignRepository;
    private SingleLiveEvent<Integer> mCampaignViewer = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mCampaignListEv = new SingleLiveEvent<>();
    private List<CampaignBean> mCampaignList = new ArrayList();
    private List<CampaignBean> mCampaignBucketList = new ArrayList();
    private final int INIT_STATE = 3;
    private int state;

    public ViewPagerViewModel(@NonNull Application application) {
        super(application);
        mCampaignRepository = new CampaignRepository(application);
    }

    public SingleLiveEvent<Integer> navigateToViewerWithPosition() {
        return mCampaignViewer;
    }

    public void loadDataFromLocalDB() {
        Log.e(TAG, "loadDataFromLocalDB");
        mCampaignList = mCampaignRepository.getAllCampaign();
        mCampaignListEv.call();
    }
    public void loadDataFromNetwork() {
        Log.e(TAG, "loadDataFromNetwork");
        state = INIT_STATE;
        mCampaignBucketList.clear();
        mCampaignList.clear();
        mCampaignListEv.call();
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

    public List<CampaignBean> getCampaignList() {
        return mCampaignList;
    }
    public List<CampaignBean> getCampaignListFromDB() {
        return mCampaignRepository.getAllCampaign();
    }

    public void setCampaignItems(Response response) {
        if (response instanceof AdsResponse) {
            mCampaignBucketList.addAll(((AdsResponse) response).campaignVOList);
            state -= 1;
            Log.e(TAG, "Receive Ads size : "+((AdsResponse) response).campaignVOList.size()+" "+mCampaignBucketList.size());
        } else if (response instanceof ArticlesResponse) {
            mCampaignBucketList.addAll(((ArticlesResponse) response).campaignVOList);
            state -= 2;
            Log.e(TAG, "Receive Article size : "+((ArticlesResponse) response).campaignVOList.size()+" "+mCampaignBucketList.size());
        }
        if (state == 0) {
            CampaignBean firstItem = DataCollection.pickFirstCampaign(mCampaignBucketList);
            mCampaignBucketList.remove(firstItem);
            mCampaignList.clear();
            mCampaignList = DataCollection.shuffleCampaigns(mCampaignBucketList);
            mCampaignList.add(0, firstItem);
            mCampaignRepository.deleteAll();
            mCampaignRepository.insertAll(mCampaignList);
            Log.e(TAG, "repo size : "+mCampaignRepository.getAllCampaign().size());
            Log.e(TAG, "list size : "+mCampaignList.size());

            mCampaignListEv.call();
        }
    }

    public SingleLiveEvent<Integer> getCampaignListEv() {
        return mCampaignListEv;
    }

    public void setCampaignViewer(int position) {
        mCampaignViewer.setValue(position);
    }

    public CampaignBean getCampaignViewer() {
        return mCampaignList.get(mCampaignViewer.getValue());
    }

    public void removeCampaign() {
        Log.e(TAG, mCampaignList.size() + " before " + mCampaignViewer.getValue());
        mCampaignList.remove(mCampaignViewer.getValue().intValue());
        Log.e(TAG, mCampaignList.size() + " after " + mCampaignViewer.getValue());
    }
}