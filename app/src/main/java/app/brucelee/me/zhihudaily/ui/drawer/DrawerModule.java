package app.brucelee.me.zhihudaily.ui.drawer;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.ui.main.MainModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/7/14.
 */
@Module(
        injects = DrawerFragment.class,
        addsTo = MainModule.class,
        library = true
)
public class DrawerModule {

    private DrawerView view;

    public DrawerModule(DrawerView view) {
        this.view = view;
    }

    @Provides @Singleton DrawerView provideDrawerView() {
        return view;
    }

    @Provides @Singleton DrawerPresenter provideDrawerPresenter(DrawerView view) {
        return new DrawerPresenterImpl(view);
    }
}
