package app.brucelee.me.zhihudaily.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;

import app.brucelee.me.zhihudaily.R;
import app.brucelee.me.zhihudaily.ZhihuApplication;
import app.brucelee.me.zhihudaily.bean.Topic;
import app.brucelee.me.zhihudaily.bean.TopicList;
import app.brucelee.me.zhihudaily.service.ZhihuService;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by bruce on 6/26/14.
 */
public class TopicListFragment extends Fragment implements AbsListView.OnScrollListener {
    ZhihuService service = ZhihuApplication.getInstance().getRestAdapter().create(ZhihuService.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCards();
    }


    private void initCards() {
        final CardListView listView = (CardListView) getActivity().findViewById(R.id.clv_topic_card_list);
        listView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true, this));

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final TopicList topics = service.getTopicList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Card> cards = new ArrayList<Card>();
                        for (Topic topic : topics.others) {
                            CardExample card = new CardExample(getActivity(), topic);
                            cards.add(card);
                        }

                        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
                        if (listView!=null){
                            listView.setAdapter(mCardArrayAdapter);
                        }
                    }
                });
            }
        }).start();

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {

    }

    public class CardExample extends Card{
        private Topic topic;
        private Context context;
        public CardExample(Context context,Topic topic) {
            super(context, R.layout.topic_card);
            this.context = context;
            this.topic = topic;
            init();
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {
            TextView title = (TextView) parent.findViewById(R.id.tv_topic_card_title);
            TextView description = (TextView) parent.findViewById(R.id.tv_topic_card_description);
            ImageView image = (ImageView) parent.findViewById(R.id.iv_topic_card_image);
            title.setText(topic.name);
            description.setText(topic.description);
            ImageLoader.getInstance().displayImage(topic.image, image);
        }

        private void init(){
            //Add ClickListener
            setOnClickListener(new OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(getContext(), "Click Listener card", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
