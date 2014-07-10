package app.brucelee.me.zhihudaily;

import android.app.Application;

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

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton public Application provideApplication() {
        return application;
    }

    @Provides @Singleton public ZhihuService provideZhihuService(Application application) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://news-at.zhihu.com/api/3")
                .build();
        return restAdapter.create(ZhihuService.class);
    }
}
