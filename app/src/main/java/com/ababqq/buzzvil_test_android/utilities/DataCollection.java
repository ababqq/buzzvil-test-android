package com.ababqq.buzzvil_test_android.utilities;

import android.util.Log;

import com.ababqq.buzzvil_test_android.models.AdBean;
import com.ababqq.buzzvil_test_android.models.ArticleBean;
import com.ababqq.buzzvil_test_android.models.CampaignBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataCollection {
    private static final String TAG = DataCollection.class.getSimpleName();

    public static List<CampaignBean> shuffleCampaigns(List<CampaignBean> campaignList) {
        Collections.shuffle(campaignList);
        return new ArrayList<>(campaignList);
    }

    public static CampaignBean pickFirstCampaign(List<CampaignBean> list) {
        List<Integer> probabilityList = new ArrayList<>();
        int highestPriority = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (highestPriority > list.get(i).firstDisplayPriority)
                highestPriority = list.get(i).firstDisplayPriority;
        }

        for (int campaignIndex = 0; campaignIndex < list.size(); campaignIndex++) {
            if (highestPriority == list.get(campaignIndex).firstDisplayPriority) {
                for (int weightObj = 0; weightObj < list.get(campaignIndex).firstDisplayWeight; weightObj++)
                    probabilityList.add(campaignIndex);
            }
        }
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        CampaignBean first = list.get(probabilityList.get(random.nextInt(probabilityList.size())));

        if (first instanceof AdBean)
            Log.e(TAG, first.id + " ads " + first.firstDisplayPriority + " " + first.firstDisplayWeight);
        else if (first instanceof ArticleBean)
            Log.e(TAG, first.id + " article " + first.firstDisplayPriority + " " + first.firstDisplayWeight);

        return first;
    }
}
