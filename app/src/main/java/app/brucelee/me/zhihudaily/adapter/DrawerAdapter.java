package app.brucelee.me.zhihudaily.adapter;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;

/**
 * Created by bruce on 6/25/14.
 */
public class DrawerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflator;

    public DrawerAdapter(Context context) {
        mContext = context;
        mInflator = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return ZhihuApplication.getInstance().getDrawerTexts().length;
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
        ((ImageView)convertView.findViewById(R.id.iv_drawer_list_item_icon)).setImageResource(ZhihuApplication.getInstance().getDrawerIcons()[position]);
        ((TextView)convertView.findViewById(R.id.tv_drawer_list_item_text)).setText(ZhihuApplication.getInstance().getDrawerTexts()[position]);
        return convertView;
    }
}
