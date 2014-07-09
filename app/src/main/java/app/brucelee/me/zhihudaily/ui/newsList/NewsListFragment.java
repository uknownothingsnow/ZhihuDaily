package app.brucelee.me.zhihudaily.ui.newsList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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

import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.bean.TopNews;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import app.brucelee.me.zhihudaily.ui.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;

public class NewsListFragment extends BaseFragment implements NewsListView {
    ZhihuService service = ZhihuApplication.getInstance().getRestAdapter().create(ZhihuService.class);
    private static final String TAG = "NewsListFragment";
    private OnFragmentInteractionListener listener;
    @InjectView(android.R.id.list) ListView listView;
    private NewsAdapter newsAdapter;
    private TopNewsViewPagerAdapter topNewsViewPagerAdapter;
    @InjectView(R.id.ptr_layout) PullToRefreshLayout pullToRefreshLayout;
    @Inject NewsListPresenter presenter;

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

        initPullToRefresh();
        View headerView = initViewPagerIndicator(inflater);
        initListView(headerView);

        return view;
    }

    private void initListView(View headerView) {
        listView.setAdapter(newsAdapter);
        ((ListView) listView).addHeaderView(headerView);
        listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position != 0) {
                    presenter.onListItemClick(position - 1);
                }
            }
        });
    }

    @Override
    public ListView getListView() {
        return listView;
    }

    @Override
    public void setNewsItems(List<News> newsList, List<TopNews> topNewsList) {
        newsAdapter.setNewsList(newsList);
        newsAdapter.notifyDataSetChanged();

        topNewsViewPagerAdapter.setTopNews(topNewsList);
        topNewsViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public NewsAdapter getNewsAdapter() {
        return newsAdapter;
    }

    private void initPullToRefresh() {
        ActionBarPullToRefresh.from(this.getActivity())
            .options(Options.create()
                    .scrollDistance(.5f)
                    .build())
            .listener(this)
            .allChildrenArePullable()
            .setup(pullToRefreshLayout);
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != listener) {
            listener.onFragmentInteraction("");
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = listView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onRefreshStarted(View view) {
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
                pullToRefreshLayout.setRefreshComplete();
            }
        }.execute();
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
