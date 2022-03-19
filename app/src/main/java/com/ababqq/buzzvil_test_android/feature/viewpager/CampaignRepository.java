package com.ababqq.buzzvil_test_android.feature.viewpager;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.ababqq.buzzvil_test_android.models.CampaignBean;
import com.ababqq.buzzvil_test_android.models.CampaignDao;
import com.ababqq.buzzvil_test_android.models.CampaignDatabase;

import java.util.ArrayList;
import java.util.List;

public class CampaignRepository {
    private final String TAG = CampaignRepository.class.getSimpleName();

    private CampaignDao campaignDao;

    public CampaignRepository(Application application) {
        CampaignDatabase database = CampaignDatabase.getInstance(application);
        campaignDao = database.campaignDao();
    }

    public void insert(CampaignBean campaignBean) {
        campaignDao.insert(campaignBean);
    }
    public void insertAll(List<CampaignBean> list) {
        for (int i=0; i<list.size();i++){
            list.get(i).setIndex(i);
            this.campaignDao.insert(list.get(i));
        }
    }
    public void update(CampaignBean campaignBean) {
        campaignDao.update(campaignBean);
    }
    public void delete(CampaignBean campaignBean) {
        campaignDao.delete(campaignBean);
    }
    public void deleteAll() {
        campaignDao.deleteAllCampaign();
    }
    public List<CampaignBean> getAll() {
        return campaignDao.getAllCampaign();
    }


    public void insertAsync(CampaignBean campaignBean) {
        new InsertCampaignAsyncTask(campaignDao).execute(campaignBean);
    }
    public void insertAllAsync(List<CampaignBean> campaignBeanList) {
        new InsertCampaignListAsyncTask(campaignDao).execute(campaignBeanList);
    }

    public void updateAsync(CampaignBean campaignBean) {
        new UpdateCampaignAsyncTask(campaignDao).execute(campaignBean);
    }

    public void deleteAsync(CampaignBean campaignBean) {
        new DeleteCampaignAsyncTask(campaignDao).execute(campaignBean);
    }

    public void deleteAllAsync() {
        new DeleteAllCampaignsAsyncTask(campaignDao).execute();
    }

    public List<CampaignBean> getAllCampaign() {
        return new ArrayList<>(campaignDao.getAllCampaign());
    }

    private static class InsertCampaignAsyncTask extends AsyncTask<CampaignBean, Void, Void> {
        private CampaignDao campaignDao;

        private InsertCampaignAsyncTask(CampaignDao campaignDao) {
            this.campaignDao = campaignDao;
        }

        @Override
        protected Void doInBackground(CampaignBean... campaignBeans) {
            campaignDao.insert(campaignBeans[0]);
            return null;
        }
    }
    private static class InsertCampaignListAsyncTask extends AsyncTask<List<CampaignBean>, Void, Void> {
        private CampaignDao campaignDao;

        private InsertCampaignListAsyncTask(CampaignDao campaignDao) {
            this.campaignDao = campaignDao;
        }

        @Override
        protected Void doInBackground(List<CampaignBean>... lists) {
            List<CampaignBean> list = lists[0];
            for (int i=0; i<list.size();i++){
                this.campaignDao.insert(list.get(i));
            }
            return null;
        }
    }

    private static class UpdateCampaignAsyncTask extends AsyncTask<CampaignBean, Void, Void> {
        private CampaignDao campaignDao;

        private UpdateCampaignAsyncTask(CampaignDao campaignDao) {
            this.campaignDao = campaignDao;
        }

        @Override
        protected Void doInBackground(CampaignBean... campaignBeans) {
            campaignDao.update(campaignBeans[0]);
            return null;
        }
    }

    private static class DeleteCampaignAsyncTask extends AsyncTask<CampaignBean, Void, Void> {
        private CampaignDao campaignDao;

        private DeleteCampaignAsyncTask(CampaignDao campaignDao) {
            this.campaignDao = campaignDao;
        }

        @Override
        protected Void doInBackground(CampaignBean... campaignBeans) {
            campaignDao.delete(campaignBeans[0]);
            return null;
        }
    }

    private static class DeleteAllCampaignsAsyncTask extends AsyncTask<CampaignBean, Void, Void> {
        private CampaignDao campaignDao;

        private DeleteAllCampaignsAsyncTask(CampaignDao campaignDao) {
            this.campaignDao = campaignDao;
        }

        @Override
        protected Void doInBackground(CampaignBean... campaignBeans) {
            campaignDao.deleteAllCampaign();
            return null;
        }
    }
}