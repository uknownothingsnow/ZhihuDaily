package app.brucelee.me.zhihudaily.shadow;

import android.content.Context;
import android.util.AttributeSet;

import com.viewpagerindicator.CirclePageIndicator;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

/**
 * Created by bruce on 7/15/14.
 */
@Implements(CirclePageIndicatorShadow.class)
public class CirclePageIndicatorShadow {
    @RealObject private CirclePageIndicator realObject;

    @Implementation
    public void constructor(Context context, AttributeSet attrs) {
        realObject = new CirclePageIndicator(context, attrs);
    }
}
