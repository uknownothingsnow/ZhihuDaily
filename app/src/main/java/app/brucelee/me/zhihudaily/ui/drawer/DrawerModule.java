package app.brucelee.me.zhihudaily.ui.drawer;

import android.content.res.TypedArray;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.data.DrawerIcons;
import app.brucelee.me.zhihudaily.data.DrawerTexts;
import app.brucelee.me.zhihudaily.ui.main.MainModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/7/14.
 */
@Module(
        injects = {
                DrawerFragment.class,
                DrawerAdapter.class
        },
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

    @Provides @Singleton @DrawerTexts
    String[] provideDrawerTexts(ZhihuApplication application) {
        return application.getResources().getStringArray(R.array.drawer_texts);
    }
    @Provides @Singleton @DrawerIcons
    int[] provideDrawerIcons(ZhihuApplication application) {
        TypedArray res = application.getResources().obtainTypedArray(R.array.drawer_icons);
        int[] ids = new int[res.length()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = res.getResourceId(i, 0);
        }
        return ids;
    }
}
