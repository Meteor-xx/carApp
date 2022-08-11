package yunkeiot.com.carapp.http.engine;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.common.Logger;
import yunkeiot.com.carapp.entity.CAMyCarsEntity;
import yunkeiot.com.carapp.entity.Data.BaseEntity;
import yunkeiot.com.carapp.entity.Data.BaseFaultEntity;

import static yunkeiot.com.carapp.http.engine.ErrorCode.ERROR_CODE_BACKERR;
import static yunkeiot.com.carapp.http.engine.ErrorCode.ERROR_CODE_ERRACC;
import static yunkeiot.com.carapp.http.engine.ErrorCode.ERROR_CODE_SUCCESS;

public class HttpRequestEngine {
    private static HttpRequestEngine instance;
    private static final String Other_ERROR = "";
    /**
     * 本地时间与服务器上时间的差值
     */
    private static long sTimeDiff = 0;
    private static final String NetError = "网络异常,请检查网络";

    public static HttpRequestEngine getInstance() {
        if (instance == null) {
            instance = new HttpRequestEngine();
        }
        return instance;
    }
    public void postRequest(Context context, Map<String, String> params, final Type type,
                            final RequestCallback callback) {
        postRequest(context, params, type, null, callback);
    }
    /**
     * Post请求
     *
     * @param context
     * @param params
     * @param type
     * @param callback
     */
    public void postRequest(Context context, Map<String, String> params, final Type type, Object backParam,
                            final RequestCallback callback) {
        if (context != null) {
            if (context instanceof Activity && ((Activity) context).isFinishing()) {
                return;
            }
            post(context, HttpConfig.HTTPS_URL, params, type, backParam, callback);
        }
    }

    /**
     * 多参数post请求  formbody
     * @param context
     * @param url
     * @param params
     * @param type
     * @param backParam
     * @param callback
     */
    public void post(Context context, String url, Map<String, String> params, final Type type, Object backParam, final RequestCallback callback) {
        String service = params.get("service"); //service开头不能带"/"
        url += service;//添加服务名
        params.remove("service");
        if (HttpTools.checkNetworkEnable(context)) {
            final long startTime = System.currentTimeMillis();
            FormBody.Builder builder = new FormBody.Builder();
            Param[] paramsArr = map2Params(params);
            for(Param param:paramsArr){
                builder.add(param.key,param.value);
            }
            FormBody requestBody = builder.build();
            Request request = new Request.Builder().url(url).post(requestBody).build();
            OkHttpUtil.enqueue(request, new MyCallback(context, startTime, url, type, service, callback).setBackParam(backParam));
        } else {
            failed(service, NetError, backParam, callback);
        }
    }
    /**
     * post请求参数类
     */
    public static class Param {

        String key;//请求的参数

        String value;//参数的值

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private Param[] map2Params(Map<String, String> params) {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    class MyCallback implements Callback {
        private long startTime;
        private Type type;
        private RequestCallback callback;
        private String url;
        private String service;
        private Context mContext;
        private Object backParam;

        public MyCallback(Context context, long startTime, String url, Type type, String service, RequestCallback callback) {
            this.startTime = startTime;
            this.type = type;
            this.callback = callback;
            this.url = url;
            this.service = service;
            this.mContext = context;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            if (!isCanceled()) {
                failed(service, Other_ERROR, backParam, callback);
                printTimeDelay(startTime);
                e.printStackTrace();
                HttpLogger.i(this.url + "    " + this.service + "\n服务器返回失败", "异常日志" + e.toString());
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                calculateTimeDiff(response);
                if (!isCanceled()) {
                    String result = response.body().string();
                    HttpLogger.i(this.service, "请求返回成功:" + result);//tag过长导致不打印log
                    result = dealJson(result);
                    if (!HttpTools.isEmpty(result)) {
                        try {
                            result = checkEmptyObj(new JSONObject(result));
                            if (type != null) {
                                if (checkSucRes(result,type)){
                                    BaseEntity entity = (BaseEntity) new Gson().fromJson(result, type);//向上转型
                                    success(service, entity, result, backParam, callback);
                                }else {
                                    BaseEntity entity = (BaseEntity) new Gson().fromJson(result, BaseFaultEntity.class);//向上转型
                                    success(service, entity, result, backParam, callback);
                                }
                            } else {
                                success(service, null, result, backParam, callback);
                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                            HttpLogger.i("httpRequest", "Json解析出错" + e.toString());
                            failed(service, Other_ERROR, backParam, callback);
                        }
                    } else {
                        failed(service, Other_ERROR, backParam, callback);
                    }
                    printTimeDelay(startTime);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                failed(service, Other_ERROR, backParam, callback);
            }
        }
        private boolean isCanceled() {
            if (mContext != null && mContext instanceof Activity && ((Activity) mContext).isFinishing()) {
                return true;
            }
            return false;
        }
        public MyCallback setBackParam(Object backParam) {
            this.backParam = backParam;
            return this;
        }
    }
    private static Handler handler = new Handler();

    private void success(final BaseEntity entity, final String result, final RequestCallback callback) {
        success("", entity, result, null, callback);
    }

    private void failed(final String result, final RequestCallback callback) {
        failed("", result, null, callback);
    }

    private void success(final String service, final BaseEntity entity, final String result, final Object backParam, final RequestCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onRequestSuccess(entity, result);
                    if (callback instanceof RequestCallback.XRequestCallback) {
                        ((RequestCallback.XRequestCallback) callback).onRequestSuccess(service, entity, result, backParam);
                    }
                }
            }
        });
    }

