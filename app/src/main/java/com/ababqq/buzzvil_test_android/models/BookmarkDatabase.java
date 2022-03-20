package com.ababqq.buzzvil_test_android.models;

import static com.ababqq.buzzvil_test_android.models.CommonCode.BASE_DATABASE;
import static com.ababqq.buzzvil_test_android.models.CommonCode.BOOKMARK_DATABASE;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CampaignBean.class}, version = 1)
public abstract class BookmarkDatabase extends RoomDatabase {
   private static final String TAG = BookmarkDatabase.class.getSimpleName();
   private static BookmarkDatabase instance;

   public abstract CampaignDao campaignDao();

   public static synchronized BookmarkDatabase getInstance(Context context) {
      if (instance == null) {
         instance = Room.databaseBuilder(context.getApplicationContext(),
                 BookmarkDatabase.class, BOOKMARK_DATABASE)
                 .fallbackToDestructiveMigration()
                 .allowMainThreadQueries()
                 //.addCallback(roomCallback)
                 .build();
         Log.e(TAG,"database ##");
      }else
         Log.e(TAG,"duple database");

      return instance;
   }

   private static Callback roomCallback = new Callback() {
      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase db) {
         super.onCreate(db);
         new PopulateDbAsyncTask(instance).execute();
      }
   };

   private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
      private CampaignDao campaignDao;

      private PopulateDbAsyncTask(BookmarkDatabase db) {
         campaignDao = db.campaignDao();
      }

      @Override
      protected Void doInBackground(Void... voids) {
         return null;
      }
   }
}