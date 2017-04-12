package selbylei.com.lsn10_materialdesign_toolbar_searchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class Main2Activity extends AppCompatActivity  {

    private final String TAG = Main2Activity.class.getSimpleName();

    private Toolbar toolbar;
    private MyScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        scrollView = (MyScrollView) findViewById(R.id.scrollView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        scrollView.setScrollListener(new MyScrollView.onScrollListener() {
            @Override
            public void scrolling(float alpha) {
                Log.i(TAG, "透明度: "+(1-alpha));
                toolbar.setAlpha(1-alpha);
            }
        });
    }


}
