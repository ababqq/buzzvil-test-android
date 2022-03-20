package com.ababqq.buzzvil_test_android.feature.viewpager.bookmark;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ababqq.buzzvil_test_android.databinding.BookMarkFragmentBinding;
import com.ababqq.buzzvil_test_android.viewmodels.BookmarkViewModel;

public class BookmarkFragment extends Fragment {
    private static final String TAG = BookmarkFragment.class.getSimpleName();

    private BookmarkViewModel mViewModel;
    private BookMarkFragmentBinding mBinding;
    private BookmarkAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(BookmarkViewModel.class);
        observeCampaignList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = mBinding.inflate(LayoutInflater.from(requireContext()));
        mBinding.setViewModel(mViewModel);
        initPager();
        mViewModel.loadBookmarkFromLocalDB();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void observeCampaignList() {
        mViewModel.getCampaignListEv().observe(requireActivity(), campaignVO -> {
            Log.e(TAG, "notifyDataSetChanged : "+mAdapter.getItemCount());
            mAdapter.notifyDataSetChanged();
        });
    }
    private void initPager() {
        mAdapter = new BookmarkAdapter(getChildFragmentManager(), getLifecycle(), mViewModel);
        mBinding.bookmarkPager.setAdapter(mAdapter);
    }
}