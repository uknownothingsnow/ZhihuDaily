package app.brucelee.me.zhihudaily.ui.main;

import android.view.Menu;

import app.brucelee.me.zhihudaily.ui.IPresenter;

/**
 * Created by bruce on 14-7-4.
 */
public interface MainPresenter extends IPresenter {
    public void onNavigationDrawerItemSelected(int position);

    public void restoreActionBar();

    public boolean onCreateOptionsMenu(Menu menu);
}
