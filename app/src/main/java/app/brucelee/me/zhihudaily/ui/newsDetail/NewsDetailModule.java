package app.brucelee.me.zhihudaily.ui.newsDetail;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.AppModule;
import app.brucelee.me.zhihudaily.interactor.NewsDetailInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/9/14.
 */
@Module(
        injects = NewsDetailActivity.class,
        addsTo = AppModule.class
)
public class NewsDetailModule {

    private NewsDetailView view;

    public NewsDetailModule(NewsDetailView view) {
        this.view = view;
    }

    @Provides @Singleton NewsDetailView provideNewsDetailView() {
        return view;
    }

    @Provides @Singleton NewsDetailPresenter provideNewsDetailPresenter(NewsDetailView view, NewsDetailInteractor interactor) {
        return new NewsDetailPresenterImpl(view, interactor);
    }
}