    private void failed(final String service, final String result, final Object backParam, final RequestCallback callback) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onRequestFailed(result);
                    if (callback instanceof RequestCallback.XRequestCallback) {
                        ((RequestCallback.XRequestCallback) callback).onRequestFailed(service, result, backParam);
                    }
                }
            }
        }, 500);
    }

    private void printTimeDelay(long startTime) {
        HttpLogger.i("服务器响应时长:", String.valueOf(System.currentTimeMillis() - startTime));
    }

    private static void calculateTimeDiff(Response response) {
        try {
            String dateStr = response.header("Date");
            Date date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US).parse(
                    dateStr);
            sTimeDiff = System.currentTimeMillis() - date.getTime();
            HttpLogger.v("[calculateTimeDiff]", String.format("local and server time differ [%d]", sTimeDiff));
        } catch (Exception e) {
            sTimeDiff = 0;
            e.printStackTrace();
        }
    }
    /**
     * 处理一下空数据
     *
     * @param json
     * @return
     */
    private static String dealJson(String json) {
        if (json.startsWith("<") || HttpTools.isEmpty(json)) {
            json = "";
        } else {
            if (json.startsWith("(")) {
                json = json.replaceFirst("\\(", "");
                HttpLogger.i("开头", "开头替换" + json);
            }
            if (json.endsWith(")")) {
                json = json.substring(0, json.length() - 1);
                HttpLogger.i("结尾", "结尾替换" + json);
            }
        }
        return json;
    }

    private static String checkEmptyObj(JSONObject jsonObject) {
        LinkedList<String> map = new LinkedList<String>();
        try {
            Iterator<String> it = jsonObject.keys();
            while (it.hasNext()) {
                String key = it.next();
                if ("[]".equals(jsonObject.getString(key)) || "null".equals(jsonObject.getString(key))) {
                    map.add(key);
                    continue;
                }

                if (key.equals("data") && ("0".equals(jsonObject.getString(key)))) {
                    map.add(key);
                    continue;
                }

                JSONObject obj = jsonObject.optJSONObject(key);
                if (obj != null) {
                    checkEmptyObj(obj);
                } else {
                    JSONArray jsonArray = jsonObject.optJSONArray(key);
                    if (jsonArray != null) {
                        if ("[]".equals(jsonObject.getString(key))) {
//                            jsonObject.remove(key);
                            map.add(key);
                        } else {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Object object = jsonArray.get(i);
                                if (object instanceof JSONObject) {
                                    checkEmptyObj(jsonArray.getJSONObject(i));
                                }
                            }
                        }
                    }
                }
            }
            for (String key : map) {
                jsonObject.remove(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return jsonObject.toString();
    }

    /**
     * 检查后台返回值
     * @param result
     * @return
     */
    private boolean checkSucRes(String result,Type type){
        try {
            JSONObject jsonObject = new JSONObject(result);
            switch (jsonObject.getString("resCode")){
                case ERROR_CODE_BACKERR:
                case ERROR_CODE_SUCCESS:
                    return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
