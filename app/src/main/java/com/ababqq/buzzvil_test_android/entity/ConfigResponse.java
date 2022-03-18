package com.ababqq.buzzvil_test_android.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigResponse extends Response {
   @SerializedName("firstAdRatio")
   @Expose
   public String firstAdRatio;
}
