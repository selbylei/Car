package selbylei.com.lsn6_recyclerview_animator;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by selbylei on 17/3/21.
 */

public class QQAdapter extends RecyclerView.Adapter<QQAdapter.QQViewHolder> implements ItemTouchMoveListener {

    private List<QQMessage> mList = new ArrayList<>();
    private DragListener mListener;


    public QQAdapter(DragListener listener, List<QQMessage> list) {
        this.mList = list;
        this.mListener = listener;
    }


    @Override
    public QQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_qq_item, parent, false);

//        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View itemView = layoutInflater.inflate(R.layout.adapter_qq_item, null);
        return new QQViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final QQViewHolder holder, int position) {
        holder.headImageView.setImageResource(mList.get(position).getResIcon());
        holder.nameTextView.setText(mList.get(position).getName());
        holder.messageTextView.setText(mList.get(position).getMsg());
        holder.timeTextView.setText(mList.get(position).getTime());

        holder.headImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //传递触摸给ItemHelper.CallBack
                    mListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 交换数据
     * @param fromPosition
     * @param toPosition
     * @return
     */
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public boolean onItemSwipe(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    class QQViewHolder extends RecyclerView.ViewHolder {
        private ImageView headImageView;
        private TextView nameTextView;
        private TextView messageTextView;
        private TextView timeTextView;

        QQViewHolder(View itemView) {
            super(itemView);
            headImageView = (ImageView) itemView.findViewById(R.id.headImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
        }
    }


}
