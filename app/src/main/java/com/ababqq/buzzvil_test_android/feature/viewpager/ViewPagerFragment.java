package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ababqq.buzzvil_test_android.R;
import com.ababqq.buzzvil_test_android.databinding.ViewPagerFragmentBinding;
import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.models.AppDatabase;
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerFragment extends Fragment implements OnViewPagerBtnClickListener, OnCampaignFetchedListener {
    private static final String TAG = ViewPagerFragment.class.getSimpleName();
    private ViewPagerViewModel mViewModel;
    private ViewPagerFragmentBinding mBinding;
    private ViewPagerAdapter mAdapter;

    private ScreenOnOffReceiver mScreenOnOffReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mViewModel = new ViewModelProvider(requireActivity()).get(ViewPagerViewModel.class);
        initFirstCampaignRatio();
        observeCampaignList();
        observeCampaignClick();
        if (mViewModel.getCampaignListFromDB().size() == 0)
            loadDataFromNetwork();
    }

    private void initFirstCampaignRatio() {
        mViewModel.setFirstCampaignRatio(AppDatabase.loadViewPagerConfig(requireContext()));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        mBinding = mBinding.inflate(LayoutInflater.from(requireContext()));
        mBinding.setViewModel(mViewModel);
        mBinding.setListener(this);
        mBinding.setIsFailedFetchData(false);
        initPager();
        if (mViewModel.getCampaignList().size() == 0)
            mViewModel.loadDataFromLocalDB();
        mAdapter.notifyDataSetChanged();
        initBroadcastReceiver();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        requireActivity().unregisterReceiver(mScreenOnOffReceiver);
    }

    private void observeCampaignList() {
        mViewModel.getCampaignListEv().observe(requireActivity(), campaignVO -> {
            Log.e(TAG, "notifyDataSetChanged : "+mAdapter.getItemCount());
            mAdapter.notifyDataSetChanged();
            if (mViewModel.getCampaignList().size() > 0)
                Log.e(TAG, mViewModel.getCampaignList().get(0).toString());
        });
    }

    private void observeCampaignClick() {
        mViewModel.navigateToViewerWithPosition().observe(requireActivity(), selectedPosition -> {
            navigateToCampaignViewer();
        });
    }

    private void initPager() {
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), getLifecycle(), mViewModel);
        mBinding.viewpagerPager.setAdapter(mAdapter);
    }

    private void initBroadcastReceiver() {
        mScreenOnOffReceiver = new ScreenOnOffReceiver(mViewModel);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        requireActivity().registerReceiver(mScreenOnOffReceiver, intentFilter);
    }
    private void loadDataFromNetwork(){
        mBinding.setIsFailedFetchData(false);
        mViewModel.loadDataFromNetwork(this);
    }

    @Override
    public void onFragmentChangeButtonClick() {
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_viewpager_fragment_to_bookmark_fragment);
    }

    @Override
    public void onDataReloadButtonClick() {
        loadDataFromNetwork();
    }

    private void navigateToCampaignViewer() {
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_viewpager_fragment_to_viewpager_detail_fragment_fragment);
    }

    @Override
    public void fetchedCampaign(Response response) {
        mViewModel.setCampaignItems(response);
    }

    @Override
    public void fetchedFailCampaign(Throwable error) {
        Log.e(TAG, error.getMessage());
        mBinding.setIsFailedFetchData(true);
    }
}