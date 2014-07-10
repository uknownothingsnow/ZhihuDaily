package app.brucelee.me.zhihudaily;

import org.robolectric.Robolectric;
import org.robolectric.TestLifecycleApplication;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bruce on 7/10/14.
 */
public class TestApplication extends ZhihuApplication {
    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }
}
