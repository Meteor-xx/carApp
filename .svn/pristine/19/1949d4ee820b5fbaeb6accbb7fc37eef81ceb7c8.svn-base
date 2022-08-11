package yunkeiot.com.carapp.application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * created by meteor on 2019/11/23
 * Description:
 */
public class CAApplication extends Application {
    private static CAApplication instance;
    private List<Activity> activityList = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        this.initBDLocation();  //百度地图初始化

//        UMConfigure.init(this, "5be3d4a3f1f556ea4c0000bb", "app", 0, "");  //Umeng app第三方统计app使用数据

//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);  //Universal-Image-Loader  第三方框架解决网络加载图片
//        ImageLoader.getInstance().init(configuration);
//
//        initBle();
    }
    public static CAApplication getApplication() {
        return instance;
    }
    public void add(Activity activity) {
        activityList.add(activity);
    }
    public void remove(Activity activity) {
        activityList.remove(activity);
    }
    public Activity getTopActivity() {
        return activityList.get(activityList.size() - 1);
    }
    public void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
