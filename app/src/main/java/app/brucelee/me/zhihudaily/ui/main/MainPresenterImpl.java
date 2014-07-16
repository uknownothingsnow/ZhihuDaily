package app.brucelee.me.zhihudaily.ui.main;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.interactor.MainInteractor;
import app.brucelee.me.zhihudaily.ui.newsList.NewsListFragment;
import app.brucelee.me.zhihudaily.ui.topicList.TopicListFragment;
import app.brucelee.me.zhihudaily.ui.login.LoginActivity;

/**
 * Created by bruce on 14-7-4.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private CharSequence title;

    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.view = mainView;
        title = ((Activity) view).getTitle();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position) {
            case 0:
                ((Activity) view).startActivity(LoginActivity.newIntent(view.getContext()));
                break;
            case 1:
                replaceFragment(R.id.container, new NewsListFragment());
                break;
            case 2:
                replaceFragment(R.id.container, new TopicListFragment());
                break;
            case 3:
                ((Activity) view).startActivity(LoginActivity.newIntent(view.getContext()));
                break;
            default:
                replaceFragment(R.id.container, new NewsListFragment());
        }
    }

    private void replaceFragment(int id, Fragment fragment) {
        ((FragmentActivity)view).getSupportFragmentManager().beginTransaction()
                .replace(id, fragment)
                .commit();
    }

    @Override
    public void restoreActionBar() {
        ActionBar actionBar = ((Activity) view).getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!view.getNavigationDrawerFragment().isDrawerOpen()) {
            ((Activity) view).getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
        }
        return true;
    }
}
