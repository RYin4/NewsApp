package com.example.rkjc.news_app_2;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class SyncIntentService extends IntentService {

    public SyncIntentService() {
        super("SyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SyncNewsTask.cancelNotification(this);
    }
}
