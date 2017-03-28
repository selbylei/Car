package selbylei.com.lsn6_recyclerview_animator;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

/**
 * Created by selbylei on 17/3/21.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchMoveListener moveListener;
    private Context mContext;

    public  MyItemTouchHelperCallback(Context context, ItemTouchMoveListener moveListener) {
        this.moveListener = moveListener;
        this.mContext = context;
    }

    /**
     * callback回调监听时先调用的方法，用来判断当前是什么动作，比如用来判断方向
     * 意思就是来判断向哪个方向拖动
     * 方向:up,down,left,right
     * 常量
     * ItemTouchHelper.UP        0x00001
     * ItemTouchHelper.DOWN      0x0010
     * ItemTouchHelper.LEFT      0x0100
     * ItemTouchHelper.RIGHT     0x1000
     * 通过位运算来计算
     * 返回的数值跟上面的常量做与运算得到的结果如果就是常量的值，说明这个返回中有这个常量的值，表示监听了这个方向的滑动
     * makeMovementFlags(dragFlags,swipeFlags)
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        //我要监听的拖拽方向
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //我要监听的侧滑方向
        int swipeFlags = ItemTouchHelper.LEFT;
        //最终我们生成的flag
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    //是否允许长按拖动效果
    @Override
    public boolean isLongPressDragEnabled() {
//        return super.isLongPressDragEnabled();
        return true;
    }

    /**
     * 当移动的时候回调－－拖拽
     * 在拖拽的过程中不断的调用adapter.notifyItemMove(from,to)方法
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        return moveListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    /**
     * 当侧滑的时候回调
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //当滑动距离超出一半的时候滑动距离设置为一半，同时要设置控件不能再向左滑动
        float state = (float) (-0.5 * viewHolder.itemView.getWidth());
        if (direction == ItemTouchHelper.LEFT) {
            moveListener.onItemSwipe(viewHolder.getAdapterPosition());
        } else if (direction == ItemTouchHelper.RIGHT) {
            Toast.makeText(mContext, "左滑删除", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {  //闲置状态
            viewHolder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 这个方法用来恢复我们对tiem做的一些动画特效
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        viewHolder.itemView.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        viewHolder.itemView.setAlpha(1); //1~0
        viewHolder.itemView.setScaleX(1);
        viewHolder.itemView.setScaleY(1);
        viewHolder.itemView.setTranslationX(0);
        super.clearView(recyclerView, viewHolder);

    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //dx水平方向拖动的增量(负往左，正往右)
        float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            viewHolder.itemView.setAlpha(alpha); //1~0
            viewHolder.itemView.setScaleX(alpha);
            viewHolder.itemView.setScaleY(alpha);
        }

        if (alpha == 0) {
            viewHolder.itemView.setAlpha(1); //1~0
            viewHolder.itemView.setScaleX(1);
            viewHolder.itemView.setScaleY(1);
        }
        //这个方法自动处理setTranslateX动画
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }
}
