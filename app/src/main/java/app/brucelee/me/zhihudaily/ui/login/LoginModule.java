package app.brucelee.me.zhihudaily.ui.login;

import javax.inject.Singleton;

import app.brucelee.me.zhihudaily.AppModule;
import app.brucelee.me.zhihudaily.interactor.LoginInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruce on 7/2/14.
 */
@Module(
        injects = LoginActivity.class,
        addsTo = AppModule.class
)
public class LoginModule {

    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides @Singleton public LoginView provideLoginView() {
        return loginView;
    }

    @Provides @Singleton public LoginPresenter provideLoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        return new LoginPresenterImpl(loginView, loginInteractor);
    }
}
