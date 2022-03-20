package com.ababqq.buzzvil_test_android.feature.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final String TAG = ViewPagerAdapter.class.getSimpleName();
    private final ViewPagerViewModel mViewModel;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ViewPagerViewModel mViewModel) {
        super(fragmentManager, lifecycle);
        this.mViewModel = mViewModel;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ViewPagerItem.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getCampaignList().size();
    }
}
