package app.brucelee.me.zhihudaily.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.bean.News;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruce on 6/25/14.
 */
public class NewsAdapter extends BaseAdapter {
    private List<News> newsList = new ArrayList<News>();
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_list_item, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        News news = newsList.get(position);
        holder.updateView(news);
        return convertView;
    }

    static class Holder {
        @InjectView(R.id.tv_news_list_item_title) TextView title;
        @InjectView(R.id.iv_news_list_item_image) ImageView image;
        public Holder(View view) {
            ButterKnife.inject(this, view);
        }

        public void updateView(News news) {
            title.setText(news.title);
            if (news.images.size() != 0) {
                ImageLoader.getInstance().displayImage(news.images.get(0), image);
            }
        }
    }
}
