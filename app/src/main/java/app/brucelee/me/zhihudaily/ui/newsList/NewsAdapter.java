package app.brucelee.me.zhihudaily.ui.newsList;

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
import app.brucelee.me.zhihudaily.ui.common.EndlessAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruce on 6/25/14.
 */
public class NewsAdapter extends EndlessAdapter<News> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    protected View doGetView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.news_list_item, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        News news = getItem(position);
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
            if (null != news.images && news.images.size() != 0) {
                ImageLoader.getInstance().displayImage(news.images.get(0), image);
            }
        }
    }
}
