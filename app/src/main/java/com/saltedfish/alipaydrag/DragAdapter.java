package com.saltedfish.alipaydrag;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SaltedFish on 2017/8/26.
 * 拖拽的适配器
 */

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.DragViewHolder> {
    private Context mContext;
    private OnDragItemListener mOnDragItemListener;

    public DragAdapter(Context context) {
        mContext = context;
    }

    @Override
    public DragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DragViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_drag, parent, false));
    }

    @Override
    public void onBindViewHolder(final DragViewHolder holder, int position) {
        holder.mTvContent.setText("" + position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startDrag(view);
                if (null != mOnDragItemListener) {
                    mOnDragItemListener.onDragItem(holder.getAdapterPosition());
                }
                return true;
            }
        });
    }

    private void startDrag(View view) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(data, shadowBuilder, view, 0);
        } else {
            view.startDrag(data, shadowBuilder, view, 0);
        }
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    static class DragViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvContent;

        private DragViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public void setOnDragItemListener(OnDragItemListener onDragItemListener) {
        mOnDragItemListener = onDragItemListener;
    }

    public interface OnDragItemListener {
        void onDragItem(int position);
    }
}
