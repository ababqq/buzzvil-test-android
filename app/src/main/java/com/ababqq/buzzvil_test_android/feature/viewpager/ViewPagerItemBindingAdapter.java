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

    @BindingAdapter(value = {"campaignIdText"}, requireAll = false)
    public static void setCampaignIdTxt(TextView textView, String id) {
        textView.setText("id : "+id);
    }
    @BindingAdapter(value = {"campaignNameText"}, requireAll = false)
    public static void setCampaignNameTxt(TextView textView, String name) {
        textView.setText("name : "+name);
    }
    @BindingAdapter(value = {"campaignImageUrlText"}, requireAll = false)
    public static void setCampaignImageUrlTxt(TextView textView, String url) {
        textView.setText("imageUrl : "+url);
    }
    @BindingAdapter(value = {"campaignPriorityText"}, requireAll = false)
    public static void setCampaignPriorityText(TextView textView, String p) {
        textView.setText("priority : "+p);
    }
    @BindingAdapter(value = {"campaignWeightText"}, requireAll = false)
    public static void setCampaignWeightText(TextView textView, String w) {
        textView.setText("weight : "+w);
    }
    @BindingAdapter(value = {"campaignFrequencyText"}, requireAll = false)
    public static void setCampaignFrequencyText(TextView textView, String f) {
        textView.setText("frequency : "+f);
    }
    @BindingAdapter(value = {"campaignLandingUrlText"}, requireAll = false)
    public static void setCampaignName(TextView textView, String url) {
        textView.setText("landing : "+url);
    }

}
