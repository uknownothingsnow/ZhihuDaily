package app.brucelee.me.zhihudaily.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;

import app.brucelee.me.zhihudaily.ZhihuApplication;
import dagger.ObjectGraph;

/**
 * Created by bruce on 7/7/14.
 */
public abstract class BaseFragment extends Fragment implements Moduabel {

    private ObjectGraph fragmentGraph;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentGraph = ((ZhihuApplication) activity.getApplication()).createScopedGraph(getModules().toArray());
        fragmentGraph.inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentGraph = null;
    }

    public ObjectGraph getFragmentGraph() {
        return fragmentGraph;
    }
}
