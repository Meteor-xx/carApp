package yunkeiot.com.carapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import yunkeiot.com.carapp.R;

public class CAMainFunctionGridViewAdapter extends BaseAdapter {
    private Context context;
    private int icons[] = {R.mipmap.main_rating, R.mipmap.main_trajectory, R.mipmap.main_driver, R.mipmap.main_warning, R.mipmap.main_peccancy, R.mipmap.main_report};
    private int titles[] = {R.string.main_nav_rating, R.string.main_nav_trajectory, R.string.main_nav_driver, R.string.main_nav_warning, R.string.main_nav_peccancy, R.string.main_nav_report};

    public CAMainFunctionGridViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.main_function_item_layout,viewGroup,false);
        ImageView icon = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);
        icon.setImageResource(icons[i]);
        title.setText(titles[i]);
        return view;
    }
}
