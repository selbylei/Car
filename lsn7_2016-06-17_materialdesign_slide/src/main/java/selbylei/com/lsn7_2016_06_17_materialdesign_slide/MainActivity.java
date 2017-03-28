package selbylei.com.lsn7_2016_06_17_materialdesign_slide;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;
    private DrawerLayout drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //设置toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("好人啊");


        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close);
//        恢复成原始状态
        drawerToggle.syncState();
//        设置监听
        drawerlayout.addDrawerListener(drawerToggle);

        //抽屉互动监听
        drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) { //滑动回调
                //sliderOffset 0～1
                View content = drawerlayout.getChildAt(0);
                View menu = drawerView;
                float scale = 1- slideOffset;
                float leftScale = (float) (1 - scale*0.3);
                float rightScale = (float) (0.7 + scale*0.3);
                menu.setScaleX(leftScale); // 1~0.7变化
                menu.setScaleY(leftScale); // 1~0.7变化
//                content.setScaleX(rightScale);
//                content.setScaleY(rightScale);

                content.setTranslationX((menu.getMeasuredWidth()*(1-scale)));

            }

            @Override
            public void onDrawerOpened(View drawerView) { //打开回调
                System.out.println("onDrawerSlide");
            }

            @Override
            public void onDrawerClosed(View drawerView) { //关闭回调
                System.out.println("onDrawerSlide");
            }

            @Override
            public void onDrawerStateChanged(int newState) {//状态变化回调
                System.out.println("onDrawerSlide");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
