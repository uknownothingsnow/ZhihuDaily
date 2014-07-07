package app.brucelee.me.zhihudaily.ui.main;

import android.support.v4.app.Fragment;

import app.brucelee.me.zhihudaily.ui.drawer.DrawerFragment;
import app.brucelee.me.zhihudaily.ui.fragment.NewsListFragment;

/**
 * Created by bruce on 7/4/14.
 */
public interface MainView extends DrawerFragment.NavigationDrawerCallbacks, NewsListFragment.OnFragmentInteractionListener {
    public void replaceFragment(int id, Fragment fragment);

    public DrawerFragment getNavigationDrawerFragment();
}
