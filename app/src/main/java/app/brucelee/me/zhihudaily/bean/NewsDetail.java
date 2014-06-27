package app.brucelee.me.zhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bruce on 6/27/14.
 */
public class NewsDetail {
    public String body;
    @SerializedName("image_source")
    public String imageSource;
    public String title;
    public String image;
    @SerializedName("share_url")
    public String shareUrl;
    public List<String> js;
    @SerializedName("ga_prefix")
    public String gaPrefix;
    public int type;
    public long id;
    public List<String> css;
}
