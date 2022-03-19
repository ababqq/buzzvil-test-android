package com.ababqq.buzzvil_test_android.feature.viewpager;

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
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerFragment extends Fragment implements OnNavigateBtnClickListener {
    private static final String TAG = ViewPagerFragment.class.getSimpleName();
    private ViewPagerViewModel mViewModel;
    private ViewPagerFragmentBinding mBinding;
    private ViewPagerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mViewModel = new ViewModelProvider(requireActivity()).get(ViewPagerViewModel.class);
        observeCampaignList();
        observeCampaignClick();
        mViewModel.loadDataFromNetwork();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        mBinding = mBinding.inflate(LayoutInflater.from(requireContext()));
        mBinding.setViewModel(mViewModel);
        mBinding.setListener(this::onFragmentChangeButtonClick);
        initPager();
        mViewModel.loadDataFromLocalDB();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    private void observeCampaignList() {
        mViewModel.getCampaignListEv().observe(requireActivity(), campaignVO -> {
            Log.e(TAG,"notifyDataSetChanged");
            mAdapter.notifyDataSetChanged();
            if (mViewModel.getCampaignList().size() > 0)
                Log.e(TAG, mViewModel.getCampaignList().get(0).toString());
            //todo : error on here.
        });
    }
    private void observeCampaignClick() {
        mViewModel.navigateToViewerWithPosition().observe(requireActivity(), selectedPosition -> {
            navigateToCampaignViewer();
        });
    }

    private void initPager() {
        mAdapter = new ViewPagerAdapter(getActivity(), mViewModel);
        mBinding.viewpagerPager.setAdapter(mAdapter);

    }

    @Override
    public void onFragmentChangeButtonClick() {
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_viewpager_fragment_to_bookmark_fragment);
    }

    private void navigateToCampaignViewer() {
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_viewpager_fragment_to_viewpager_detail_fragment_fragment);
    }
}