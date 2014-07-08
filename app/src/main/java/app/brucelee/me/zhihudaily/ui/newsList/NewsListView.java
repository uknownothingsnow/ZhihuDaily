package app.brucelee.me.zhihudaily.ui.newsList;

import android.widget.AbsListView;

import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by bruce on 7/8/14.
 */
public interface NewsListView extends AbsListView.OnItemClickListener, OnRefreshListener, AbsListView.OnScrollListener {
}
