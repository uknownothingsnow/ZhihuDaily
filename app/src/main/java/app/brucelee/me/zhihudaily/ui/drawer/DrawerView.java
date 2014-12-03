package app.brucelee.me.zhihudaily.ui.drawer;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ListView;

/**
 * Created by bruce on 7/7/14.
 */
public interface DrawerView {
    ListView getListView();

    DrawerLayout getDrawerLayout();

    ActionBarDrawerToggle getDrawerToggle();

    View getContainerView();

    boolean isFromSavedInstanceState();
}
