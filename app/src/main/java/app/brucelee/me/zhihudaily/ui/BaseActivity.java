package app.brucelee.me.zhihudaily.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import app.brucelee.me.zhihudaily.ZhihuApplication;
import dagger.ObjectGraph;

/**
 * Created by bruce on 7/2/14.
 */
public abstract class BaseActivity extends FragmentActivity implements Injectable {
    private ObjectGraph activityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((ZhihuApplication) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract IPresenter getPresenter();
}
