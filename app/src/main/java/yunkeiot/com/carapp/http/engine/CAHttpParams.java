package yunkeiot.com.carapp.http.engine;


import com.google.gson.internal.$Gson$Preconditions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.spec.ECField;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import yunkeiot.com.carapp.common.CAShareData;
import yunkeiot.com.carapp.common.CATools;
import yunkeiot.com.carapp.entity.CALoginEntity;

/**
 *  Created by meteor on 2020/1/15
 *  Description:
 */
public class CAHttpParams {

    /**
     * 公共参数
     * @param service 功能请求
     */
    private static Map<String,String> getCommonParams(String service){
        Map<String,String> map = new HashMap<>();
        map.put("version","v1.0");
        map.put("resSrt","");
        map.put("service",service);
        CALoginEntity.UserLoginData loginData = CAShareData.getUserLoginData();
        if (loginData != null && !CATools.isEmpty(loginData.getAccessToken())) {
            map.put("accessToken", loginData.getAccessToken());
        }
        return map;
    }

    /**
     * 密码登录
     */
    private static Map<String,String> getLoginParams(String account,String password){
        Map<String,String> params = new HashMap<>();
        JSONObject data = new JSONObject();
        try {
            data.put("account",account);
            data.put("password",password);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("data",String.valueOf(data));
        return params;
    }

    /**
     * 密码登录
     *
     * @param account
     * @param password
     * @return
     */
    public static Map<String, String> getLoginByPwd(String account, String password) {
        Map<String, String> params = getCommonParams(CAMethods.METHOD_NAME_PWD_LOGIN);
        JSONObject data = new JSONObject();
        try {
            data.put("account",account);
            data.put("password",password);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("data", String.valueOf(data));
        return params;
    }

    /**
     * vehicleInfoList
     */
    public static Map<String,String> getVehicleInfoListOfAll(int _page){
        Map<String,String> params = getCommonParams(CAMethods.METHOD_VEHICLE_VEHICLEINFOLIST);
        JSONObject data = new JSONObject();
        try {
            data.put("plateNumber","");
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("data",String.valueOf(data));
        params.put("page",String.valueOf(_page));
        params.put("pageSize","100");
        return params;
    }

    /**
     * 获取车辆今日行程汇总
     */
    public static Map<String,String> getTravelNowDate(String _platenumber){
        Map<String,String> params = getCommonParams(CAMethods.METHOD_TRAVEL_TRAVELNOWDATE);
        JSONObject data = new JSONObject();
        try {
            data.put("plateNumber",_platenumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("data",String.valueOf(data));
        return params;
    }

    /**
     * 查询车辆行程信息
     */
    public static Map<String ,String > getTravelList(String _platenumber,int _page){
        Map<String,String> params = getCommonParams(CAMethods.METHOD_TRAVEL_TRAVELLIST);

        JSONObject data = new JSONObject();
        try {
            data.put("plateNumber",_platenumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("data",String.valueOf(data));
        params.put("page",String.valueOf(_page));
        params.put("descOrAsc","desc");
        params.put("orderBy","start_time");
        return params;
    }

    /**
     * 查询车辆告警信息
     */
    public static Map<String,String> getAlarmList(String _plateNumber,int _page ){
        Map<String,String> params = getCommonParams(CAMethods.METHOD_ALARM_ALARMLIST);
        JSONObject data = new JSONObject();

        try{
            data.put("plateNumber",_plateNumber);
            data.put("dataType",0);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("data",String.valueOf(data));
        params.put("page",String.valueOf(_page));
        params.put("descOrAsc","desc");
        params.put("orderBy","machine_time");
        return params;
    }
}
