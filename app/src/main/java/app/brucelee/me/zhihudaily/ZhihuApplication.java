package app.brucelee.me.zhihudaily;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import retrofit.RestAdapter;

/**
 * Created by bruce on 6/25/14.
 */
public class ZhihuApplication extends Application {
    private static ZhihuApplication instance;
    private ObjectGraph objectGraph;

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

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .build();
        ImageLoader.getInstance().init(config);

        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
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

    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }
}
