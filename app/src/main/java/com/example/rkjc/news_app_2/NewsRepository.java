package com.example.rkjc.news_app_2;

import android.app.Application;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;


public class NewsRepository {

    private com.example.rkjc.news_app_2.NewsItemDao mNewsDao;
    private LiveData<List<NewsItem>> mAllNews;

    NewsRepository(Context application) {
        com.example.rkjc.news_app_2.NewsDatabase db = com.example.rkjc.news_app_2.NewsDatabase.getDatabase(application);
        mNewsDao = db.newsDao();
        mAllNews = mNewsDao.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNews() {
        new allTheNewsAsyncTask(mNewsDao).execute();
        return mAllNews;
    }

    public void syncNews(URL url) {
        new syncTheNewsAsyncTask(mNewsDao).execute(url);
    }

    private class allTheNewsAsyncTask extends AsyncTask<Void, Void, Void> {

        private com.example.rkjc.news_app_2.NewsItemDao mAsyncTaskDao;
        allTheNewsAsyncTask(com.example.rkjc.news_app_2.NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAllNews = mAsyncTaskDao.loadAllNewsItems();
            return null;
        }
    }

    private class syncTheNewsAsyncTask extends AsyncTask<URL, Void, Void> {

        private com.example.rkjc.news_app_2.NewsItemDao mAsyncTaskDao;
        syncTheNewsAsyncTask(com.example.rkjc.news_app_2.NewsItemDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(URL... urls) {
            String newsSearchResults = "";
            ArrayList<NewsItem> news = new ArrayList<>();

            try {
                newsSearchResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            mAsyncTaskDao.clearAll();
            news = JsonUtils.parseNews(newsSearchResults);

            mAsyncTaskDao.insert(news);

            return null;
        }
    }
}