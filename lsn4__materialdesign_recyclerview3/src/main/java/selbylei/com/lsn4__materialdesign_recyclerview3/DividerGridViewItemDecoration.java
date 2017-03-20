package selbylei.com.lsn4__materialdesign_recyclerview3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by selbylei on 17/3/19.
 */

public class DividerGridViewItemDecoration extends RecyclerView.ItemDecoration {
    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };
    private Context mContext;

    public DividerGridViewItemDecoration(Context context, int mOrientation) {

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

        drawVertical(c, parent);
        drawHorizontal(c, parent);

    }

    //绘制水平间隔线
    private void drawVertical(Canvas c, RecyclerView parent) {

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //绘制垂直间隔线
    private void drawHorizontal(Canvas c, RecyclerView parent) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int right = child.getRight() + params.rightMargin;
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

    }


    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        //四个方向的偏移量
        int right = mDivider.getIntrinsicWidth();
        int bottom = mDivider.getIntrinsicWidth();
        outRect.set(0, 0, right, bottom);
    }
}
