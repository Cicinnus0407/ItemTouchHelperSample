package com.cicinnus0407.itemtouchhelpersample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cicinnus on 17-8-28.
 */

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemTouchViewHolder> {

    private ArrayList<ItemTouchHelperBean> mList;
    private OnItemClickListener onItemClickListener;
    private OnItemDragListener onItemDragListener;//拖拽监听

    public ItemTouchHelperAdapter(ArrayList<ItemTouchHelperBean> data) {
        mList = new ArrayList<>();
        mList.addAll(data);
    }

    @Override
    public ItemTouchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_touch_helper, null);
        return new ItemTouchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemTouchViewHolder holder, final int position) {
        holder.tvTitle.setText(mList.get(position).title);
        //拖拽
        holder.ivDragButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    if (onItemDragListener != null) {
                        onItemDragListener.onDrag(holder);
                    }
                }
                return false;
            }
        });

        //点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(holder.getAdapterPosition(), mList.get(position));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemDragListener(OnItemDragListener onItemDragListener) {
        this.onItemDragListener = onItemDragListener;
    }

    public interface OnItemClickListener {
        void onClick(int position, ItemTouchHelperBean bean);
    }

    public interface OnItemDragListener {
        void onDrag(ItemTouchViewHolder holder);
    }


    public class ItemTouchViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivDragButton;
        public TextView tvTitle;

        public ItemTouchViewHolder(View itemView) {
            super(itemView);
            ivDragButton = (ImageView) itemView.findViewById(R.id.iv_drag_button);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
