package app.brucelee.me.zhihudaily.ui.newsDetail;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.AppModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/9/14.
 */
@Module(
        injects = NewsDetailActivity.class,
        addsTo = AppModule.class,
        library = true
)
public class NewsDetailModule {

    private NewsDetailView view;

    public NewsDetailModule(NewsDetailView view) {
        this.view = view;
    }

    @Provides @Singleton NewsDetailView provideNewsDetailView() {
        return view;
    }

    @Provides @Singleton NewsDetailPresenter provideNewsDetailPresenter(NewsDetailView view) {
        return new NewsDetailPresenterImpl(view);
    }
}
