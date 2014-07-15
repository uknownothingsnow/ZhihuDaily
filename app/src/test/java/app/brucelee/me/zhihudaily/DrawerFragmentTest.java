package app.brucelee.me.zhihudaily;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import app.brucelee.me.zhihudaily.shadow.CirclePageIndicatorShadow;
import app.brucelee.me.zhihudaily.ui.drawer.DrawerFragment;
import app.brucelee.me.zhihudaily.ui.main.MainActivity;

import static org.junit.Assert.assertNotNull;
import static org.robolectric.util.FragmentTestUtil.startFragment;
/**
 * Created by bruce on 7/15/14.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml", emulateSdk = 18, shadows = {CirclePageIndicatorShadow.class})
public class DrawerFragmentTest {
    private final ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
    private DrawerFragment fragment;

    @Before
    public void setUp() throws Exception
    {
        fragment = new DrawerFragment();
        startFragment(fragment);
    }

    @Test
    public void should_not_be_null() {
        assertNotNull(fragment);
    }
}
