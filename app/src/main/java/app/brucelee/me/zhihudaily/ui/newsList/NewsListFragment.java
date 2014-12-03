package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.addHeaderView(headerView);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(getContext(), (view, position) -> {
                // do whatever
                presenter.onListItemClick(position);
            })
        );
//        PauseOnScrollListener pauseOnScrollListener = new PauseOnScrollListener(ImageLoader.getInstance(), false, true, this);
//        recyclerView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                pauseOnScrollListener.onScrollStateChanged(view, scrollState);
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                int topRowVerticalPosition =
//                    (recyclerView == null || recyclerView.getChildCount() == 0) ?
//                        0 : recyclerView.getChildAt(0).getTop();
//                swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
//
//                pauseOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
//                if (newsAdapter.shouldRequestNextPage(firstVisibleItem, visibleItemCount, totalItemCount)) {
//                    if (!newsAdapter.isLoadingData() && !newsAdapter.isLoadAllFinished()) {
//                        newsAdapter.setIsLoadingData(true);
//                        presenter.loadMore();
//                    }
//                }
//            }
//        });

//        recyclerView.setOnItemClickListener((adapterView, view, position, id) -> {
//            if (position != 0) {
//                presenter.onListItemClick(position - 1);
//            }
//        });
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
