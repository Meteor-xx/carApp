package yunkeiot.com.carapp.http.engine;

public class HttpConfig {
    public static final boolean IS_PUBLISH = false;  //是否为发布版本
    /**
     * 当前环境
     */
//    public static HttpsEnv HTTPS_CURRENT_ENVIRONMENT = HttpsEnv.HTTPS_DEV;
//    public static HttpsEnv HTTPS_CURRENT_ENVIRONMENT = HttpsEnv.HTTPS_TEST;
    public static HttpsEnv HTTPS_CURRENT_ENVIRONMENT = HttpsEnv.HTTPS_PUBLISH;

    private static String BASE_IP = "https://xiaochengxu.yunkeiot.cn/";

    public static boolean IS_TEST_VERSION = true;

    //外网 https://xiaochengxu.yunkeiot.cn/car_api

    static {
        if(IS_TEST_VERSION){
            BASE_IP = "https://xiaochengxu.yunkeiot.cn/";
        }else{
            BASE_IP = "https://xiaochengxu.yunkeiot.cn/";
        }
    }

    /**
     * 开发环境
     */
    private static String HTTPS_URL_DEV = BASE_IP + "car_api/";

    /**
     * 测试环境
     */
    private static String HTTPS_URL_TEST = BASE_IP + "car_api/";

    /**
     * 发布环境
     */
    private static String HTTPS_URL_PUBLISH = BASE_IP + "car_api/";


    /**
     * 环境配置
     */
    public static String HTTPS_GET_SERVICE_URL = HTTPS_URL_DEV;


    /**
     * 当前网络请求地址
     */
    public static String HTTPS_URL = HTTPS_URL_DEV;

    static {
        switch (HTTPS_CURRENT_ENVIRONMENT) {
            case HTTPS_DEV:
                HTTPS_GET_SERVICE_URL = HTTPS_URL_DEV;
                HTTPS_URL = HTTPS_URL_DEV;

                break;
            case HTTPS_TEST:
                HTTPS_GET_SERVICE_URL = HTTPS_URL_TEST;
                HTTPS_URL = HTTPS_URL_TEST;


                break;
            case HTTPS_PUBLISH:
                HTTPS_GET_SERVICE_URL = HTTPS_URL_PUBLISH;
                HTTPS_URL = HTTPS_URL_PUBLISH;

                break;
        }
    }


    public static void setCurrentEnvironment(HttpsEnv httpsEnv) {
        switch (httpsEnv) {
            case HTTPS_DEV:
                HTTPS_GET_SERVICE_URL = HTTPS_URL_DEV;
                HTTPS_URL = HTTPS_URL_DEV;

                break;
            case HTTPS_TEST:
                HTTPS_GET_SERVICE_URL = HTTPS_URL_TEST;
                HTTPS_URL = HTTPS_URL_TEST;

                break;
            case HTTPS_PUBLISH:
                HTTPS_GET_SERVICE_URL = HTTPS_URL_PUBLISH;
                HTTPS_URL = HTTPS_URL_PUBLISH;

                break;
        }
        HTTPS_CURRENT_ENVIRONMENT = httpsEnv;
    }


    public static final String ENCRYPT = "l]Ybbrj_ZnFz4~9VB7)Gq7[0HGCY]oq{1*I]Eji_e[(~qVRYB#axu+r6IDzeE0*EvX2&BCWt~QmXZc==";
}
