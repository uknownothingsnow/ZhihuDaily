package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.bean.TopNews;
import app.brucelee.me.zhihudaily.ui.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;

public class NewsListFragment extends BaseFragment implements NewsListView {
    private static final String TAG = "NewsListFragment";
    @InjectView(android.R.id.list) ListView listView;
    private NewsAdapter newsAdapter;
    private TopNewsViewPagerAdapter topNewsViewPagerAdapter;
    @InjectView(R.id.swipe_container) SwipeRefreshLayout swipeRefreshLayout;
    @Inject NewsListPresenter presenter;
    @Inject Application application;

    public NewsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsAdapter = new NewsAdapter(getActivity());
        topNewsViewPagerAdapter = new TopNewsViewPagerAdapter(getFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.inject(this, view);

        View headerView = initViewPagerIndicator(inflater);
        initListView(headerView);
        initPullToRefresh();
        return view;
    }

    private void initListView(View headerView) {
        listView.addHeaderView(headerView);
        listView.setAdapter(newsAdapter);
        PauseOnScrollListener pauseOnScrollListener = new PauseOnScrollListener(ImageLoader.getInstance(), false, true, this);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                pauseOnScrollListener.onScrollStateChanged(view, scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                pauseOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                if (newsAdapter.shouldRequestNextPage(firstVisibleItem, visibleItemCount, totalItemCount)) {
                    if (!newsAdapter.isLoadingData() && !newsAdapter.isLoadAllFinished()) {
                        newsAdapter.setIsLoadingData(true);
                        presenter.loadMore();
                    }
                }
            }
        });

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            if (position != 0) {
                presenter.onListItemClick(position - 1);
            }
        });
    }

    @Override
    public ListView getListView() {
        return listView;
    }

    @Override
    public void onNewsFetched(List<News> newsList, List<TopNews> topNewsList) {
        newsAdapter.clear();
        newsAdapter.addAll(newsList);
        topNewsViewPagerAdapter.setTopNews(topNewsList);
    }

    @Override
    public void onMoreLoaded(List<News> newsList) {
        newsAdapter.setIsLoadingData(false);
        newsAdapter.addAll(newsList);
    }

    @Override
    public NewsAdapter getNewsAdapter() {
        return newsAdapter;
    }

    @Override
    public Context getContext() {
        return application;
    }

    private void initPullToRefresh() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.theme_accent));
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        super.onPostExecute(result);

                        // Notify PullToRefreshLayout that the refresh has finished
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }.execute();
            }
        });
    }

    private View initViewPagerIndicator(LayoutInflater inflater) {
        View headerView = inflater.inflate(R.layout.news_list_header, null, false);
        ViewPager viewPager = (ViewPager) headerView.findViewById(R.id.pager);
        viewPager.setAdapter(topNewsViewPagerAdapter);
        CirclePageIndicator indicator = (CirclePageIndicator)headerView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        return headerView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = listView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onRefreshStarted(View view) {

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {

    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new NewsListModule(this));
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
