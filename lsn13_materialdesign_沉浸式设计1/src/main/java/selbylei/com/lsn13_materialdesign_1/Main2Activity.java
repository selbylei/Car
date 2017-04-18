package selbylei.com.lsn13_materialdesign_1;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {

    private Toolbar toolbar;

    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("沉浸式方法二，增加状态栏的高度");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //查看源码，需要知道状态栏的高度是多少
            //<dimen name="status_bar_height">24dp</dimen>
            // <dimen name="navigation_bar_height">48dp</dimen>
            //通过反射获取系统的这个值
            //android.R.dimen.status_bar_height反射android.R
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //增加toolbar的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
            params.height += getStateBarHeight(this);
            toolbar.setLayoutParams(params);
            //设置toolbar的padding
            toolbar.setPadding(
                    toolbar.getPaddingLeft(),
                    toolbar.getPaddingTop() + getStateBarHeight(this),
                    toolbar.getPaddingRight(),
                    toolbar.getPaddingBottom());
        }
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return int
     */
    private int getStateBarHeight(Context context) {
        //反射运行时类android.R.dimen.status_bar_height
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField("status_bar_height").get(object).toString();
            int height = Integer.valueOf(heightStr); //这边得到的是height的一个id值
            statusBarHeight = context.getResources().getDimensionPixelSize(height);//转化成px
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
