package app.brucelee.me.zhihudaily.ui.newsList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import app.brucelee.me.zhihudaily.R;

public class TopNewsFragment extends Fragment {
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "imageUrl";

    private String title;
    private String imageUrl;

    public static TopNewsFragment newInstance(String title, String imageUrl) {
        TopNewsFragment fragment = new TopNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }
    public TopNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            imageUrl = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_news, container, false);
        ImageView imageView = ((ImageView) view.findViewById(R.id.iv_top_news_image));
        ImageLoader.getInstance().displayImage(imageUrl, imageView);
        return view;
    }
}
