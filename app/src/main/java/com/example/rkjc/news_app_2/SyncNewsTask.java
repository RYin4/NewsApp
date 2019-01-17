package com.example.rkjc.news_app_2;

import android.content.Context;
import android.util.Log;
import java.net.URL;

public class SyncNewsTask {
    private static final String TAG = "SynchronizeNewsTask";

    public static void cancelNotification(Context context) {
        NotificationUtils.clearAllNotifications(context);
    }

    public static void synchronizeNews(Context context) {
        NewsRepository mRepository = new NewsRepository(context);
        URL url = NetworkUtils.buildUrl("b3282c577785438a8c23efe931c987bb");
        mRepository.syncNews(url);
        NotificationUtils.NotifySync(context);
        Log.i(TAG, "Sync Successful");
    }
}

