package selbylei.com.lsn5_materialdesign_recyclerview_head_bottom.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by selbylei on 17/3/21.
 */

public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter {


    private ArrayList<View> mHeaderViewInfos = new ArrayList<View>();
    private ArrayList<View> mFooterViewInfos = new ArrayList<View>();
    private RecyclerView.Adapter mAdapter;

    private static final int TYPE_HEAD = -1;
    private static final int TYPE_FOOTER = -2;


    public HeaderViewRecyclerAdapter(ArrayList<View> headerViewInfos, ArrayList<View> footerViewInfos, RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        if (headerViewInfos == null) {
            mHeaderViewInfos = new ArrayList<View>();
        } else {
            mHeaderViewInfos = headerViewInfos;
        }
        if (mFooterViewInfos == null) {
            mFooterViewInfos = new ArrayList<View>();
        } else {
            mFooterViewInfos = footerViewInfos;
        }
    }


    //判断当前是什么类型
    @Override
    public int getItemViewType(int position) {
        //header
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {  //代表头部
            return TYPE_HEAD;
        }

        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        //footer
        return TYPE_FOOTER;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEAD) {
            return new HeaderViewHolder(mHeaderViewInfos.get(0));
        } else if (viewType == TYPE_FOOTER) {
            return new HeaderViewHolder(mFooterViewInfos.get(0));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeadersCount();
        //代表头部
        if (position < numHeaders) return;
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder,adjPosition);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }


    private int getFootersCount() {
        return mFooterViewInfos.size();
    }

    private int getHeadersCount() {
        return mHeaderViewInfos.size();
    }


    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
