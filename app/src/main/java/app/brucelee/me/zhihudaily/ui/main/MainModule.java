package app.brucelee.me.zhihudaily.ui.main;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.AppModule;
import app.brucelee.me.zhihudaily.interactor.MainInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/4/14.
 */
@Module(
        injects = MainActivity.class,
        addsTo = AppModule.class
)
public class MainModule {

    private MainView mainView;

    public MainModule(MainView mainView) {
        this.mainView =  mainView;
    }

    @Provides @Singleton public MainView provideMainView() {
        return mainView;
    }

    @Provides @Singleton public MainPresenter provideMainPresenter(MainView mainView, MainInteractor mainInteractor) {
        return new MainPresenterImpl(mainView, mainInteractor);
    }
}
