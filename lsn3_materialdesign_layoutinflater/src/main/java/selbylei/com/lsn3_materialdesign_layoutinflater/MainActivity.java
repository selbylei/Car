package selbylei.com.lsn3_materialdesign_layoutinflater;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private boolean isGrid = false;
    private RecyclerView recyclerView;
    private Context mContext;
    private List<String> list = new ArrayList<String>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        setListener();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("item" + i);
        }
    }


    private void initView() {
        mContext=this;
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(list);

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(mContext, postion+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGrid) {
                    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    isGrid = true;
                } else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    isGrid = false;
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(0);
            }
        });
    }


    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<String> mList;

        private OnItemClickListener mOnClickListener;

        MyAdapter(List list) {
            this.mList = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder,int position) {
            holder.textView.setText(mList.get(position));
            holder.itemView.setOnClickListener(new MyClickListener(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

        void addItem(int position) {
            mList.add(position, "add item" + position);
            notifyDataSetChanged();
        }

        //删除
        void removeData(int position) {
            mList.remove(position);
            notifyItemRemoved(position);  //移除通知
        }

        interface OnItemClickListener {
            void onItemClick(View view, int position);
        }


        void setOnItemClickListener(OnItemClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }


        class MyClickListener implements View.OnClickListener {

            private int pos;

            public MyClickListener(int pos) {
                this.pos = pos;
            }

            @Override
            public void onClick(View v) {
                if(mOnClickListener!=null){
                    mOnClickListener.onItemClick(v, pos);
                }
            }
        }
    }

}

