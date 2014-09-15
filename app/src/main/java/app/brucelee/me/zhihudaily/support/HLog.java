package app.brucelee.me.zhihudaily.support;

/**
 * Created by bruce on 9/15/14.
 */
import android.util.Log;

/**
 * HLog
 * romainpiel
 * 13/09/2014
 */
public class HLog {

    public static int v(String tag, String msg) {
        return Log.v(tag, getHMsg(msg));
    }

    public static int v(String tag, String msg, Throwable tr) {
        return Log.v(tag, getHMsg(msg), tr);
    }

    public static int d(String tag, String msg) {
        return Log.d(tag, getHMsg(msg));
    }

    public static int d(String tag, String msg, Throwable tr) {
        return Log.d(tag, getHMsg(msg), tr);
    }

    public static int i(String tag, String msg) {
        return Log.i(tag, getHMsg(msg));
    }

    public static int i(String tag, String msg, Throwable tr) {
        return Log.i(tag, getHMsg(msg), tr);
    }

    public static int w(String tag, String msg) {
        return Log.w(tag, getHMsg(msg));
    }

    public static int w(String tag, String msg, Throwable tr) {
        return Log.w(tag, getHMsg(msg), tr);
    }

    public static int e(String tag, String msg) {
        return Log.e(tag, getHMsg(msg));
    }

    public static int e(String tag, String msg, Throwable tr) {
        return Log.e(tag, getHMsg(msg), tr);
    }

    public static int wtf(String tag, String msg) {
        return Log.wtf(tag, getHMsg(msg));
    }

    public static int wtf(String tag, String msg, Throwable tr) {
        return Log.wtf(tag, getHMsg(msg), tr);
    }

    public static int println(int priority, String tag, String msg) {
        return Log.println(priority, tag, getHMsg(msg));
    }

    private static String getHMsg(String msg) {

        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();

        Integer methodIndex = null;
        boolean hlogFound = false;
        boolean stop = false;
        for (int i = 0; i < stackTraceElement.length && !stop; i++) {
            if (stackTraceElement[i].getClassName().contains(HLog.class.getSimpleName())) {
                hlogFound = true;
            } else if (hlogFound) {
                methodIndex = i;
                stop = true;
            }
        }

        if (methodIndex != null) {
            String fullClassName = stackTraceElement[methodIndex].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = stackTraceElement[methodIndex].getMethodName();
            String lineNumber = String.valueOf(stackTraceElement[methodIndex].getLineNumber());

            return String.format("%s .%s(%s.java:%s)", msg, methodName, className, lineNumber);
        } else {
            return msg;
        }
    }
}