package com.ababqq.buzzvil_test_android.entity;

import com.ababqq.buzzvil_test_android.models.ArticleBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponse extends Response {
    @SerializedName("campaigns")
    @Expose
    public List<ArticleBean> campaignVOList = null;
}