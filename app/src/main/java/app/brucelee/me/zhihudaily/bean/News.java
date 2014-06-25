package app.brucelee.me.zhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruce on 6/25/14.
 */
public class News {
    public String title;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("ga_prefix")
    public String gaPrefix;
    public List<String> images;
    public int type;
    public long id;
}
