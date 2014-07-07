package app.brucelee.me.zhihudaily.ui.main;

import android.view.Menu;

/**
 * Created by bruce on 14-7-4.
 */
public interface MainPresenter {
    public void onNavigationDrawerItemSelected(int position);

    public void restoreActionBar();

    public boolean onCreateOptionsMenu(Menu menu);
}
