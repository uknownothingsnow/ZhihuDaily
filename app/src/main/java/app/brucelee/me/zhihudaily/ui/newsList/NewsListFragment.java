package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.bean.TopNews;
import app.brucelee.me.zhihudaily.ui.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsListFragment extends BaseFragment implements NewsListView {
    private static final String TAG = "NewsListFragment";
    @InjectView(R.id.list) RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private TopNewsViewPagerAdapter topNewsViewPagerAdapter;
    @InjectView(R.id.swipe_container) SwipeRefreshLayout swipeRefreshLayout;
    @Inject NewsListPresenter presenter;
    @Inject Application application;

    private boolean loading = false;

    public NewsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsAdapter = new NewsAdapter(new ArrayList<>());
        topNewsViewPagerAdapter = new TopNewsViewPagerAdapter(getFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.inject(this, view);

//        View headerView = initViewPagerIndicator(inflater);
        initListView();
        initPullToRefresh();
        return view;
    }

    private void initListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addHeaderView(headerView);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(getContext(), (view, position) -> {
                // do whatever
                presenter.onListItemClick(position);
            })
        );
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (!loading) {
                    if (totalItemCount > 0 && (visibleItemCount + pastVisiblesItems + 1) >= totalItemCount) {
                        Log.d(TAG, "Start Load More!");
                        loading = true;
                        presenter.loadMore();
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onNewsFetched(List<News> newsList, List<TopNews> topNewsList) {
        newsAdapter.clear();
        newsAdapter.addNewsList(newsList);
        topNewsViewPagerAdapter.setTopNews(topNewsList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onMoreLoaded(List<News> newsList) {
        newsAdapter.addNewsList(newsList);
        swipeRefreshLayout.setRefreshing(false);
        loading = false;
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
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadMore();
            swipeRefreshLayout.setRefreshing(true);
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
//        View emptyView = recyclerView.getEmptyView();
//
//        if (emptyText instanceof TextView) {
//            ((TextView) emptyView).setText(emptyText);
//        }
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
