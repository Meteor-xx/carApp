package yunkeiot.com.carapp.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.ui.adapter.CAFragmentAdapter;
import yunkeiot.com.carapp.ui.base.CABaseFragment;


public class CAHomeFragment extends CABaseFragment {
    private String TAG_HOME_MAIN = "TAG_HOME_MAIN",TAG_HOME_CHOOSE_CAR = "TAG_HOME_CHOOSE_CAR";
    private ImageView weatherIcon;
    private TextView weatherTemp;
    private ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();

    private CAHomeMainFragment homeMainFragment;


    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     * 注意：
     *    此处重写的是Fragment父类onCreatView方法，参数为三个
     *    不是重写CABaseFragment内onCreatView
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_home_layout_new);
    }

    @Override
    public void setUpViews(){
        super.setUpViews();
        weatherIcon = (ImageView) findViewById(R.id.weatheIcon);
        weatherTemp = (TextView) findViewById(R.id.weatherTemp);
        initTabView();
    }
    private void initTabView(){
        ((RadioGroup)findViewById(R.id.home_tab_view)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.page1){
                    setPageIndex(0);
                }else {
                    setPageIndex(1);
                }
            }
        });
        viewPager = (ViewPager) findViewById(R.id.home_container);
        addFragments();
    }
    @Override
    public void setUpData() {
        super.setUpData();

    }


    @Override
    public void refreshData() {
        super.refreshData();
        if(homeMainFragment != null){
            homeMainFragment.refreshData();
        }
    }
    private void setPageIndex(int index){
        if (index == 0){
            findViewById(R.id.page1_line).setVisibility(View.VISIBLE);
            findViewById(R.id.page2_line).setVisibility(View.INVISIBLE);
            ((RadioButton)findViewById(R.id.page1)).setChecked(true);
        }else if(index == 1){
            findViewById(R.id.page1_line).setVisibility(View.INVISIBLE);
            findViewById(R.id.page2_line).setVisibility(View.VISIBLE);
            ((RadioButton)findViewById(R.id.page2)).setChecked(true);
        }
        viewPager.setCurrentItem(index,true);
    }

    private void addFragments(){
        addFragment(R.id.home_container,new CAHomeMainFragment(),TAG_HOME_MAIN);
        commit();
        setCurrentFragmentByTag(TAG_HOME_MAIN);
        fragmentList.add(homeMainFragment= new CAHomeMainFragment());
        viewPager.setAdapter(new CAFragmentAdapter(getChildFragmentManager(),fragmentList));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                setPageIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
