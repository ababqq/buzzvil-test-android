package com.ababqq.buzzvil_test_android.feature.viewpager.bookmark;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ababqq.buzzvil_test_android.viewmodels.BookmarkViewModel;

public class BookmarkAdapter extends FragmentStateAdapter {
    private static final String TAG = BookmarkAdapter.class.getSimpleName();
    private final BookmarkViewModel mViewModel;

    public BookmarkAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, BookmarkViewModel mViewModel) {
        super(fragmentManager, lifecycle);
        this.mViewModel = mViewModel;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return BookmarkItem.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getCampaignList().size();
    }
}
