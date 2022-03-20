package com.ababqq.buzzvil_test_android.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ababqq.buzzvil_test_android.feature.viewpager.bookmark.BookmarkRepository;
import com.ababqq.buzzvil_test_android.models.CampaignBean;
import com.ababqq.buzzvil_test_android.mvvm.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

public class BookmarkViewModel extends AndroidViewModel {

    private BookmarkRepository mBookmarkRepository;
    private SingleLiveEvent<Integer> mCampaignListEv = new SingleLiveEvent<>();
    private List<CampaignBean> mCampaignList = new ArrayList();

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        mBookmarkRepository = new BookmarkRepository(application);
    }
    public void loadBookmarkFromLocalDB(){
        mCampaignList = mBookmarkRepository.getAllCampaign();
        mCampaignListEv.call();
    }

    public SingleLiveEvent<Integer> getCampaignListEv() {
        return mCampaignListEv;
    }

    public List<CampaignBean> getCampaignList() {
        return mCampaignList;
    }
}