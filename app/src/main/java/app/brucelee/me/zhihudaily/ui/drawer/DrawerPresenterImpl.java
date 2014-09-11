package app.brucelee.me.zhihudaily.ui.drawer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.event.DrawerItemClickEvent;
import app.brucelee.me.zhihudaily.ui.BaseFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by bruce on 7/7/14.
 */
public class DrawerPresenterImpl implements DrawerPresenter {

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    private DrawerView view;
    private int currentSelectedPosition = 1;
    private boolean userLearnedDrawer;

    public DrawerPresenterImpl(DrawerView view) {
        this.view = view;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(((Fragment)view).getActivity());
        userLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);
    }

    @Override
    public void select(int position) {
        //因为添加了header view，所以-1
        currentSelectedPosition = position - 1;
        if (view.getListView() != null) {
            view.getListView().setItemChecked(position, true);
        }
        if (view.getDrawerLayout() != null) {
            view.getDrawerLayout().closeDrawer(view.getContainerView());
        }
        EventBus.getDefault().post(new DrawerItemClickEvent(position));
    }

    @Override
    public void showActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    @Override
    public void onDrawerOpened() {
        if (!userLearnedDrawer) {
            // The user manually opened the drawer; store this flag to prevent auto-showing
            // the navigation drawer automatically in the future.
            userLearnedDrawer = true;
            SharedPreferences sp = PreferenceManager
                    .getDefaultSharedPreferences(((Fragment) view).getActivity());
            sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
        }
    }

    @Override
    public boolean isLearned() {
        return userLearnedDrawer;
    }

    @Override
    public void initListView() {
        view.getListView().setAdapter(new DrawerAdapter(getActionBar().getThemedContext(), ((BaseFragment) view).getFragmentGraph()));
        view.getListView().setItemChecked(currentSelectedPosition, true);
    }

    @Override
    public void setUp() {
        view.getDrawerLayout().setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        showActionBar();

        if (!isLearned() && !view.isFromSavedInstanceState()) {
            view.getDrawerLayout().openDrawer(view.getContainerView());
            new ShowcaseView.Builder(((Fragment)view).getActivity())
                    .setTarget(new ActionViewTarget(((Fragment)view).getActivity(), ActionViewTarget.Type.HOME))
                    .setContentTitle("点左上角按钮或者向左滑动收起侧边栏")
                    .setContentText("")
                    .setStyle(R.style.CustomShowcaseTheme)
                    .hideOnTouchOutside()
                    .build();
        }

        // Defer code dependent on restoration of previous instance state.
        view.getDrawerLayout().post(new Runnable() {
            @Override
            public void run() {
                view.getDrawerToggle().syncState();
            }
        });
    }

    private ActionBar getActionBar() {
        return ((Fragment) view).getActivity().getActionBar();
    }
}
