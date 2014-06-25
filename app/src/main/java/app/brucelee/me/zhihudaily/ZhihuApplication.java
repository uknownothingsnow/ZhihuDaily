package app.brucelee.me.zhihudaily;

import android.app.Application;

/**
 * Created by bruce on 6/25/14.
 */
public class ZhihuApplication extends Application {
    private static ZhihuApplication mInstance;

    private String[] drawerTexts;
    public int[] drawerIcons;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        drawerTexts = new String[]{
                this.getString(R.string.drawer_home),
                this.getString(R.string.drawer_daily_topic),
                this.getString(R.string.drawer_fav),
                this.getString(R.string.drawer_app),
        };

        drawerIcons = new int[] {
                R.drawable.menu_home,
                R.drawable.menu_management,
                R.drawable.menu_collect,
                R.drawable.menu_feture,
        };
    }

    public static ZhihuApplication getInstance() {
        return mInstance;
    }

    public String[] getDrawerTexts() {
        return drawerTexts;
    }

    public int[] getDrawerIcons() {
        return drawerIcons;
    }
}
