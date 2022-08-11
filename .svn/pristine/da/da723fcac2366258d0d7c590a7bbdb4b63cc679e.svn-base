package yunkeiot.com.carapp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.ycbjie.ycstatusbarlib.bar.YCAppBar;
import yunkeiot.com.carapp.callback.CADialogCloseCallback;
import yunkeiot.com.carapp.common.Logger;
import yunkeiot.com.carapp.entity.Data.BaseEntity;
import yunkeiot.com.carapp.http.engine.HttpRequestEngine;
import yunkeiot.com.carapp.http.engine.RequestCallback;
import yunkeiot.com.carapp.ui.main.CAMainActivity;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.application.CAApplication;
import yunkeiot.com.carapp.common.CAToast;
import yunkeiot.com.carapp.common.CATools;

import static yunkeiot.com.carapp.http.engine.ErrorCode.ERROR_CODE_TOKEN_ERROR1;
import static yunkeiot.com.carapp.http.engine.ErrorCode.ERROR_CODE_TOKEN_ERROR2;


/**
 * created by meteor on 2019/11/23
 * Description
 */
public abstract class CABaseActivity extends AppCompatActivity {
    public static int activityCount = 0;
    public static final int PAGE_REFRESH = 1, PAGE_LOADMORE = 2;
    private final boolean autoHideKeyboard = true;

    public abstract void setUpViews();
    public abstract void setUpData();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCount += 1;
        CAApplication.getApplication().add(this);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//        getWindow().setNavigationBarColor(Color.WHITE);
        registerEventBus();
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBarTranslucent();
        initFragment();
        setUpViews();
        setUpData();
    }
    private void setStatusBarTranslucent() {
        QMUIStatusBarHelper.translucent(this, getResources().getColor(R.color.colorPrimary));
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
        //设置颜色
        YCAppBar.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
        setFitsSystemWindows(true);
    }
    public void setFitsSystemWindows(boolean fitsSystemWindows) {
        ViewGroup viewGroup = ((ViewGroup) findViewById(android.R.id.content));
        if (viewGroup != null && viewGroup.getChildCount() > 0) {
            View view = viewGroup.getChildAt(0);
            view.setBackgroundColor(getResources().getColor(R.color.color_24272c));
            view.setFitsSystemWindows(fitsSystemWindows);
        }
    }

    /**
     * fragment 相关
     */
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Map<String, Fragment> fragmentMap = new LinkedHashMap<>();
    private String preFragmentTag, currentTag;

    private void initFragment() {
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
    }
    public void addFragment(int containerId, Fragment fragment, String tag) {
        fragmentTransaction.add(containerId, fragment, tag);
        fragmentMap.put(tag, fragment);
        fragmentTransaction.hide(fragment);
    }

    public void setCurrentFragmentByTag(String tag) {
        if (tag != null) {
            preFragmentTag = currentTag;
            currentTag = tag;
            fragmentTransaction = fragmentManager.beginTransaction();
            Iterator<String> keys = fragmentMap.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                if (!key.equals(tag)) {
                    fragmentTransaction.hide(fragmentMap.get(key));
                }
            }
            fragmentTransaction.show(fragmentMap.get(tag)).commitAllowingStateLoss();
        }
    }

    public void commit() {
        this.fragmentTransaction.commit();
    }

    /**
     * 刷新数据
     */
    public void refreshData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

      //  MobclickAgent.onResume(this); umeng
    }

    @Override
    protected void onPause() {
        super.onPause();
      //  MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        CAApplication.getApplication().remove(this);
        super.onDestroy();
        unRegisterEventBus();
        activityCount -= 1;
    }
    /**
     * 启动某一个界面
     *
     * @param activity
     */
    public void startActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void toMainActivity() {
        startActivity(createMainIntent());
    }

    public void toMainActivity(Intent intent) {
        startActivity(intent);
    }

    public Intent createMainIntent() {
        Intent intent = new Intent(this, CAMainActivity.class);
        return intent;
    }

    private void registerEventBus() {
        EventBus.getDefault().register(this);
    }
    /**
     * 退出到登录页面
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void threadEvent(Object messageEvent) {
        Activity activity = CAApplication.getApplication().getTopActivity();
        if (this == activity) {
//            CAShareData.saveUserLoginData(null);
            showLogoutDialog();
        } else {
            finish();
        }
    }
    private boolean isShowingDialog = false;
    private void showLogoutDialog() {
//        if (!isShowingDialog) {
//            isShowingDialog = true;
//            showCommonDialog("你的账号已经在其它设备登录，如非本人操作，请留意账户安全。", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    logout();
//                    startActivity(EBLoginActivity.class);
//                    isShowingDialog = false;
//                    finish();
//                }
//            });
//        }
    }
    public void showToast(String msg) {
        if (!isFinishing() && !CATools.isEmpty(msg)) {
            CAToast.showText(this, msg);
        }
    }
    public void showToast(int resId) {
        if (!isFinishing()) {
            showToast(getString(resId));
        }
    }
    private void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setNavTitle(String title) {
//        TextView tvTitle = (TextView) findViewById(R.id.nav_title);
//        TextView ivImage = (TextView) findViewById(R.id.nav_back);
//        if (tvTitle != null) {
//            tvTitle.setText(title);
//        }
//        if (ivImage != null) {
//            ivImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (onNavBackClicked()) {
//                        finish();
//                    }
//                }
//            });
//        }
        setNavBackTitle(title);
    }

    public void setNavBackTitle(String title) {
        TextView ivImage = (TextView) findViewById(R.id.nav_back);
//        setNavTitle("");
        if (ivImage != null) {
            ivImage.setText(title);
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNavBackClicked()) {
                        finish();
                    }
                }
            });
        }
    }

    public void setNavBackTitle(int title) {
        if (title > 0) {
            setNavBackTitle(getString(title));
        }
    }

    public void setNavTitle(int resId) {
        if (resId > 0) {
            setNavTitle(getString(resId));
        }
    }

    public void setNavTitleBackGroundColor(int color) {
        View view = findViewById(R.id.common_nav_header);
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }


    public void hideNavBottomLine(boolean hide) {
        View view = findViewById(R.id.nav_line);
        if (view != null) {
            if (hide) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public void hideNavHeaderBack(){
        View view = findViewById(R.id.nav_back);
        if ( view!= null){
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 检查权限
     * @param permission
     * @param requestCode
     * @return
     */
    public boolean checkAppPermission(String permission, int requestCode) {
        return CATools.checkPermissionAbove23(this, permission, requestCode);
    }

    /**
     * 设置菜单图
     *
     * @param visible
     * @param resId
     */
