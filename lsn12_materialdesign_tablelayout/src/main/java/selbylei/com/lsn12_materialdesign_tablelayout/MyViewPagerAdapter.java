package selbylei.com.lsn12_materialdesign_tablelayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Button;


/**
 * Created by selbylei on 17/4/13.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private String[] mTitles;

    public MyViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.mTitles = titles;
        this.mFragmentManager = fm;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", mTitles[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


}
