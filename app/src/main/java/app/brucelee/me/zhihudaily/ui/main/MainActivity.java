package app.brucelee.me.zhihudaily.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.ui.BaseActivity;
import app.brucelee.me.zhihudaily.ui.drawer.DrawerFragment;
import app.brucelee.me.zhihudaily.R;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements MainView {

    private DrawerFragment navigationDrawerFragment;
    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        navigationDrawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        navigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        presenter.onNavigationDrawerItemSelected(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return presenter.onCreateOptionsMenu(menu);
    }

    @Override
    public void replaceFragment(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    @Override
    public DrawerFragment getNavigationDrawerFragment() {
        return navigationDrawerFragment;
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
