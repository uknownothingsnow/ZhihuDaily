package app.brucelee.me.zhihudaily;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/11/14.
 */
@Module(

        // this is your production modules that you're overriding

        // here you override all the real objects with mocks
        overrides = true)
public class TestModule {
}