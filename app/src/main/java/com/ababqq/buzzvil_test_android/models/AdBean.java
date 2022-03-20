package com.ababqq.buzzvil_test_android.models;


public class AdBean extends CampaignBean {

    public AdBean(Integer id, String name, String imageUrl, Integer firstDisplayPriority, Integer firstDisplayWeight, Integer frequency, String landingUrl) {
        super(id, name, imageUrl, firstDisplayPriority, firstDisplayWeight, frequency, landingUrl);
        this.setType(AdBean.class.getSimpleName());
    }
}