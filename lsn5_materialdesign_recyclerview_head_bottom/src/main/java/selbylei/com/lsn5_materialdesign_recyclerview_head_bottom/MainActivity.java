package selbylei.com.lsn5_materialdesign_recyclerview_head_bottom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import selbylei.com.lsn5_materialdesign_recyclerview_head_bottom.adapter.BaseRecyclerAdapter;


public class MainActivity extends AppCompatActivity {

    private WrapRecyclerView recyclerView;
    private BaseRecyclerAdapter adapter;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (WrapRecyclerView) findViewById(R.id.recyclerView);

        TextView headerView = new TextView(this);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        headerView.setLayoutParams(params);
        headerView.setText("我是头部");
        recyclerView.addHeaderView(headerView);


        TextView footerView = new TextView(this);
        RecyclerView.LayoutParams fls = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(fls);
        footerView.setText("我是底部");
        recyclerView.addFooterView(footerView);

        for (int i = 0; i < 50; i++) {
            mList.add("item" + i);
        }

        adapter = new BaseRecyclerAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}