//    public void setNavMenuVisible(boolean visible,int resId){
//        ImageView ivImage = (ImageView)findViewById(R.id.nav_menu);
//        if (ivImage != null){
//            if (resId > 0){
//                ivImage.setImageResource(resId);
//            }
//            if (visible){
//                ivImage.setVisibility(View.VISIBLE);
//            }else {
//                ivImage.setVisibility(View.GONE);
//            }
//            ivImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onNavMenuClicked(view);
//                }
//            });
//        }
//    }
    /**
     * 可以复写此函数来控制返回按键的操作
     *
     * @return
     */
    public boolean onNavBackClicked() {
        return true;
    }

    public void setCommonEmptyViewVisible(boolean visible) {
        if (findViewById(R.id.commonEmptyView) != null) {
            findViewById(R.id.commonEmptyView).setVisibility(!visible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     *
     * 网络相关
     *
     */

    /**
     * post 网络请求
     */
    public void postRequest(Map<String, String> params, final Type type,
                            final RequestCallback callback) {
        HttpRequestEngine.getInstance().postRequest(this, params, type, new RequestCallback() {
            @Override
            public void onRequestSuccess(BaseEntity result, String jsonData) {
                callback.onRequestSuccess(result, jsonData);
                if (result != null) {
                    checkIsError(Integer.parseInt(result.getCode()));
                }
            }
            @Override
            public void onRequestFailed(String msg) {
                callback.onRequestFailed(msg);
            }
        });
    }

    private void checkIsError(int state) {
        if (state == ERROR_CODE_TOKEN_ERROR1 || state == ERROR_CODE_TOKEN_ERROR2) {
            //EventBus.getDefault().post(new Object());
        }
    }

    /**
     * 对话框
     */
    private QMUITipDialog tipDialog;

    public void showLoadingDialog(String message) {
        if (CATools.isEmpty(message)) {
            message = getString(R.string.dialog_waiting);
        }
        if (tipDialog == null && !isDestroyed() && !isFinishing()) {
            tipDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(message)
                    .create();
            tipDialog.show();
        }
    }
    /**
     * 关闭对话框
     */
    public void closeTipDialog() {
        if (tipDialog != null && tipDialog.isShowing()) {
            tipDialog.dismiss();
            tipDialog = null;
        }
    }
    private void closeTipDialogAuto(final CADialogCloseCallback dialogCloseCallback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeTipDialog();
                if (dialogCloseCallback != null) {
                    dialogCloseCallback.onDialogClosed();
                }
            }
        }, 2000);
    }
    public void showSuccessDialog(String message) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(message)
                .create();
        tipDialog.show();
        closeTipDialogAuto(null);
    }
    public void showFailedDialog(String message) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord(message)
                .create();
        tipDialog.show();
        closeTipDialogAuto(null);
    }
    public void showSuccessDialog(String message, CADialogCloseCallback dialogCloseCallback) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(message)
                .create();
        tipDialog.show();
        closeTipDialogAuto(dialogCloseCallback);
    }
    public void showFailedDialog(String message, CADialogCloseCallback dialogCloseCallback) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord(message)
                .create();
        tipDialog.show();
        closeTipDialogAuto(dialogCloseCallback);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev) && autoHideKeyboard && !(this instanceof CAMainActivity)) {
                v.clearFocus();
                hideKeyboard(v.getWindowToken());
                hideKeyBoardEvent();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void hideKeyBoardEvent() {

    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
