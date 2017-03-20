package selbylei.com.lsn4__materialdesign_recyclerview3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by selbylei on 17/3/18.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int[] attrs = new int[]{
            android.R.attr.listDivider,
     };
    private Context mContext;

    public MyItemDecoration(Context context, int mOrientation) {

        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);

        typedArray.recycle();


//        mDivider = AppCompatResources.getDrawable(mContext, R.drawable.item_divider);
        setOrientation(mOrientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("哥们你搞笑吧");
        }
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        //RecyclerView会回调绘制方法
        if (mOrientation == LinearLayoutManager.VERTICAL) {  //垂直
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

        super.onDraw(c, parent, state);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {

        int top = parent.getPaddingTop();
        int bottom = parent.getBottom() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() + params.leftMargin + Math.round(ViewCompat.getTranslationX(child)) + child.getWidth();
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //垂直分割线
    private void drawVertical(Canvas c, RecyclerView parent) {

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        /**
         *    获得条目的偏移量(所有的条目都会调用这个方法)
         *    获取每个间隔条目的矩形区域
         */
        if (mOrientation == LinearLayoutManager.VERTICAL) {  //垂直
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

}
