package com.ababqq.buzzvil_test_android.feature.viewpager;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ababqq.buzzvil_test_android.R;
import com.ababqq.buzzvil_test_android.databinding.SplashFragmentBinding;
import com.ababqq.buzzvil_test_android.databinding.ViewPagerFragmentBinding;
import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.feature.MainActivity;
import com.ababqq.buzzvil_test_android.feature.splash.OnConfigFetchedListener;
import com.ababqq.buzzvil_test_android.models.AppDatabase;
import com.ababqq.buzzvil_test_android.viewmodels.SplashViewModel;
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerFragment extends Fragment implements OnButtonClickListener{

    private ViewPagerViewModel mViewModel;
    private ViewPagerFragmentBinding mBinding;

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

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
        mBinding.setListener(this::onFragmentChangeButtonClick);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onFragmentChangeButtonClick() {
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_viewpager_fragment_to_bookmark_fragment);
    }
}