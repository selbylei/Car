package selbylei.com.lsn11_materialdesign_palette;


import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by selbylei on 17/4/12.
 */

public class HomeFragment extends Fragment {

    private ImageView iv;
    private TextView tv_title, tv_content;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.from(container.getContext()).inflate(R.layout.fragment_home, container, false);
        iv = (ImageView) rootView.findViewById(R.id.iv);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        //得到bitmap中的一些色彩－－通过palette分析出来
        BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();

        //异步任务－－可能分析的图片比较大或者颜色比较复杂，会耗时比较久，防止卡死主线程
        Palette.from(drawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取google推荐的颜色的
                Palette.Swatch lightMutedSwatch = palette.getLightVibrantSwatch();
                //谷歌推荐：图片整体的颜色的rgb混合值－－主色调
                lightMutedSwatch.getRgb();
                //谷歌推荐：文本的颜色
                lightMutedSwatch.getBodyTextColor();
                //谷歌推荐：标题的颜色
                lightMutedSwatch.getTitleTextColor();
                //分析该颜色在图片中占有的像素值
                int population = lightMutedSwatch.getPopulation();

                int backgroundColor = getTranslucentColor(0.5f, lightMutedSwatch.getRgb());
                int titleColor = lightMutedSwatch.getTitleTextColor();
                int textColor = lightMutedSwatch.getTitleTextColor();
                tv_title.setBackgroundColor(backgroundColor);
                tv_title.setTextColor(titleColor);
                tv_content.setBackgroundColor(backgroundColor);
                tv_content.setTextColor(textColor);
            }
        });

        Palette.from(drawable.getBitmap()).generate();
        return rootView;
    }


    /**
     * 透明度       红色      绿色        红色
     * 1101 0111 1000 1011 1101 0111 1000 1011
     *
     * @param percent
     * @param rgb
     * @return
     */
    private int getTranslucentColor(float percent, int rgb) {

        int blue = Color.blue(rgb);
        int green = Color.green(rgb);
        int red = Color.red(rgb);
        int alpha = Color.alpha(red);
//        int blue = rgb & 0xff;
//        int green = rgb >> 8 & 0xff;
//        int red = rgb >> 16 & 0xff;
//        int alpha = rgb >>> 24;
        alpha = Math.round(alpha * percent);
        return Color.argb(alpha, red, green, blue);
    }
}
