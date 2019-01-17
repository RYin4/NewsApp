package com.example.rkjc.news_app_2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.util.ArrayList;

public class JsonUtils {

    //parse the JSON news
    public static ArrayList<NewsItem> parseNews(String jObject){

        ArrayList<NewsItem> newsItemList = new ArrayList<>();

        try {
            JSONObject mainJSONObject = new JSONObject(jObject);
            JSONArray items = mainJSONObject.getJSONArray("articles");

            for(int i = 0; i < items.length(); i++){
                JSONObject item = items.getJSONObject(i);
                newsItemList.add(new NewsItem(item.getString("author"), item.getString("title"),
                        item.getString("description"), item.getString("url"), item.getString("urlToImage"), item.getString("publishedAt") ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItemList;
    }
}


