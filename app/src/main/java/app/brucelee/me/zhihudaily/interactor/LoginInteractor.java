package app.brucelee.me.zhihudaily.interactor;

import app.brucelee.me.zhihudaily.ui.login.LoginResultListener;

/**
 * Created by bruce on 7/2/14.
 */
public interface LoginInteractor {
    public void login(String email, String password, LoginResultListener listener);
}
