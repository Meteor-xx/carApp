package yunkeiot.com.carapp.ui.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.common.CAShareData;
import yunkeiot.com.carapp.common.CATools;
import yunkeiot.com.carapp.common.Logger;
import yunkeiot.com.carapp.entity.CAMyCarsEntity;
import yunkeiot.com.carapp.ui.adapter.CACarsAdapter;
import yunkeiot.com.carapp.ui.adapter.CAFragmentAdapter;
import yunkeiot.com.carapp.ui.base.CABaseActivity;
import yunkeiot.com.carapp.ui.main.fragment.CACarDetailFragment;

public class CAMyCarsActivity extends CABaseActivity implements AdapterView.OnItemClickListener{
    private ViewPager mContentViewPager;
    private View emptyFrame, groupFrame;
    private List<CAMyCarsEntity.CarBean> carsList;
    private ListView listView;
    private CACarsAdapter carsAdapter;
    private DrawerLayout drawerlayout;
    private ImageButton toolbar;
    private int defaultPage = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycars_drawer_layout);
    }

    @Override
    public void setUpViews() {
        setNavTitle(getString(R.string.nav_my_cars));
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        emptyFrame = findViewById(R.id.empty_frame);
        groupFrame = findViewById(R.id.group_frame);
        toolbar = (ImageButton) findViewById(R.id.toolbar);
        mContentViewPager = (ViewPager) findViewById(R.id.contentViewPager);
        listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(carsAdapter = new CACarsAdapter(this));
        listView.setOnItemClickListener(this);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public void setUpData() {
        carsList = CAShareData.getUserAllVehicleinfolist();
        if (carsList!=null){
            carsAdapter.setData(carsList);
        }
        initPager();
    }
    List<Fragment> fragmentList = new ArrayList<>();
    private void initPager(){
        if (carsList != null){
            List<String> titleList =new ArrayList<>();
            String defaultCar = CAShareData.getDefaultCar();
            Logger.i("defaultCar",defaultCar);
            fragmentList.clear();
            for (int i = 0;i < carsList.size(); i++){
                if (!CATools.isEmpty(defaultCar)&&carsList.get(i).getPlateNumber().equals(defaultCar))
                    defaultPage = i;
                titleList.add(carsList.get(i).getPlateNumber());
                CACarDetailFragment fragment = new CACarDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", carsList.get(i));
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }
            CAFragmentAdapter fragmentAdapter =new CAFragmentAdapter(getSupportFragmentManager(),fragmentList);
            fragmentAdapter.setTitleList(titleList);
            mContentViewPager.setAdapter(fragmentAdapter);
            mContentViewPager.setCurrentItem(defaultPage, true);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        drawerlayout.closeDrawer(Gravity.LEFT);
        mContentViewPager.setCurrentItem(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
