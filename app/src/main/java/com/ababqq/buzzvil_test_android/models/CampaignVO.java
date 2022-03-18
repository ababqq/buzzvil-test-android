package com.ababqq.buzzvil_test_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CampaignVO {

   @SerializedName("id")
   @Expose
   public Integer id;
   @SerializedName("name")
   @Expose
   public String name;
   @SerializedName("imageUrl")
   @Expose
   public String imageUrl;
   @SerializedName("firstDisplayPriority")
   @Expose
   public Integer firstDisplayPriority;
   @SerializedName("firstDisplayWeight")
   @Expose
   public Integer firstDisplayWeight;
   @SerializedName("frequency")
   @Expose
   public Integer frequency;
   @SerializedName("landing_url")
   @Expose
   public String landingUrl;
}