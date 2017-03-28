package selbylei.com.lsn5_materialdesign_recyclerview_head_bottom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import selbylei.com.lsn5_materialdesign_recyclerview_head_bottom.adapter.HeaderViewRecyclerAdapter;

/**
 * Created by selbylei on 17/3/21.
 */

public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViewInfos = new ArrayList<View>();
    private ArrayList<View> mFooterViewInfos = new ArrayList<View>();
    private Adapter mAdapter;

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //添加headView
    public void addHeaderView(View v) {
        mHeaderViewInfos.add(v);
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }


    //添加headView
    public void addFooterView(View v) {
        mFooterViewInfos.add(v);
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }


    @Override
    public void setAdapter(Adapter adapter) {

        if (mHeaderViewInfos.size() > 0 || mFooterViewInfos.size() > 0) {
            mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }


}
