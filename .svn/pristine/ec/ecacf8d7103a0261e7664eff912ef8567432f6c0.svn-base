package yunkeiot.com.carapp.ui.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CAFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private FragmentManager fm;
    public CAFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fm = fm;
        this.fragmentList = fragmentList;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList != null && titleList.size() > position) {
            return titleList.get(position);
        }
        return super.getPageTitle(position);
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public void updatePager(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        return fragmentList ==null?0:fragmentList.size();
    }
}
