package app.brucelee.me.zhihudaily.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import app.brucelee.me.zhihudaily.bean.News;
import app.brucelee.me.zhihudaily.bean.TopNews;
import app.brucelee.me.zhihudaily.ui.fragment.TopNewsFragment;

/**
 * Created by bruce on 6/26/14.
 */
public class TopNewsViewPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    private List<TopNews> hotNews = new ArrayList<TopNews>();

    public void setTopNews(List<TopNews> hotNews) {
        this.hotNews = hotNews;
        notifyDataSetChanged();
    }


    public TopNewsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TopNewsFragment.newInstance(hotNews.get(position).title, hotNews.get(position).image);
    }

    @Override
    public int getIconResId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return hotNews.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return hotNews.get(position).title;
    }
}