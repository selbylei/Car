package selbylei.com.lsn4__materialdesign_recyclerview3;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private MyAdapter adapter;
    private List<String> mList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManger;
    private boolean isAddDivider = false;
    private boolean isGrid = false;
    private int mOrientation = RecyclerView.HORIZONTAL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_add:
                        adapter.addItem(mList.size());
                        break;

                    case R.id.menu_delete:
                        adapter.removeData(mList.size() - 1);
                        break;

                    case R.id.menu_line:
                        if (!isAddDivider) {
//                            DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);//系统自带的DividerItemDecoration
//                            MyItemDecoration decoration = new MyItemDecoration(mContext, mOrientation); //LinearLayoutManager的分割线
//                            DividerGridViewItemDecoration decoration = new DividerGridViewItemDecoration(mContext,mOrientation);//gridView的分割线
                            SuperDividerItemDecoration decoration = new SuperDividerItemDecoration(mContext, mOrientation);//gridView的分割线
                            recyclerView.addItemDecoration(decoration);
                            isAddDivider = true;
                        }
                        break;

                    case R.id.menu_theme:
                        if (isGrid) {
                            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4, mOrientation, false);
                            recyclerView.setLayoutManager(layoutManager);
                            isGrid = false;
                        } else {
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, mOrientation, false);
                            recyclerView.setLayoutManager(layoutManager);
                            isGrid = true;
                        }
                        break;
                }
                return false;
            }
        });
    }


    private void initView() {
        mContext = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.lsn4);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManger = new GridLayoutManager(mContext, 4, mOrientation, false);
        recyclerView.setLayoutManager(layoutManger);
        adapter = new MyAdapter(mList);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            mList.add("item" + i);
        }
    }

    private Context mContext;


}



