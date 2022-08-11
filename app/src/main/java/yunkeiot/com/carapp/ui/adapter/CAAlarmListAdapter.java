package yunkeiot.com.carapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.cardview.widget.CardView;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.common.CATools;
import yunkeiot.com.carapp.common.Logger;
import yunkeiot.com.carapp.entity.CAAlarmEntity;

public class CAAlarmListAdapter extends MyBaseAdapter {
    private Context mContext;
    private List<CAAlarmEntity.AlarmItems> dataList;

    public CAAlarmListAdapter(Context context) {
        super(context);
        mContext = context;
    }
    public void setData(List<CAAlarmEntity.AlarmItems> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return this.dataList == null ? 0 : this.dataList.size();
    }

    @Override
    public CAAlarmEntity.AlarmItems getItem(int i) {
        return this.dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.alarm_item_layout,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.alarmCard = (CardView) view.findViewById(R.id.alarm_Card);
            viewHolder.alarmName = (TextView)view.findViewById(R.id.alarm_name);
            viewHolder.alarmAddress = (TextView)view.findViewById(R.id.alarm_address);
            viewHolder.alarmTime = (TextView)view.findViewById(R.id.alarm_time);
            viewHolder.newAlarm = (ImageView) view.findViewById(R.id.new_alarm);

            view.setTag(viewHolder);
        }else {
            viewHolder =(CAAlarmListAdapter.ViewHolder) view.getTag();
        }
        CAAlarmEntity.AlarmItems item = getItem(i);
        viewHolder.newAlarm.setVisibility(View.VISIBLE);
        viewHolder.alarmTime.setText(CATools.getLocalTimeStyleYMD(item.getAlarmTime()));
        viewHolder.alarmAddress.setText(item.getStartGpsLocation().getAddressText()!=null?item.getStartGpsLocation().getAddressText():"");
        viewHolder.alarmName.setText(item.getAlarmName());
        switch (item.getDataType()){
            case 1:
            case 6:
            case 7:
                viewHolder.alarmCard.setCardBackgroundColor(mContext.getResources().getColor(R.color.color_green));
                break;
            case 2:
                viewHolder.alarmCard.setCardBackgroundColor(mContext.getResources().getColor(R.color.color_f7cf0f));
                break;
            case 3:
            case 4:
                viewHolder.alarmCard.setCardBackgroundColor(mContext.getResources().getColor(R.color.color_f61E1E));
                break;
        }
        return view;
    }
    class ViewHolder {
        TextView alarmTime;
        TextView alarmAddress;
        TextView alarmName;
        CardView alarmCard;
        ImageView newAlarm;
    }
}
