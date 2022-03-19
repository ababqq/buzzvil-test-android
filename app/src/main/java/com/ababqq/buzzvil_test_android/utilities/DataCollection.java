package com.ababqq.buzzvil_test_android.utilities;

import android.util.Log;

import com.ababqq.buzzvil_test_android.models.AdVO;
import com.ababqq.buzzvil_test_android.models.ArticleVO;
import com.ababqq.buzzvil_test_android.models.CampaignVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataCollection {
    private static final String TAG = DataCollection.class.getSimpleName();

    public static List<CampaignVO> shuffleCampaigns(List<CampaignVO> campaignList) {
        Collections.shuffle(campaignList);
        return campaignList;
    }

    public static CampaignVO pickFirstCampaign(List<CampaignVO> list) {
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

        CampaignVO first = list.get(probabilityList.get(random.nextInt(probabilityList.size())));

        if (first instanceof AdVO)
            Log.e(TAG, first.id + " ads " + first.firstDisplayPriority + " " + first.firstDisplayWeight);
        else if (first instanceof ArticleVO)
            Log.e(TAG, first.id + " article " + first.firstDisplayPriority + " " + first.firstDisplayWeight);

        return first;
    }
}
