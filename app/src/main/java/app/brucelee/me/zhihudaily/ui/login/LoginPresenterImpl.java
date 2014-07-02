package app.brucelee.me.zhihudaily.ui.login;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;

import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.interactor.LoginInteractor;

/**
 * Created by bruce on 7/2/14.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginResultListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onValidationSucceeded() {
        loginInteractor.login(loginView.getEmail(), loginView.getPassword(), this);
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();

        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
        }
    }

    @Override
    public void onEmailError() {

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void onSuccess() {
        Toast.makeText(ZhihuApplication.getInstance(), "登录成功", Toast.LENGTH_LONG).show();
    }
}
