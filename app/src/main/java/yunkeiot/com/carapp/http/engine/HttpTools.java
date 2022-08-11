package yunkeiot.com.carapp.http.engine;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HttpTools {
    private static final String SIGN_KEY = "d883fd21fb995169";
    /**
     * Description:判断空
     *
     * @param str
     * @return
     * @author 李满 2014-10-23
     */
    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str) || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Description:检查网络是否可用
     *
     * @param context
     * @return
     * @author 李满 2014-6-16
     */
    public static boolean checkNetworkEnable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cwjManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cwjManager.getActiveNetworkInfo();
        if (network == null)
            return false;
        return network.isAvailable();
    }


//    public static String getSign(Map paraMap) {
//        String sign = null;
//        try {
//            sign = SignatureUtil.createSignBase64(paraMap,SIGN_KEY);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return sign;
//    }

    @SuppressWarnings("unchecked")
    public static String formatBizQueryParaMap(Map paraMap) {
        List<String> sorted = new ArrayList<>();
        Collection<String> co = paraMap.values();
        for (Iterator<String> iterator = co.iterator(); iterator.hasNext(); ) {
            String string = (String) iterator.next();
            sorted.add(string);
        }
//        Logger.d("排序后的列表：", sorted + "");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < sorted.size(); i++) {
            buffer.append(sorted.get(i));
        }
        String result = "";
        if (buffer.length() > 0) {
            result = buffer.substring(0, buffer.length());
        }
        return result;
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    private static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);
//        Logger.d("排序后的列表：", sortMap + "");

        return sortMap;
    }

    static class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {

            return str1.compareTo(str2);
        }

    }

    /**
     * MD5加密
     *
     * @param string
     * @return
     */
    public static String stringToMD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
