package app.brucelee.me.zhihudaily.ui.main;

import app.brucelee.me.zhihudaily.interactor.MainInteractor;

/**
 * Created by bruce on 14-7-4.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.view = mainView;
        this.interactor = mainInteractor;
    }
}
