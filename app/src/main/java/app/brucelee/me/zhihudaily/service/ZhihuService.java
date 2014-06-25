package app.brucelee.me.zhihudaily.service;

import java.util.List;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.bean.News;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by bruce on 6/25/14.
 */
public interface ZhihuService {
    @GET("/news/latest")
    LatestNewsList getLatestNews();
}
