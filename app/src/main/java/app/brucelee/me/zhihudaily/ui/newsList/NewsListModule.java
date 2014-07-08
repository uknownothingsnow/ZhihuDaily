package app.brucelee.me.zhihudaily.ui.newsList;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.interactor.NewsListInteractor;
import app.brucelee.me.zhihudaily.ui.main.MainModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/8/14.
 */
@Module(
        injects = {
                NewsListFragment.class,
        },
        addsTo = MainModule.class,
        library = true
)
public class NewsListModule {

    private NewsListView view;

    public NewsListModule(NewsListView view) {
        this.view = view;
    }

    @Provides @Singleton public NewsListView provideNewsListView() {
        return view;
    }

    @Provides @Singleton public NewsListPresenter provideNewsListPresenter(NewsListView view, NewsListInteractor interactor) {
        return new NewsListPresenterImpl(view, interactor);
    }
}
