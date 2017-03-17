package selbylei.com.lsn2_materialdesign_recyclerview1;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selbylei on 17/3/17.
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    private List<String> mList;
    private List<Integer> heights;

    public StaggeredAdapter(Context context, List<String> list) {
        this.mList = list;
        heights = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            heights.add((int) (200 + Math.random() * 50));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view =View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);
//        View view = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, parent);
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {//绑定数据

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.textView.getLayoutParams();
        params.height = heights.get(position);
        holder.textView.setBackgroundColor(Color.rgb(100, heights.get(position) - 45, heights.get(position) - 45));
        holder.textView.setLayoutParams(params);
        holder.textView.setText(mList.get(position));

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
}
