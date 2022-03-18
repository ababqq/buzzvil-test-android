package com.ababqq.buzzvil_test_android.entity;

import com.ababqq.buzzvil_test_android.models.CampaignVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class Response {
    @SerializedName("campaigns")
    @Expose
    public List<CampaignVO> campaignVOList = null;
}
