package selbylei.com.lsn6_recyclerview_animator;

/**
 * Created by selbylei on 17/3/21.
 */

public interface ItemTouchMoveListener {


    /**
     * 当拖拽的时候回调
     * 可以在此方法里面实现；拖拽条目并实现刷新效果
     * @param fromPosition
     * @param toPosition
     * @return 是否调了这个方法
     */
    boolean onItemMove(int fromPosition,int toPosition);

    boolean onItemSwipe(int position);
}
