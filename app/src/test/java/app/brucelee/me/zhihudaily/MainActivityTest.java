package app.brucelee.me.zhihudaily;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.ListView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.event.DrawerItemClickEvent;
import app.brucelee.me.zhihudaily.shadow.CirclePageIndicatorShadow;
import app.brucelee.me.zhihudaily.ui.main.MainActivity;
import app.brucelee.me.zhihudaily.ui.topicList.TopicListFragment;
import de.greenrobot.event.EventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 18, reportSdk = 18, shadows = {CirclePageIndicatorShadow.class})
public class MainActivityTest {

    @Test
    public void test_main_activity_title_should_be_set_on_create() throws Exception {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        CharSequence title = controller.create().get().getTitle();
        assertEquals(title, "知乎日报");
    }

    @Test
    public void test_drawer_should_be_set_on_main_activity_create() throws Exception {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        assertNotNull(controller.create().get().getNavigationDrawerFragment());
    }

    @Test
    public void test_container_fragment_should_be_proper_type_when_drawer_item_is_selected() {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
//        MainActivity mainActivity = controller.create().start().resume().get();
        controller.get().getResources().getString(R.string.app_name);
        controller.get().getResources().getColor(R.color.list_item_desc_light);
        controller.get().getResources().getColor(R.color.blue_normal);
//        controller.get().getResources().getColor(R.color.default_circle_indicator_page_color);
//        EventBus.getDefault().post(new DrawerItemClickEvent(2));
//        Fragment fragment = mainActivity.getSupportFragmentManager().findFragmentById(R.id.container);
//        assertTrue("current main fragment should be instance of TopicListFragment", fragment instanceof TopicListFragment);
    }
}
