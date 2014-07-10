package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.service.ZhihuService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/2/14.
 */
@Module(
        library = true,
        complete = false
)
public class InteractorsModule {
    @Provides public LoginInteractor provideLoginInteractor() {
        return new LoginInteractorImpl();
    }

    @Provides public MainInteractor provideMainInteractor() {
        return new MainInteractorImpl();
    }

    @Provides public NewsListInteractor provideNewsListInteractor(ZhihuService service) {
        return new NewsListInteractorImpl(service);
    }

    @Provides public NewsDetailInteractor provideNewsDetailInteractor(ZhihuService service) {
        return new NewsDetailInteractorImpl(service);
    }

    @Provides public TopicListInteractor provideTopicListInteractor(ZhihuService service) {
        return new TopicListInteractorImpl(service);
    }
}
