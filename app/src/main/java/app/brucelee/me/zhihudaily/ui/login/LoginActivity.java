package app.brucelee.me.zhihudaily.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.ui.BaseActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @InjectView(R.id.btn_sign_in)
    ActionProcessButton actionProgressButton;
    @Required(order = 1, message = "邮箱不能为空")
    @Email(order = 2, message = "必须是合法的邮箱格式")
    @InjectView(R.id.et_email)
    EditText email;
    @Required(order = 3, message = "密码不能为空")
    @InjectView(R.id.et_password)
    EditText password;
    private Validator validator;

    @Inject
    LoginPresenter loginPresenter;

    public static Intent newIntent() {
        Intent intent = new Intent(ZhihuApplication.getInstance(), LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        actionProgressButton.setMode(ActionProcessButton.Mode.ENDLESS);
        actionProgressButton.setOnClickListener(this);
        validator = new Validator(this);
        validator.setValidationListener(loginPresenter);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new LoginModule(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public void showProgress() {
        actionProgressButton.setProgress(1);
    }

    @Override
    public void hideProgress() {
        actionProgressButton.setProgress(0);
    }

    @Override
    public void setEmailError() {
        email.setError("email error");
    }

    @Override
    public void setPasswordError() {
        password.setError("password error");
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void onClick(View view) {
        validator.validate();
    }
}
