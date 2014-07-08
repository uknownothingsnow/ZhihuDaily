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
    private RestAdapter restAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://news-at.zhihu.com/api/3")
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .build();
        ImageLoader.getInstance().init(config);

        objectGraph = ObjectGraph.create(getModules().toArray());
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    public static ZhihuApplication getInstance() {
        return instance;
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }
}
