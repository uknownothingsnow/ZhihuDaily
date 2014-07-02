package app.brucelee.me.zhihudaily.ui.login;

/**
 * Created by bruce on 7/2/14.
 */
public interface LoginResultListener {
    public void onEmailError();

    public void onPasswordError();

    public void onSuccess();
}
