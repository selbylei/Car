package selbylei.com.lsn6_recyclerview_animator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DragListener {

    private RecyclerView recyclerView;
    private QQAdapter adapter;
    private List<QQMessage> mList = new ArrayList<>();
    private ItemTouchHelper itemTouchHelper;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = DataUtil.initData();
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QQAdapter(this, mList);
        recyclerView.setAdapter(adapter);

        //条目触摸帮助类
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(mContext,adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        //绑定到recyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        itemTouchHelper.startDrag(holder);
    }
}
