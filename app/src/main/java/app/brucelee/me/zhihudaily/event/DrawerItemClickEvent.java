package app.brucelee.me.zhihudaily.event;

/**
 * Created by bruce on 7/15/14.
 */
public class DrawerItemClickEvent {
    private int position;

    public DrawerItemClickEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
