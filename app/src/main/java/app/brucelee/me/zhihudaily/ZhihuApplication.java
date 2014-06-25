package app.brucelee.me.zhihudaily;

import android.app.Application;

import retrofit.RestAdapter;

/**
 * Created by bruce on 6/25/14.
 */
public class ZhihuApplication extends Application {
    private static ZhihuApplication instance;

    private String[] drawerTexts;
    public int[] drawerIcons;
    private RestAdapter restAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        drawerTexts = new String[]{
                this.getString(R.string.drawer_home),
                this.getString(R.string.drawer_daily_topic),
                this.getString(R.string.drawer_fav),
                this.getString(R.string.drawer_app),
                this.getString(R.string.drawer_setting),
        };

        drawerIcons = new int[] {
                R.drawable.menu_home,
                R.drawable.menu_management,
                R.drawable.menu_collect,
                R.drawable.menu_feture,
                R.drawable.menu_setting,
        };

        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://news-at.zhihu.com/api/3")
                .build();
    }

    public static ZhihuApplication getInstance() {
        return instance;
    }

    public String[] getDrawerTexts() {
        return drawerTexts;
    }

    public int[] getDrawerIcons() {
        return drawerIcons;
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }
}
