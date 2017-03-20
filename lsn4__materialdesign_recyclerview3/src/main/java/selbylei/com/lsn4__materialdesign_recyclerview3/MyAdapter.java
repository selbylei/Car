package selbylei.com.lsn4__materialdesign_recyclerview3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by selbylei on 17/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> mList;

    private OnItemClickListener mOnClickListener;

    MyAdapter(List list) {
        this.mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position) {
        holder.textView.setText(mList.get(position));
        holder.itemView.setOnClickListener(new MyClickListener(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    void addItem(int position) {
        mList.add(position, "item" + position);
        notifyDataSetChanged();
    }

    //删除
    void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);  //移除通知
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    void setOnItemClickListener(OnItemClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }


    class MyClickListener implements View.OnClickListener {

        private int pos;

        public MyClickListener(int pos) {
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {
            if(mOnClickListener!=null){
                mOnClickListener.onItemClick(v, pos);
            }
        }
    }
}
