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
import com.ababqq.buzzvil_test_android.models.AdBean;
import com.ababqq.buzzvil_test_android.models.ArticleBean;
import com.ababqq.buzzvil_test_android.models.CampaignBean;
import com.ababqq.buzzvil_test_android.mvvm.SingleLiveEvent;
import com.ababqq.buzzvil_test_android.utilities.DataCollection;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerViewModel extends AndroidViewModel {
    private final String TAG = ViewPagerViewModel.class.getSimpleName();
    private final int INIT_STATE = 3;

    private ViewPagerRepository mRepository = new ViewPagerRepository();
    private CampaignRepository mCampaignRepository;
    private SingleLiveEvent<Integer> mCampaignViewer = new SingleLiveEvent<>();
    private SingleLiveEvent<Integer> mCampaignListEv = new SingleLiveEvent<>();
    private List<CampaignBean> mCampaignList = new ArrayList();
    private List<CampaignBean> mCampaignBucketList = new ArrayList();

    private int state;
    private Integer mAdCount, mArticleCount, mAdRatio, mArticleRatio;

    public ViewPagerViewModel(@NonNull Application application) {
        super(application);
        mCampaignRepository = new CampaignRepository(application);
        mAdCount = 0;
        mArticleCount = 0;
    }

    public SingleLiveEvent<Integer> navigateToViewerWithPosition() {
        return mCampaignViewer;
    }

    public void setFirstCampaignRatio(String loadViewPagerConfig) {
        String config[] = loadViewPagerConfig.split(":");
        mAdRatio = Integer.parseInt(config[0]);
        mArticleRatio = Integer.parseInt(config[1]);
        checkSupplyToCrossRatioOrder();
    }

    public void loadDataFromLocalDB() {
        Log.e(TAG, "loadDataFromLocalDB");
        mCampaignList = getCampaignListFromDB();
        if (mCampaignList.size() > 0)
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
                Log.e(TAG, error.getMessage());
            }
        });
        mRepository.requestArticleCampaigns(new OnCampaignFetchedListener() {
            @Override
            public void fetchedCampaign(Response response) {
                setCampaignItems(response);
            }

            @Override
            public void fetchedFailCampaign(Throwable error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    public List<CampaignBean> getCampaignList() {
        return mCampaignList;
    }

    public List<CampaignBean> getCampaignListFromDB() {
        List<CampaignBean> list = new ArrayList<>(mCampaignRepository.getAllCampaign());

        for (int i=0; i<list.size(); i++){
            CampaignBean c = list.get(i);

            if (c.getType().equals(AdBean.class.getSimpleName()))
                Log.d(TAG, "AdBean");
            else if (c.getType().equals(ArticleBean.class.getSimpleName()))
                Log.d(TAG, "ArticleBean");
            else if (c.getType().equals(CampaignBean.class.getSimpleName()))
                Log.d(TAG, "CampaignBean");
        }

        return mCampaignRepository.getAllCampaign();
    }

    public void setCampaignItems(Response response) {
        if (response instanceof AdsResponse) {
            mCampaignBucketList.addAll(((AdsResponse) response).campaignVOList);
            state -= 1;
            Log.e(TAG, "Receive Ads size : " + ((AdsResponse) response).campaignVOList.size() + " " + mCampaignBucketList.size());
        } else if (response instanceof ArticlesResponse) {
            mCampaignBucketList.addAll(((ArticlesResponse) response).campaignVOList);
            state -= 2;
            Log.e(TAG, "Receive Article size : " + ((ArticlesResponse) response).campaignVOList.size() + " " + mCampaignBucketList.size());
        }
        if (state == 0) {
            if (mCampaignBucketList.size() > 0) {
                demandCrossRatioOrder(shuffleCampaigns());
                saveCampaignsToLocalDB();
                Log.e(TAG, "repo size : " + getCampaignListFromDB().size());
                Log.e(TAG, "list size : " + mCampaignList.size());
                mCampaignListEv.call();
            }
        }
    }

    public CampaignBean shuffleCampaigns() {
        if (mCampaignBucketList.size() <= 0)
            mCampaignBucketList = new ArrayList<>(mCampaignList);

        CampaignBean firstItem = DataCollection.pickFirstCampaign(mCampaignBucketList);
        mCampaignBucketList.remove(firstItem);
        mCampaignList.clear();
        mCampaignList = DataCollection.shuffleCampaigns(mCampaignBucketList);
        mCampaignList.add(0, firstItem);
        mCampaignBucketList.add(firstItem);

        return firstItem;
    }

    public void saveCampaignsToLocalDB() {
        mCampaignRepository.deleteAll();
        mCampaignRepository.insertAll(mCampaignList);
    }

    public void shuffleCampaignByAdsRatio() {
        boolean isNeedCrossType = false;
        if (mCampaignList.size() > 0 && mCampaignList.size() > 0) {
            CampaignBean firstCampaign = mCampaignList.get(0);

            CampaignBean c = firstCampaign;

            if (c.getType().equals(AdBean.class.getSimpleName()))
                Log.d(TAG, "----------AdBean");
            else if (c.getType().equals(ArticleBean.class.getSimpleName()))
                Log.d(TAG, "----------ArticleBean");
            else if (c.getType().equals(CampaignBean.class.getSimpleName()))
                Log.d(TAG, "----------CampaignBean");


            isNeedCrossType = !isRightCrossRatioOrder(firstCampaign);


            if (isNeedCrossType) {
                checkSupplyToCrossRatioOrder();
            }
            while (true) {
                if (firstCampaign.getClass().getSimpleName().equals(shuffleCampaigns().getClass().getSimpleName()) != isNeedCrossType)
                    break;
            }

            saveCampaignsToLocalDB();
            demandCrossRatioOrder(mCampaignList.get(0));
        }
    }

    private boolean checkSupplyToCrossRatioOrder() {
        if (mAdCount <= 0 && mArticleCount <= 0) {
            mAdCount = mAdRatio;
            mArticleCount = mArticleRatio;
            Log.e(TAG, "SupplyCrossRatioOrder : " + mAdCount + " " + mArticleCount);
            return true;
        }
        return false;
    }

    public void demandCrossRatioOrder(CampaignBean firstCampaign) {
        if (firstCampaign.getType().equals(AdBean.class.getSimpleName()))
            mAdCount--;
        if (firstCampaign.getType().equals(ArticleBean.class.getSimpleName()))
            mArticleCount--;
        Log.e(TAG, "demandCrossRatioOrder : " + mAdCount + " " + mArticleCount);
    }

    public boolean isRightCrossRatioOrder(CampaignBean firstCampaign) {
        if(firstCampaign.getType().equals(AdBean.class.getSimpleName())){
            if (mAdCount > 0)
                return true;
            else
                return false;
        } else if(firstCampaign.getType().equals(ArticleBean.class.getSimpleName())){
            if (mArticleCount > 0)
                return true;
            else
                return false;
        }
        return true;
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