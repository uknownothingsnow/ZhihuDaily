package zhihudaily;

import android.app.Activity;
import android.support.v4.app.Fragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;


import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.event.DrawerItemClickEvent;
import app.brucelee.me.zhihudaily.ui.main.MainActivity;
import app.brucelee.me.zhihudaily.ui.topicList.TopicListFragment;
import de.greenrobot.event.EventBus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

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
    public void test_container_fragment_should_be_proper_type_when_drawer_item_is_selected() {
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().start().resume().get();
        EventBus.getDefault().post(new DrawerItemClickEvent(2));
        Fragment fragment = mainActivity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertTrue("current main fragment should be instance of TopicListFragment", fragment instanceof TopicListFragment);
    }
}
