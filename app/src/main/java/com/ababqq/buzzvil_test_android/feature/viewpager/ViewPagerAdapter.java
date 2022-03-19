package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

class ViewPagerAdapter extends FragmentStateAdapter {
    private static final String TAG = ViewPagerAdapter.class.getSimpleName();
    private final ViewPagerViewModel mViewModel;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ViewPagerViewModel viewModel) {
        super(fragmentActivity);
        mViewModel = viewModel;
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

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }
}
