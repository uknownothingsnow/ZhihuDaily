package app.brucelee.me.zhihudaily;

import app.brucelee.me.zhihudaily.interactor.InteractorsModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/2/14.
 */
@Module(
        injects = {
                ZhihuApplication.class
        },
        includes = {
                InteractorsModule.class
        }
)
public class AppModule {

    private ZhihuApplication application;

    public AppModule(ZhihuApplication application) {
        this.application = application;
    }

    @Provides public ZhihuApplication getApplication() {
        return application;
    }
}
