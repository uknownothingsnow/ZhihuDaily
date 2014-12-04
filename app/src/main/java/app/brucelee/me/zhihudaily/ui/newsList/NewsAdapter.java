package app.brucelee.me.zhihudaily.ui.newsList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import app.brucelee.me.zhihudaily.ui.common.EndlessAdapter;
import app.brucelee.me.zhihudaily.ui.newsDetail.NewsDetailActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruce on 6/25/14.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {
    private List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        holder.updateView(newsList.get(i));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addNewsList(List<News> newsList) {
        this.newsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public News getItem(int position) {
        return this.newsList.get(position);
    }

    public void clear() {
        this.newsList.clear();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_news_list_item_title) TextView title;
        @InjectView(R.id.iv_news_list_item_image) ImageView image;
        public Holder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void updateView(News news) {
            title.setText(news.title);
            if (null != news.images && news.images.size() != 0) {
                ImageLoader.getInstance().displayImage(news.images.get(0), image);
            }
        }
    }
}
