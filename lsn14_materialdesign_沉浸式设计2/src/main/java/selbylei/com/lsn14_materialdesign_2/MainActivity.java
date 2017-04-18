package selbylei.com.lsn14_materialdesign_2;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasNavigationBarShow(getWindowManager());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("沉浸式设计");
        View navigationBar = findViewById(R.id.navigationBar);
        setOrChangeTranslucentColor(toolbar, navigationBar, R.color.colorPrimary);

    }


}
