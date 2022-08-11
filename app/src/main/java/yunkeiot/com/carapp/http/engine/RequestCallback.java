package yunkeiot.com.carapp.http.engine;

import yunkeiot.com.carapp.entity.Data.BaseEntity;

/**
 * Created by admin on 2016/3/21.
 * Rewrote by Meteor on 2020/1/15
 */
public interface RequestCallback {
    /**
     * @Directions:请求成功
     * @author: liman
     * @date: 2015-8-12
     * @tag:@param result 结果实体
     * @tag:@param jsonData 原始json数据
     */
    public void onRequestSuccess(BaseEntity result, String jsonData);

    /**
     * @Directions:请求错误
     * @author: liman
     * @date: 2015-8-12
     * @tag:@param msg 出错原因
     */
    public void onRequestFailed(String msg);

    abstract class XRequestCallback implements RequestCallback {

        @Override
        @Deprecated
        public void onRequestSuccess(BaseEntity result, String jsonData) {}

        @Override
        @Deprecated
        public void onRequestFailed(String msg) {}

        public abstract void onRequestSuccess(String service, BaseEntity result, String jsonData, Object backParam);

        public abstract void onRequestFailed(String service, String msg, Object backParam);
    }

}
