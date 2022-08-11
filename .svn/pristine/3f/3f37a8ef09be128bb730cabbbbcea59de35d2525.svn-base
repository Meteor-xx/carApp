package yunkeiot.com.carapp.common;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.application.CAApplication;

public class CAToast {
    // Toast对象
    private static Toast toast = null;

    // 构造方法私有化 不允许new对象
    private CAToast() {
    }

    public static void showText(String text) {
        showText(CAApplication.getApplication(), text);
    }

    public static void showText(@StringRes int textRes) {
        showText(CAApplication.getApplication(), CAApplication.getApplication().getResources().getString(textRes));
    }

    /**
     * 显示Toast
     */
    public static void showText(Context context, String text) {
        if (context != null && !CATools.isEmpty(text)) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.eb_static_toast,
                    null);
            TextView tv_custom_toast = (TextView) layout.findViewById(R.id.tv_custom_toast);
            text = text.replace("\\n", "\n");
            tv_custom_toast.setText(text);

//            tv_custom_toast.setText(Html.fromHtml(text));
            if (toast == null) {
                toast = new Toast(context);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setView(layout);
            toast.show();
        }
    }

    public static void showTextLong(String text) {
        showTextLong(CAApplication.getApplication(), text);
    }

    public static void showTextLong(Context context, String text) {
        if (context != null && !CATools.isEmpty(text)) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.eb_static_toast,
                    null);
            TextView tv_custom_toast = (TextView) layout.findViewById(R.id.tv_custom_toast);
            tv_custom_toast.setText(text);
            if (toast == null) {
                toast = new Toast(context);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
            }
            toast.setView(layout);
            toast.show();
        }
    }
}
