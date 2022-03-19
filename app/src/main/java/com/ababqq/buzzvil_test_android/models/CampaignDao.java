package com.ababqq.buzzvil_test_android.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CampaignDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CampaignBean campaign);

    @Update
    void update(CampaignBean campaign);

    @Delete
    void delete(CampaignBean campaign);

    @Query("DELETE FROM campaign_table")
    void deleteAllCampaign();

    @Query("SELECT * FROM campaign_table")
    List<CampaignBean> getAllCampaign();
}