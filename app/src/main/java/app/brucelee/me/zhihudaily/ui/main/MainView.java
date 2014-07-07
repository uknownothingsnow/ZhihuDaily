package app.brucelee.me.zhihudaily.ui.main;

import android.support.v4.app.Fragment;

import app.brucelee.me.zhihudaily.ui.fragment.NavigationDrawerFragment;
import app.brucelee.me.zhihudaily.ui.fragment.NewsListFragment;

/**
 * Created by bruce on 7/4/14.
 */
public interface MainView extends NavigationDrawerFragment.NavigationDrawerCallbacks, NewsListFragment.OnFragmentInteractionListener {
    public void replaceFragment(int id, Fragment fragment);

    public NavigationDrawerFragment getNavigationDrawerFragment();
}
