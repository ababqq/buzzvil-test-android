package com.ababqq.buzzvil_test_android.feature.viewpager;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ababqq.buzzvil_test_android.R;
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerFragment extends Fragment {

    private ViewPagerViewModel mViewModel;

    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewPagerViewModel.class);
        // TODO: Use the ViewModel
    }

}