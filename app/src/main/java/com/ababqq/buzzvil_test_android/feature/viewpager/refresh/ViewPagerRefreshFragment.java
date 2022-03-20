package com.ababqq.buzzvil_test_android.feature.viewpager.refresh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ababqq.buzzvil_test_android.databinding.ViewPagerFragmentBinding;

public class ViewPagerRefreshFragment extends Fragment{
    private static final String TAG = ViewPagerRefreshFragment.class.getSimpleName();

    private ViewPagerFragmentBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = mBinding.inflate(LayoutInflater.from(requireContext()));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Navigation.findNavController(mBinding.getRoot()).popBackStack();
    }
}