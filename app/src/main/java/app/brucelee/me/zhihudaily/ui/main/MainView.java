package app.brucelee.me.zhihudaily.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;

import app.brucelee.me.zhihudaily.ui.drawer.DrawerFragment;
import app.brucelee.me.zhihudaily.ui.newsList.NewsListFragment;

/**
 * Created by bruce on 7/4/14.
 */
public interface MainView extends NewsListFragment.OnFragmentInteractionListener {
    public void replaceFragment(int id, Fragment fragment);

    public DrawerFragment getNavigationDrawerFragment();

    public Context getContext();
}
