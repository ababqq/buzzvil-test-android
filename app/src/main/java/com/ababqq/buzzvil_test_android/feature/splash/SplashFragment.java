package com.ababqq.buzzvil_test_android.feature.splash;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ababqq.buzzvil_test_android.R;
import com.ababqq.buzzvil_test_android.databinding.SplashFragmentBinding;
import com.ababqq.buzzvil_test_android.entity.ConfigResponse;
import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.feature.MainActivity;
import com.ababqq.buzzvil_test_android.models.AppDatabase;
import com.ababqq.buzzvil_test_android.viewmodels.SplashViewModel;
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;
import com.google.android.material.snackbar.Snackbar;

public class SplashFragment extends Fragment {

    private SplashViewModel mViewModel;
    private SplashFragmentBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(SplashViewModel.class);
        mViewModel.loadViewPagerConfig(new OnConfigFetchedListener() {
            @Override
            public void fetchedViewPagerConfig(Response response) {
                AppDatabase.saveViewPagerConfig(requireContext(), response);

                //Test Code
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    Intent intent = new Intent(requireContext(), MainActivity.class);
                    intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    requireActivity().finish();
                }, 600);
            }

            @Override
            public void fetchedFailViewPagerConfig(Throwable error) {
                Toast.makeText(requireContext(), getText(R.string.fail_fetched_config), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = mBinding.inflate(LayoutInflater.from(requireContext()));
        mBinding.setViewModel(mViewModel);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}