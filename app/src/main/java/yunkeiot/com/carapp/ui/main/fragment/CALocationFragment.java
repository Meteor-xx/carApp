package yunkeiot.com.carapp.ui.main.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.mapOptions.CALocationMgr;
import yunkeiot.com.carapp.mapOptions.CAOnLocationChangedListener;
import yunkeiot.com.carapp.ui.base.CABaseFragment;

public class CALocationFragment extends CABaseFragment implements View.OnClickListener, BaiduMap.OnMarkerClickListener, BaiduMap.OnMapLoadedCallback, BaiduMap.OnMapTouchListener{
    private TextureMapView mapView;
    private BaiduMap baiduMap;
    private CAOnLocationChangedListener caOnLocationChangedListener;
    private boolean isFirstLoc = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //在使用百度地图SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getActivity().getApplicationContext());

        return super.onCreateView(inflater, container, savedInstanceState, R.layout.location_layout);
    }

    @Override
    public void setUpViews() {
        super.setUpViews();
        mapView = (TextureMapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setOnMapLoadedCallback(this);
        baiduMap.setOnMarkerClickListener(this);
        baiduMap.setOnMapTouchListener(this);
        findViewById(R.id.btn_relocation).setOnClickListener(this);
        findViewById(R.id.btn_report).setOnClickListener(this);

        setLocation();
    }

    @Override
    public void setUpData() {
//        super.setUpData();
    }

    @Override
    public void refreshData() {
        super.refreshData();
    }


    /**
     * 获取当前位置
     */
    private void setLocation(){
        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, BitmapDescriptorFactory.fromResource(R.mipmap.map_arrow),
                0xAAFFFF88, 0xAA00FF00));
        mapView.showZoomControls(false);
        mapView.showScaleControl(false);
        mapView.setLogoPosition(LogoPosition.logoPostionleftBottom);
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        CALocationMgr.getInstance(this.getActivity()).setOnLocationChangedListener(caOnLocationChangedListener = new CAOnLocationChangedListener() {
            @Override
            public void onLocationChanged(BDLocation bdLocation) {
                if (bdLocation != null) {
                    // 构造定位数据
                    MyLocationData locData = new MyLocationData.Builder()
                            .accuracy(bdLocation.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                            .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                            .longitude(bdLocation.getLongitude()).build();
                    // 设置定位数据
                    baiduMap.setMyLocationData(locData);
                    if (isFirstLoc) {
                        LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                        MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(ll, 16);//设置地图中心及缩放级别
                        baiduMap.animateMapStatus(update);
                        isFirstLoc = false;
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d("LocationFragment","onResume");
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
        Log.d("LocationFragment","onPause");
    }

    @Override
    public void onDestroy() {
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
        Log.d("LocationFragment","onDestroy");
        super.onDestroy();
    }



    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public void onTouch(MotionEvent motionEvent) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


}
