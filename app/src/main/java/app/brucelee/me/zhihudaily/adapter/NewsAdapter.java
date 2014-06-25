package app.brucelee.me.zhihudaily.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.bean.News;

/**
 * Created by bruce on 6/25/14.
 */
public class NewsAdapter extends BaseAdapter {
    private List<News> news = new ArrayList<News>();
    private Context context;
    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.news.addAll(news);
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setNews(List<News> news) {
        this.news.clear();
        this.news.addAll(news);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_list_item, null);
        }
        ((TextView) convertView.findViewById(R.id.tv_new_list_item_title)).setText(news.get(position).title);
        return convertView;
    }
}
