package app.brucelee.me.zhihudaily.ui.drawer;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.data.DrawerIcons;
import app.brucelee.me.zhihudaily.data.DrawerTexts;
import dagger.ObjectGraph;

/**
 * Created by bruce on 6/25/14.
 */
public class DrawerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflator;
    @Inject @DrawerTexts
    String[] drawerTexts;
    @Inject @DrawerIcons
    int[] drawerIcons;


    public DrawerAdapter(Context context, ObjectGraph objectGraph) {
        mContext = context;
        mInflator = LayoutInflater.from(mContext);
        objectGraph.inject(this);
    }

    @Override
    public int getCount() {
        return drawerTexts.length;
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
        if (null == convertView) {
            convertView = mInflator.inflate(R.layout.drawer_list_item, null);
        }
        ((ImageView)convertView.findViewById(R.id.iv_drawer_list_item_icon)).setImageResource(drawerIcons[position]);
        ((TextView)convertView.findViewById(R.id.tv_drawer_list_item_text)).setText(drawerTexts[position]);
        return convertView;
    }
}
