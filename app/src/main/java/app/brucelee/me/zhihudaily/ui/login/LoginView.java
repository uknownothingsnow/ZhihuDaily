package app.brucelee.me.zhihudaily.ui.login;

/**
 * Created by bruce on 7/2/14.
 */
public interface LoginView {
    public void showProgress();

    public void hideProgress();

    public void setEmailError();

    public void setPasswordError();

    public String getEmail();

    public String getPassword();
}
