package app.brucelee.me.zhihudaily.service;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.bean.TopicList;
import retrofit.http.GET;

/**
 * Created by bruce on 6/25/14.
 */
public interface ZhihuService {
    @GET("/news/latest")
    LatestNewsList getLatestNewsList();
    @GET("/themes")
    TopicList getTopicList();
}
