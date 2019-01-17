package com.example.rkjc.news_app_2;

import android.os.AsyncTask;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.Job;

//Implement a FirebaseJobService that uses your Repository method(s) to sync the database with the news api. (5pts)

public class SyncNewsJobService extends JobService {

    private AsyncTask<Void, Void, Void> mSyncTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        mSyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                //calls on synchronizeNews to begin a sync of news, using the api key
                SyncNewsTask.synchronizeNews(getApplicationContext());
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(jobParameters, false);
            }
        };
        mSyncTask.execute();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if(mSyncTask != null) mSyncTask.cancel(true);
        return false;
    }
}