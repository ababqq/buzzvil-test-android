package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ababqq.buzzvil_test_android.R;
import com.ababqq.buzzvil_test_android.databinding.ViewPagerFragmentBinding;
import com.ababqq.buzzvil_test_android.entity.Response;
import com.ababqq.buzzvil_test_android.viewmodels.ViewPagerViewModel;

public class ViewPagerFragment extends Fragment implements OnButtonClickListener{
    private static final String TAG = ViewPagerFragment.class.getSimpleName();
    private ViewPagerViewModel mViewModel;
    private ViewPagerFragmentBinding mBinding;
    private ViewPagerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ViewPagerViewModel.class);
        observeCampaignList();
        observeCampaignClick();
        mViewModel.loadAdCampaigns(new OnCampaignFetchedListener() {
            @Override
            public void fetchedCampaign(Response response) {
                mViewModel.setCampaignItems(response);
            }

            @Override
            public void fetchedFailCampaign(Throwable error) {
                Toast.makeText(requireContext(), getText(R.string.fail_fetched_ads), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = mBinding.inflate(LayoutInflater.from(requireContext()));
        mBinding.setViewModel(mViewModel);
        mBinding.setListener(this::onFragmentChangeButtonClick);
        initPager();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void observeCampaignList() {
        mViewModel.getCampaignList().observe(requireActivity(), campaignVO -> {
            mAdapter.notifyDataSetChanged();
        });
    }
    private void observeCampaignClick() {
        mViewModel.navigateToViewerWithPosition().observe(requireActivity(), selectedPosition -> {
            navigateToCampaignViewer(selectedPosition);
        });
    }

    private void initPager() {
        mAdapter = new ViewPagerAdapter(requireActivity(), mViewModel);
        mBinding.viewpagerPager.setAdapter(mAdapter);
    }

    @Override
    public void onFragmentChangeButtonClick() {
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_viewpager_fragment_to_bookmark_fragment);
    }

    private void navigateToCampaignViewer(int selectedPosition) {
        Navigation.findNavController(mBinding.getRoot()).navigate(R.id.action_viewpager_fragment_to_viewpager_detail_fragment_fragment);
    }
}