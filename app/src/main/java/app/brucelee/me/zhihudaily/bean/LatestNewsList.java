package app.brucelee.me.zhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bruce on 6/25/14.
 */
public class LatestNewsList {
    public String date;
    @SerializedName("stories")
    public List<News> news;
    @SerializedName("top_stories")
    public List<News> topNews;
}
