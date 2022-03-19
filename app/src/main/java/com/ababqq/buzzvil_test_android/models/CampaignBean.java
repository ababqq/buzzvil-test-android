package com.ababqq.buzzvil_test_android.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "campaign_table")
public class CampaignBean {

    @PrimaryKey(autoGenerate = false)
    public Integer index;
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

    public CampaignBean(Integer id, String name, String imageUrl, Integer firstDisplayPriority, Integer firstDisplayWeight, Integer frequency, String landingUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.firstDisplayPriority = firstDisplayPriority;
        this.firstDisplayWeight = firstDisplayWeight;
        this.frequency = frequency;
        this.landingUrl = landingUrl;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getFirstDisplayPriority() {
        return firstDisplayPriority;
    }

    public void setFirstDisplayPriority(Integer firstDisplayPriority) {
        this.firstDisplayPriority = firstDisplayPriority;
    }

    public Integer getFirstDisplayWeight() {
        return firstDisplayWeight;
    }

    public void setFirstDisplayWeight(Integer firstDisplayWeight) {
        this.firstDisplayWeight = firstDisplayWeight;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getLandingUrl() {
        return landingUrl;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    @Override
    public String toString() {
        return "CampaignBean{" +
                "index=" + index +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", firstDisplayPriority=" + firstDisplayPriority +
                ", firstDisplayWeight=" + firstDisplayWeight +
                ", frequency=" + frequency +
                ", landingUrl='" + landingUrl + '\'' +
                '}';
    }
}