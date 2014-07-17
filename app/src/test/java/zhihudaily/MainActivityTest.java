package zhihudaily;

import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;


import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.AppModule;
import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.event.DrawerItemClickEvent;
import app.brucelee.me.zhihudaily.ui.main.MainActivity;
import app.brucelee.me.zhihudaily.ui.main.MainPresenter;
import app.brucelee.me.zhihudaily.ui.topicList.TopicListFragment;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    static MainPresenter mainPresenter;

    private ZhihuApplication getApplication() {
        return (ZhihuApplication) Robolectric.application;
    }

    @Before
    public void setUp() {
        mainPresenter = mock(MainPresenter.class);
        getApplication().createScopedGraph(new TestMainModule()).inject(this);
    }

    @Module(
            injects = {
                    MainActivityTest.class,
                    TestMainActivity.class
            },
            addsTo = AppModule.class,
            overrides = true
    )
    static class TestMainModule {
        @Provides
        @Singleton
        MainPresenter provideMainPresenter() {
            return mainPresenter;
        }
    }

    @Test
    public void test_main_activity_title_should_be_set_on_create() throws Exception {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        CharSequence title = controller.create().get().getTitle();
        assertEquals(title, "知乎日报");
    }

    @Test
    public void test_drawer_should_be_set_on_main_activity_create() throws Exception {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        assertNotNull(activity.getNavigationDrawerFragment());
    }

    @Test
    public void test_drawer_should_default_selected_1() {
        Robolectric.buildActivity(TestMainActivity.class).create().visible().start().get();
        verify(mainPresenter).onNavigationDrawerItemSelected(1);
    }

    @Test
    public void test_click_drawer_item_should_work() {
        Robolectric.buildActivity(TestMainActivity.class).create().visible().start().resume().get();
        EventBus.getDefault().post(new DrawerItemClickEvent(2));
        verify(mainPresenter, times(1)).onNavigationDrawerItemSelected(2);
    }

    static class TestMainActivity extends MainActivity {
        @Override
        public List<Object> getModules() {
            return Arrays.<Object>asList(new TestMainModule());
        }
    }
}
