package com.ababqq.buzzvil_test_android.feature.viewpager.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ababqq.buzzvil_test_android.databinding.SplashFragmentBinding;
import com.ababqq.buzzvil_test_android.databinding.ViewPagerFragmentDetailBinding;
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerItemDetailFragment extends Fragment {

    private ViewPagerViewModel mViewModel;
    private ViewPagerFragmentDetailBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ViewPagerViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = mBinding.inflate(LayoutInflater.from(requireContext()));
        mBinding.setViewModel(mViewModel);
        mBinding.setCampaignItem(mViewModel.getCampaignViewer());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}