package yunkeiot.com.carapp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import yunkeiot.com.carapp.http.engine.HttpConfig;


public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    public MyBaseAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    /**
     * load image
     * @param targetImage
     * @param url
     */
    public void loadImage(ImageView targetImage,String url){
        if (url!= null && (!url.startsWith("http://")&&!url.startsWith("https://"))){
            url = HttpConfig.HTTPS_URL + url;
        }

    }
}
