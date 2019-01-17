package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.R;
import com.firebase.jobdispatcher.BuildConfig;

//Schedule this service to start every 10 seconds with a 10 flex seconds using FirebaseJobDispatcher.
// It should start up when main activity loads, and should repeat forever.
// Also, set replace current to true. Make and pass in all the necessary tags for identification by the system. (5pts)

public class SyncNewsUtils {

    private static final int SYNC_SCHEDULE_SEC = 10;
    private static final int SYNC_FLEXTIME_SEC = 10;
    private static final String SYNC_JOB_TAG = "sync_job_tag";
    private static boolean sInitialize;

    synchronized public static void scheduleSync(@NonNull final Context context) {

        if (sInitialize) {
            return;
        }

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher firebaseDispatcher = new FirebaseJobDispatcher(driver);

        //activates the job service where it begins to sync news
        Job syncJob = firebaseDispatcher.newJobBuilder()
                .setService(SyncNewsJobService.class)
                .setTag(SYNC_JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(SYNC_SCHEDULE_SEC,
                        SYNC_SCHEDULE_SEC + SYNC_FLEXTIME_SEC))
                .setReplaceCurrent(true)
                .build();
        firebaseDispatcher.schedule(syncJob);
        sInitialize = true;
    }
}
