package app.brucelee.me.zhihudaily.ui.newsDetail;

import com.google.common.base.Optional;

import app.brucelee.me.zhihudaily.bean.NewsDetail;
import app.brucelee.me.zhihudaily.interactor.NewsDetailInteractor;
import app.brucelee.me.zhihudaily.ui.OnFirstLoadListener;
import rx.Observer;
import rx.Subscription;

import static com.google.common.base.Optional.fromNullable;

/**
 * Created by bruce on 7/9/14.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, OnFirstLoadListener<NewsDetail> {

    private NewsDetailView view;
    private NewsDetailInteractor interactor;
    private Subscription request;

    public NewsDetailPresenterImpl(NewsDetailView view, NewsDetailInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void show(NewsDetail newsDetail) {
        String newContent = getCustomerCss() + buildCssAndJs(newsDetail) + insertHeaderImage(newsDetail);
        view.getWebView().loadData(newContent, "text/html; charset=UTF-8", null);
    }

    @Override
    public void fetch() {
        interactor.fetch(view.getId(), this);
    }

    @Override
    public void onPause() {
        request.unsubscribe();
    }

    @Override
    public void onResume() {
        request = interactor.doFetch(view.getId(), new Observer<NewsDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewsDetail newsDetail) {
                onFirstLoad(newsDetail);
            }
        });
    }

    private String getCustomerCss() {
        return "<style type=\"text/css\">\n" +
                "\t.headline .img-wrap {\n" +
                "\t\tposition: relative;\n" +
                "\t\tmax-height: 375px;\n" +
                "\t\toverflow: hidden;\n" +
                "\t}\n" +
                "\n" +
                "\t.headline .headline-title {\n" +
                "\t\tmargin: 20px 0;\n" +
                "\t\tbottom: 10px;\n" +
                "\t\tz-index: 1;\n" +
                "\t\tposition: absolute;\n" +
                "\t\tcolor: white;\n" +
                "\t\ttext-shadow: 0px 1px 2px rgba(0,0,0,0.3);\n" +
                "\t}\n" +
                "\n" +
                "\t.headline .img-source {\n" +
                "\t\tposition: absolute;\n" +
                "\t\tbottom: 10px;\n" +
                "\t\tz-index: 1;\n" +
                "\t\tfont-size: 12px;\n" +
                "\t\tcolor: rgba(255,255,255,.6);\n" +
                "\t\tright: 40px;\n" +
                "\t\ttext-shadow: 0px 1px 2px rgba(0,0,0,.3);\n" +
                "\t}\n" +
                "</style>";
    }

    private String insertHeaderImage(NewsDetail newsDetail) {
        String image = "<div class=\"headline\">\n" +
                "\n" +
                "<div class=\"img-wrap\">\n" +
                "<h1 class=\"headline-title\">" + newsDetail.title + "</h1>\n" +
                "\n" +
                "\n" +
                "<span class=\"img-source\">图片：Lily / CC BY</span>\n" +
                "\n" +
                "\n" +
                "<img src=\"" + newsDetail.image + "\" alt=\"\">\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</div>";
        Optional<String> body = fromNullable(newsDetail.body);
        return body.isPresent() ? body.get().replaceFirst("<div class=\"img-place-holder\"></div>", image) : "";
    }

    private String buildCssAndJs(NewsDetail newsDetail) {
        StringBuilder sb = new StringBuilder();
        for (String css : newsDetail.css) {
            sb.append("<link rel=\"stylesheet\" href=\"").append(css).append("\" />");
        }

        for (String js : newsDetail.js) {
            sb.append("<script src=\"").append(js).append("\" />");
        }
        return sb.toString();
    }

    @Override
    public void onFirstLoad(NewsDetail newsDetail) {
        show(newsDetail);
    }
}
