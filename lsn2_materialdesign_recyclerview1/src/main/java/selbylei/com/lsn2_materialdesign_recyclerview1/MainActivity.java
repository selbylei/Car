package selbylei.com.lsn2_materialdesign_recyclerview1;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn4)
    Button btn4;
    private ListPopupWindow lpw;
    ArrayList<String> list = new ArrayList<String>() {{
        add("A");
        add("B");
        add("C");
    }};

    ArrayList<String> list2 = new ArrayList<String>() {{
        add("A");
        add("B");
        add("C");
        add("C");
        add("C");
        add("C");
        add("C");
        add("C");
        add("C");
    }};

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn2)
    AppCompatButton btn2;
    @BindView(R.id.spinner)
    AppCompatSpinner spinner;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.btn3)
    AppCompatButton btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //popuWindow
        lpw = new ListPopupWindow(this);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
        lpw.setAdapter(adapter2);
        lpw.setWidth(ListPopupWindow.MATCH_PARENT);
        lpw.setHeight(ListPopupWindow.WRAP_CONTENT);
        lpw.setAnchorView(btn2); //设置锚点
//        lpw.setPromptPosition(ListPopupWindow.POSITION_PROMPT_ABOVE);
        lpw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn2.setText(list2.get(position));
                lpw.dismiss();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }
        });
        //ProgressBar
        pb.setMax(100);
        pb.setProgress(50);

        //下拉刷新

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(), "刷新了", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });
        refreshLayout.setColorSchemeColors(Color.YELLOW, Color.GRAY, Color.RED, Color.GREEN, Color.WHITE, Color.DKGRAY);
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        refreshLayout.setDistanceToTriggerSync(100);

        //popuMenu
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != lpw && lpw.isShowing()) {
                    lpw.dismiss();
                } else {
                    lpw.show();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMene(v);
            }
        });

    }

    private void showPopupMene(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
        popupMenu.show();
    }


    private void showDialog(View v) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("女朋友");
        build.setMessage("给我一个女朋友");
        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        build.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        build.show();
    }


    @OnClick(R.id.btn4)
    public void onClick() {
        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
        startActivity(intent);
    }
}
