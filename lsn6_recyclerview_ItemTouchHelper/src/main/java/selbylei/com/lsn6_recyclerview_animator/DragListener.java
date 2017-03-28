package selbylei.com.lsn6_recyclerview_animator;

import android.support.v7.widget.RecyclerView;

/**
 * Created by selbylei on 17/3/21.
 * 改接口用于需要主动回调拖拽效果
 */

public interface DragListener {
    void onStartDrag(RecyclerView.ViewHolder holder);
}
