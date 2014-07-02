package app.brucelee.me.zhihudaily.interactor;

import android.os.Handler;
import android.util.Log;

import app.brucelee.me.zhihudaily.ui.login.LoginResultListener;

/**
 * Created by bruce on 7/2/14.
 */
public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(String email, String password, final LoginResultListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess();
            }
        }, 2000);
    }
}
