package selbylei.com.lsn10_materialdesign_toolbar_searchview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by selbylei on 17/4/12.
 */

public class MyScrollView extends ScrollView {


    private onScrollListener mListener;

    public void setScrollListener(onScrollListener mListener) {
        this.mListener = mListener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        int screenHeight = getContext().getResources().getDisplayMetrics().heightPixels; //屏幕的高度
        int scrollY = getScrollY();
        if (mListener!=null){
            if (scrollY<=screenHeight/2f){
                mListener.scrolling(  scrollY/(screenHeight/2f));
            }
       }
        super.onScrollChanged(l, t, oldl, oldt);
    }



    interface onScrollListener {
        void scrolling(float alpha);
    }
}
