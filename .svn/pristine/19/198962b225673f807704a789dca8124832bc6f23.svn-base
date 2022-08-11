package yunkeiot.com.carapp.common;

import android.app.Activity;
import android.content.pm.PackageManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by meteor on 2019/11/23
 */
public class CATools {

    /**
     * 判空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
    /**
     * 权限审请
     *
     * @param context
     * @param permission
     * @param requestCode
     * @return
     */
    public static boolean checkPermissionAbove23(Activity context, String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(context, permission)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(context, new String[]{permission},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 时间戳处理
     */
    public static String getLocalTimeStyleYMD(long timeStamp){
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            String time = sdf.format(new Date(timeStamp));
            Logger.i("时间戳",timeStamp + "  "+ time);
            return time;
        }catch (Exception e){
            return "暂无";
        }
    }

}
