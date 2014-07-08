package app.brucelee.me.zhihudaily;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.interactor.InteractorsModule;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by bruce on 7/2/14.
 */
@Module(
        includes = {
                InteractorsModule.class
        },
        library = true
)
public class AppModule {

    private ZhihuApplication application;

    public AppModule(ZhihuApplication application) {
        this.application = application;
    }

    @Provides @Singleton public ZhihuApplication provideApplication() {
        return application;
    }

    @Provides @Singleton public ZhihuService provideZhihuService(ZhihuApplication application) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://news-at.zhihu.com/api/3")
                .build();
        return restAdapter.create(ZhihuService.class);
    }
}
