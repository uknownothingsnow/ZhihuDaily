package app.brucelee.me.zhihudaily.ui.drawer;

/**
 * Created by bruce on 7/7/14.
 */
public interface DrawerPresenter {
    void select(int position);

    void showActionBar();

    void onDrawerOpened();

    boolean isLearned();

    void initListView();

    void setUp();
}
