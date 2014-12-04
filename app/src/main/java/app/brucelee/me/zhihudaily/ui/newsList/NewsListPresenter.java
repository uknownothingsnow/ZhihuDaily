package app.brucelee.me.zhihudaily.ui.newsList;

/**
 * Created by bruce on 7/8/14.
 */
public interface NewsListPresenter {
    public void onListItemClick(final int position);

    public void firstTimeLoad();

    public void loadMore();
}
