package app.brucelee.me.zhihudaily.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;

import app.brucelee.me.zhihudaily.ZhihuApplication;
import dagger.ObjectGraph;

/**
 * Created by bruce on 7/7/14.
 */
public abstract class BaseFragment extends Fragment implements Moduable {

    private ObjectGraph activityGraph;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityGraph = ((ZhihuApplication) activity.getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityGraph = null;
    }
}
