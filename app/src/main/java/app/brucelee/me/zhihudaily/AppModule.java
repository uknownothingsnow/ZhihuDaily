package app.brucelee.me.zhihudaily;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.interactor.InteractorsModule;
import dagger.Module;
import dagger.Provides;

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

    @Provides @Singleton public ZhihuApplication getApplication() {
        return application;
    }
}
