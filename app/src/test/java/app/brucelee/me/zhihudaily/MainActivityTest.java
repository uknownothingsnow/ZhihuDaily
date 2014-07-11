package app.brucelee.me.zhihudaily;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ui.main.MainActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 18)
public class MainActivityTest {
    private final ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
    @Test
    public void test_MainActivity_title() throws Exception {
//        MainActivity mainActivity = controller.create().start().get();
//        assertNotNull(mainActivity.getNavigationDrawerFragment());
//        String appName = new MainActivity().getResources().getString(R.string.app_name);
//        assertThat(appName, equalTo("知乎日报"));
        String[] texts = Robolectric.application.getResources().getStringArray(R.array.drawer_texts);
        assertEquals(texts.length, 5);
    }
}
