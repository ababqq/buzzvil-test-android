package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.ababqq.buzzvil_test_android.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ViewPagerItemBindingAdapter {
    @BindingAdapter(value = {"campaignImageUrl"}, requireAll = false)
    public static void setCampaignImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .fitCenter()
                .into(imageView);
    }
}
