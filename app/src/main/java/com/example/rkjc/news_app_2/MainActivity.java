package com.example.rkjc.news_app_2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    //display your data in a list format
    private RecyclerView mRecyclerView;

    //bind data to the recycler view
    private NewsRecyclerViewAdapter mAdapter;

    //list of each news article
    private ArrayList<NewsItem> news = new ArrayList<>();


    private NewsItemViewModel mNewsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        mNewsViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        mAdapter = new NewsRecyclerViewAdapter(this, news);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        //activate firebase dispatcher in syncNewsUtils, sync news every 10 seconds
        SyncNewsUtils.scheduleSync(this);
        mNewsViewModel.getAllNews().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                mAdapter.setNews(newsItems);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.action_search) {
            URL url = NetworkUtils.buildUrl("b3282c577785438a8c23efe931c987bb");
            mNewsViewModel.syncNews(url);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
