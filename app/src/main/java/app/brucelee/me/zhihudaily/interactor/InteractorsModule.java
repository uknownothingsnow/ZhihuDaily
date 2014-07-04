package app.brucelee.me.zhihudaily.interactor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/2/14.
 */
@Module(
        library = true
)
public class InteractorsModule {
    @Provides public LoginInteractor provideLoginInteractor() {
        return new LoginInteractorImpl();
    }

    @Provides public MainInteractor provideMainInteractor() {
        return new MainInteractorImpl();
    }
}
