package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.net.URL;
import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsRepository mRepository;
    private LiveData<List<NewsItem>> mAllNews;

    public NewsItemViewModel(Application application) {
        super(application);
        mRepository = new NewsRepository(application);
    }

    public LiveData<List<NewsItem>> getAllNews() {
        mAllNews = mRepository.getAllNews();
        return mAllNews;
    }

    public void syncNews(URL url) {
        mRepository.syncNews(url);
    }
}