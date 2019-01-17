package com.example.rkjc.news_app_2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.R;
import android.content.Context;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsItemDao newsDao();

    private static volatile NewsDatabase INSTANCE;

    static NewsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsDatabase.class, "news_database"). build();
                }
            }
        }
        return INSTANCE;
    }
}
