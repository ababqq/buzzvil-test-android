package com.ababqq.buzzvil_test_android.entity;

import com.ababqq.buzzvil_test_android.models.AdBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdsResponse extends Response {
    @SerializedName("campaigns")
    @Expose
    public List<AdBean> campaignVOList = null;
}