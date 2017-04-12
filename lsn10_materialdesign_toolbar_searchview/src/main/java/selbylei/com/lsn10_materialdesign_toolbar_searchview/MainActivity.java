package selbylei.com.lsn10_materialdesign_toolbar_searchview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("网易新闻");

        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationIcon(R.drawable.btn_ic_back);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.lsn4, menu);   //添加菜单
        MenuItem menuItem = menu.findItem(R.id.menu_seach);
        final SearchView view = (SearchView) menuItem.getActionView();
        //设置一出来就显示搜索框
        view.setIconified(false);
        //设置一出来就显示搜索框,并且不能被隐藏
//        view.setIconifiedByDefault(false);
        //实现自定义的扩展效果
        /**
         *  searchView使用了布局abc_search_view.xml
         *  设置提交按钮
         */

        view.setSubmitButtonEnabled(true); //设置提交按钮是否可见
        ImageView icon = (ImageView) view.findViewById(R.id.search_go_btn);
         icon.setVisibility(View.VISIBLE);
        //设置宽度
        view.setMaxWidth(1000);
        //关闭搜索输入框点击事件的监听
        view.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(getApplicationContext(), "关闭搜索框", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });

        //提交按钮监听事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "setOnClickListener", Toast.LENGTH_LONG).show();

            }
        });

        //搜索文字写入监听
        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), "提交文本进行搜索", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 10) {
                    Snackbar.make(view, "最多输入10个字", Snackbar.LENGTH_LONG).show();
                }
                return false;
            }
        });

        //打开搜索输入框点击事件的监听
        view.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "打开搜索框", Toast.LENGTH_LONG).show();
            }
        });

        view.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                return false;
            }
        });

        /**
         * 修改点进来的提示
         */
        SearchView.SearchAutoComplete searchAuto = (SearchView.SearchAutoComplete) view.findViewById(R.id.search_src_text);
        searchAuto.setHint("输入您要查询的商品");

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
