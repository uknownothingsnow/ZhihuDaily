package app.brucelee.me.zhihudaily.service;

import app.brucelee.me.zhihudaily.bean.LatestNewsList;
import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.bean.TopicList;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by bruce on 6/25/14.
 */
public interface ZhihuService {
    @GET("/news/latest")
    LatestNewsList getLatestNewsList();
    @GET("/themes")
    TopicList getTopicList();
    @GET("/story/{id}")
    NewsDetail getNewsDetail(@Path("id") long id);

    @GET("/story/{id}")
    Observable<NewsDetail> getNewsDetailObservable(@Path("id") long id);
}
