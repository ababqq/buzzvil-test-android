package com.ababqq.buzzvil_test_android.models;

import static com.ababqq.buzzvil_test_android.models.CommonCode.BASE_DATABASE;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ababqq.buzzvil_test_android.BaseApplication;

@Database(entities = {CampaignBean.class}, version = 1)
public abstract class CampaignDatabase extends RoomDatabase {
   private static final String TAG = CampaignDatabase.class.getSimpleName();
   private static CampaignDatabase instance;

   public abstract CampaignDao campaignDao();

   public static synchronized CampaignDatabase getInstance(Context context) {
      if (instance == null) {
         instance = Room.databaseBuilder(context.getApplicationContext(),
                 CampaignDatabase.class, BASE_DATABASE)
                 .fallbackToDestructiveMigration()
                 .allowMainThreadQueries()
                 //.addCallback(roomCallback)
                 .build();
      }

      return instance;
   }

   private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase db) {
         super.onCreate(db);
         new PopulateDbAsyncTask(instance).execute();
      }
   };

   private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
      private CampaignDao campaignDao;

      private PopulateDbAsyncTask(CampaignDatabase db) {
         campaignDao = db.campaignDao();
      }

      @Override
      protected Void doInBackground(Void... voids) {
         return null;
      }
   }
}