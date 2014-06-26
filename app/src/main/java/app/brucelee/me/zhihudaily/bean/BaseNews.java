package app.brucelee.me.zhihudaily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bruce on 6/26/14.
 */
public class BaseNews {
    public String title;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("ga_prefix")
    public String gaPrefix;
    public int type;
    public long id;
}
