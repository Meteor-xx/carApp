package yunkeiot.com.carapp.common;

import android.util.Log;

public class Logger {
    public static void log(String tag, String msg) {
        Log.i(tag, "CApp -msg-->" + msg);
    }

    public static void i(String tag,String msg){
        Log.i(tag, "msg-->" + msg);
    }
}
