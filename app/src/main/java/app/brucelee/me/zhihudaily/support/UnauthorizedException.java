package app.brucelee.me.zhihudaily.support;

import retrofit.RetrofitError;

/**
 * Created by bruce on 7/22/14.
 */
public class UnauthorizedException extends Exception {
    RetrofitError error;
    public UnauthorizedException(RetrofitError error) {
        super();
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(error.getMessage());
        sb.append("\n");
        sb.append(error.getUrl());
        sb.append("\n");
        sb.append("isNetoworkError");
        sb.append(error.isNetworkError());
        return sb.toString();
    }
}
