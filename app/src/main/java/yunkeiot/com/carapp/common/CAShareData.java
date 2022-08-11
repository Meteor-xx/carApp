package yunkeiot.com.carapp.common;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import yunkeiot.com.carapp.application.CAApplication;
import yunkeiot.com.carapp.entity.CALoginEntity;
import yunkeiot.com.carapp.entity.CAMyCarsEntity;
import yunkeiot.com.carapp.http.engine.MD5Util;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by meteor on 2019/11/23
 * Description:
 */
public class CAShareData {
    private static final String USER_CURRENT_ACCOUNT="USER_CURRENT_ACCOUNT";
    private static final String USER_LOGIN_DATA = "USER_LOGIN_DATA_";
    private static final String DEFAULT_CAR = "DEFAULT_CAR_";
    private static final String USER_LOGIN_ACCOUNT = "USER_LOGIN_ACCOUNT";
    private static final String USER_LOGIN_PWD = "USER_LOGIN_PWD_";
    private static final String USER_ALL_VEHICLEINFOLIST = "USER_ALL_VEHICLEINFOLIST_";

    public static SharedPreferences makeSharedPreferences(String tag) {
        return CAApplication.getApplication().getSharedPreferences(tag, MODE_PRIVATE);
    }

    /**
     * 保存当前登录账户
     * @param _account
     */
    public static void saveCurrentLoginAccount(String _account){
        SharedPreferences sp = makeSharedPreferences(USER_CURRENT_ACCOUNT);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_CURRENT_ACCOUNT,_account);
        editor.commit();
    }
    public static String getUserCurrentAccount(){
        SharedPreferences sp = makeSharedPreferences(USER_CURRENT_ACCOUNT);
        return sp.getString(USER_CURRENT_ACCOUNT,null);
    }

    /**
     * 保存用户登录状态信息
     *
     * @param data
     */
    public static void saveUserLoginData(CALoginEntity.UserLoginData data) {
        SharedPreferences sp = makeSharedPreferences(USER_LOGIN_DATA);
        SharedPreferences.Editor editor = sp.edit();
        if (data == null) {  //未传进数据
            if (isLoginWidthDefault()) {
                editor.putString(USER_LOGIN_DATA, "");
            } else {
            CALoginEntity.UserLoginData userLoginData = getUserLoginData();
            if (userLoginData != null) {  //存储空间有数据
                    CALoginEntity.UserLoginData userLoginDataNew = new CALoginEntity.UserLoginData();
                    userLoginDataNew.setAccessToken(userLoginData.getAccessToken());
                    String json = new Gson().toJson(userLoginDataNew);
                editor.putString(USER_LOGIN_DATA, json);
            } else {
                editor.putString(USER_LOGIN_DATA, "");
            }
            }
        } else {
            String json = new Gson().toJson(data);
            editor.putString(USER_LOGIN_DATA, json);
        }
        editor.commit();
    }
    /**
     * 获取用户登录数据
     *
     * @return
     */
    public static CALoginEntity.UserLoginData getUserLoginData() {
        SharedPreferences sp = makeSharedPreferences(USER_LOGIN_DATA);
        String data = sp.getString(USER_LOGIN_DATA, "");
        if (data == null || "".equals(data)) {
            return null;
        } else {
            return new Gson().fromJson(data, CALoginEntity.UserLoginData.class);
        }
    }
    public static void saveLoginAccount(String _account) {
        SharedPreferences sp = makeSharedPreferences(_account);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_LOGIN_ACCOUNT, _account);
        editor.commit();
    }

    public static String getLoginAccount(String _account) {
        SharedPreferences sp = makeSharedPreferences(_account);
        return sp.getString(USER_LOGIN_ACCOUNT, null);
    }

    public static void saveLoginPwd(String _account,String _pwd) {
        SharedPreferences sp = makeSharedPreferences(_account);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_LOGIN_PWD, MD5Util.encrypt(_pwd));
        editor.commit();
    }

    public static String getLoginPwd(String _account) {
        SharedPreferences sp = makeSharedPreferences(_account);
        return sp.getString(USER_LOGIN_PWD, null);
    }

    /**
     * 保存用户所有车辆数据
     * @param myCarsEntity
     */

    public static void saveUserAllVehicleInfoList(List<CAMyCarsEntity.CarBean> myCarsEntity) {

        SharedPreferences sp = makeSharedPreferences(USER_ALL_VEHICLEINFOLIST+getUserCurrentAccount());
        SharedPreferences.Editor editor = sp.edit();
        if (myCarsEntity == null) {  //未传进数据
            editor.putString(USER_ALL_VEHICLEINFOLIST, "");
        } else {
            String json = new Gson().toJson(myCarsEntity);
            editor.putString(USER_ALL_VEHICLEINFOLIST, json);
        }
        editor.commit();
    }
    public static List<CAMyCarsEntity.CarBean> getUserAllVehicleinfolist(){
        SharedPreferences sp = makeSharedPreferences(USER_ALL_VEHICLEINFOLIST+getUserCurrentAccount());
        String json = sp.getString(USER_ALL_VEHICLEINFOLIST,null);
        if (json != null){
            List<CAMyCarsEntity.CarBean> carBeans = new Gson().fromJson(json,new TypeToken<List<CAMyCarsEntity.CarBean>>(){}.getType());
            return carBeans;
        }
        return null;
    }

    public static void saveDefaultCar(String defaultCar){
        SharedPreferences sp = makeSharedPreferences(getUserCurrentAccount());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(DEFAULT_CAR,defaultCar);
        editor.commit();
    }
    public static String getDefaultCar(){
        SharedPreferences sp = makeSharedPreferences(getUserCurrentAccount());
        String defaultCar = sp.getString(DEFAULT_CAR,"");
        if (defaultCar!=""){
            return defaultCar;
        }else {
            return null;
        }
    }


    /**
     * 是否为游客模式
     *
     * @return
     */
    public static boolean isLoginWidthDefault() {
        CALoginEntity.UserLoginData data = getUserLoginData();
        if (data != null) {
            return true;
        }
        return false;
    }
}
