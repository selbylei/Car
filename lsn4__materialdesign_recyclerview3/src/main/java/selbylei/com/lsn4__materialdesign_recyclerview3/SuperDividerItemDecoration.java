package selbylei.com.lsn4__materialdesign_recyclerview3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by selbylei on 17/3/20.
 * 去掉右边和左边的线
 */

public class SuperDividerItemDecoration extends RecyclerView.ItemDecoration {


    private Drawable mDivider;
    private int spanCount;
    private Context mContext;
    private int mOrientation;
    private int[] attrs = {
            android.R.attr.listDivider
    };

    public SuperDividerItemDecoration(Context mContext, int mOrientation) {
        this.mContext = mContext;
        this.mOrientation = mOrientation;

        TypedArray ta = mContext.obtainStyledAttributes(attrs);
        mDivider = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }


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

        super.getItemOffsets(outRect, itemPosition, parent);

        //注意这四个偏移量是这个相对于每个item的偏移量，四周的四条线
        int right = mDivider.getIntrinsicWidth();
        int bottom = mDivider.getIntrinsicWidth();
        if (isLastColumn(itemPosition, parent)) {
            right = 0;
        }
        if (isLastRow(itemPosition, parent)) {
            bottom = 0;
        }
        outRect.set(0, 0, right, bottom);
    }

    //判断是否最后一行
    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = parent.getAdapter().getItemCount(); //总items数
            int spanCount = getSpanCount(parent);      //总的组数
            int lastCount = childCount % spanCount;
            if (lastCount == 0 || lastCount < spanCount) {
                return true;
            }
        }
        return false;
    }

    //判断是否最后一列
    private boolean isLastColumn(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = getSpanCount(parent);
            if ((itemPosition + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    //获取有多少组
    private int getSpanCount(RecyclerView parent) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager glm = (GridLayoutManager) layoutManager;
            return glm.getSpanCount();
        }
        return 0;
    }


}
