package com.ababqq.buzzvil_test_android.feature.viewpager;

import static com.ababqq.buzzvil_test_android.models.CommonCode.BUNDLE_CAMPAIGN_ITEM_POSITION;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ababqq.buzzvil_test_android.databinding.ViewPagerItemBinding;
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerItem extends Fragment implements OnCampaignClickListener {
    private static final String TAG = ViewPagerItem.class.getSimpleName();
    private ViewPagerItemBinding mBinding;
    private ViewPagerViewModel mViewModel;
    private int mItemPosition;

    public ViewPagerItem() {
        // Required empty public constructor
    }

    public static ViewPagerItem newInstance(int position) {
        ViewPagerItem fragment = new ViewPagerItem();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_CAMPAIGN_ITEM_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mItemPosition = getArguments().getInt(BUNDLE_CAMPAIGN_ITEM_POSITION, 0);
        }

        mViewModel = new ViewModelProvider(requireActivity()).get(ViewPagerViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = mBinding.inflate(LayoutInflater.from(getContext()));
        mBinding.setItemPosition(mItemPosition);
        mBinding.setCampaignItem(mViewModel.getCampaignList().getValue().get(mItemPosition));
        mBinding.setListener(this::onCampaignItemClick);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCampaignItemClick(int position) {
        mViewModel.setCampaignViewer(position);
    }
}
