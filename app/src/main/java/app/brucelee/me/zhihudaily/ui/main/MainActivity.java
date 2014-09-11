package app.brucelee.me.zhihudaily.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.tundem.aboutlibraries.Libs;
import com.tundem.aboutlibraries.ui.LibsActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.event.DrawerItemClickEvent;
import app.brucelee.me.zhihudaily.ui.BaseActivity;
import app.brucelee.me.zhihudaily.ui.IPresenter;
import app.brucelee.me.zhihudaily.ui.drawer.DrawerFragment;
import app.brucelee.me.zhihudaily.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;


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
    protected IPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //default select 1st
        presenter.onNavigationDrawerItemSelected(1);
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return presenter.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                gotoAbout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public DrawerFragment getNavigationDrawerFragment() {
        return navigationDrawerFragment;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void onEvent(DrawerItemClickEvent event) {
        presenter.onNavigationDrawerItemSelected(event.getPosition());
    }

    private void gotoAbout() {
        //Create an intent with context and the Activity class
        Intent i = new Intent(getApplicationContext(), LibsActivity.class);
        //Pass the fields of your application to the lib so it can find all external lib information
        i.putExtra(Libs.BUNDLE_FIELDS, Libs.toStringArray(R.string.class.getFields()));
        //Define the libs you want (only those who don't include the information, and are managed by the AboutLibraries library)
        //(OPTIONAL if all used libraries offer the information or are autoDetected)
        i.putExtra(Libs.BUNDLE_LIBS, new String[]{"crouton", "actionbarsherlock", "showcaseview"});

        //Display the library version (OPTIONAL)
        i.putExtra(Libs.BUNDLE_VERSION, true);
        //Display the library license (OPTIONAL
        i.putExtra(Libs.BUNDLE_LICENSE, true);

        //Set a title (OPTIONAL)
        i.putExtra(Libs.BUNDLE_TITLE, "Open Source");

        //Pass your theme (OPTIONAL)
        i.putExtra(Libs.BUNDLE_THEME, android.R.style.Theme_Holo);
        //Pass a custom accent color (OPTIONAL)
        i.putExtra(Libs.BUNDLE_ACCENT_COLOR, "#3396E5");
        //Pass the information if it should use the Translucent decor (OPTIONAL) -> requires ACCENT_COLOR
        i.putExtra(Libs.BUNDLE_TRANSLUCENT_DECOR, true);

        //start the activity
        startActivity(i);
    }
}
