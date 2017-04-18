package selbylei.com.lsn14_materialdesign_2;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;


/**
 * Created by selbylei on 17/4/17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断版本［4.4，5.0）设置导航栏和状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        }
    }


    //沉浸式设计，设置导航栏和状态栏
    public void setOrChangeTranslucentColor(Toolbar toolbar, View bottomNavigationBar, int translucentPrimaryColorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            if (toolbar != null) {
                LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
                p.height += getSystemComponentDimen(this, "status_bar_height");
                toolbar.setLayoutParams(p);
                toolbar.setPadding(
                        toolbar.getPaddingLeft(),
                        toolbar.getPaddingTop() + getSystemComponentDimen(this, "status_bar_height"),
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
            }
            if (bottomNavigationBar != null) {
                if (hasNavigationBarShow(getWindowManager())) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) bottomNavigationBar.getLayoutParams();
                    layoutParams.height += getSystemComponentDimen(this, "navigation_bar_height");
                    bottomNavigationBar.setLayoutParams(layoutParams);
                    bottomNavigationBar.setBackgroundColor(getResources().getColor(translucentPrimaryColorRes));
                }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(translucentPrimaryColorRes));
            getWindow().setNavigationBarColor(getResources().getColor(translucentPrimaryColorRes));
        }
    }


    /**
     * 获取系统的dimen值
     *
     * @param context
     * @param dimenName
     * @return
     */
    public static int getSystemComponentDimen(Context context, String dimenName) {
        int dimenHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.valueOf(heightStr); //这边得到的是height的一个id值
            dimenHeight = context.getResources().getDimensionPixelSize(height);//转化成px
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dimenHeight;
    }

    //判断是否有虚拟导航
    public static boolean hasNavigationBarShow(WindowManager wm) {
        //获取整个屏幕的高度
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getRealMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        int widthPixels = outMetrics.widthPixels;
        Log.i(TAG, "整个屏幕的高度: " + heightPixels + "整个屏幕的宽度: " + widthPixels);
        //获取内容展示部分的高度
        int heightPixels2 = wm.getDefaultDisplay().getHeight();
        int widthPixels2 = wm.getDefaultDisplay().getWidth();
        int w = widthPixels - widthPixels2;
        int h = heightPixels - heightPixels2;
        Log.i(TAG, "内容展示高度: " + heightPixels2 + "内容展示宽度: " + widthPixels2);
        Log.i(TAG, "高度差: " + h + "宽度差: " + w);
        return w > 0 || h > 0;
    }
}
