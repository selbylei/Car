package selbylei.com.lsn12_materialdesign_tablelayout;


import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentPagerAdapter mAdapter;
    private FragmentManager mFragmentManager;

    private String[] titles = {
            "条目1",
            "条目2",
            "条目3",
            "条目4",
            "条目5",
            "条目6",
            "条目7",
            "条目8",
            "条目9",
            "条目10",
            "条目11",
            "条目12",
            "条目13",
            "条目14",
            "条目15",
            "条目16",
            "条目17",
            "条目18"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new MyViewPagerAdapter(mFragmentManager, titles);
        viewPager.setAdapter(mAdapter);


        //方法一
        //tabLayout选中时关联viewpager
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            //选中的时候回调
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition(),true);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){
//
//        });
//        tabLayout.setTabsFromPagerAdapter(mAdapter);

        //方法二
        tabLayout.setupWithViewPager(viewPager,false);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab!=null){
                View view = View.inflate(this,R.layout.layout_tab,null);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab);
                imageView.setImageResource(R.mipmap.ic_launcher_round);
                TextView textView = (TextView) view.findViewById(R.id.tv_tab);
                textView.setText("条目"+i);
                tab.setCustomView(view);
            }
        }

        tabLayout.removeTabAt(1);
        tabLayout.getSelectedTabPosition();


    }


}
