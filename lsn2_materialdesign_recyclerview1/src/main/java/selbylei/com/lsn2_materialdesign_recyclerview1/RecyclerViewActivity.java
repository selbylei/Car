package selbylei.com.lsn2_materialdesign_recyclerview1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {

    private static List<String> list = new ArrayList<String>() {{
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
        add("abc");
        add("123");
        add("edfo0");
    }};

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private StaggeredAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        adapter = new StaggeredAdapter(this, list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //默认水平的
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

    }
}
