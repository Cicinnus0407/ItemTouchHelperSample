package com.cicinnus0407.itemtouchhelpersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by cicinnus on 17-8-28.
 * 拖拽切换位置,最简单的方式,通过长按view进行拖拽
 */

public class ItemTouchHelperActivity extends AppCompatActivity {


    RecyclerView rvTouchHelper;
    private ItemTouchHelperAdapter touchHelperAdapter;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_helper);
        initRv();
        initTouchHelper();
    }


    private void initTouchHelper() {
        //第一个参数是允许拖拽的方向,第二个参数是允许滑动的方向
        MyCallBack myCallBack = new MyCallBack(
                ItemTouchHelper.UP |
                        ItemTouchHelper.DOWN |
                        ItemTouchHelper.LEFT |
                        ItemTouchHelper.RIGHT, 0);
        //设置数据改变的adapter
        myCallBack.setAdapter(touchHelperAdapter);
        //初始化ItemTouchHelper
        itemTouchHelper = new ItemTouchHelper(myCallBack);
        //与rv绑定
        itemTouchHelper.attachToRecyclerView(rvTouchHelper);
    }

    private void initRv() {
        rvTouchHelper = (RecyclerView) findViewById(R.id.rv_touch_helper);
        //Adapter
        touchHelperAdapter = new ItemTouchHelperAdapter(getData());
        //网格LayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rvTouchHelper.setLayoutManager(gridLayoutManager);
        rvTouchHelper.setAdapter(touchHelperAdapter);

        //点击
        touchHelperAdapter.setOnItemClickListener(new ItemTouchHelperAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, ItemTouchHelperBean bean) {
                Toast.makeText(ItemTouchHelperActivity.this, bean.title + ":\n position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //拖拽
        touchHelperAdapter.setOnItemDragListener(new ItemTouchHelperAdapter.OnItemDragListener() {
            @Override
            public void onDrag(ItemTouchHelperAdapter.ItemTouchViewHolder holder) {
                itemTouchHelper.startDrag(holder);
            }
        });

    }

    //生成数据
    private ArrayList<ItemTouchHelperBean> getData() {
        ArrayList<ItemTouchHelperBean> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new ItemTouchHelperBean(i, "title-" + i));
        }
        return list;
    }


    //拖拽的简单回调
    private class MyCallBack extends ItemTouchHelper.SimpleCallback {

        private RecyclerView.Adapter adapter;

        //设置adapter
        public void setAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        public MyCallBack(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            final int fromPos = viewHolder.getAdapterPosition();
            final int toPos = target.getAdapterPosition();
            //通知adapter移动了位置
            adapter.notifyItemMoved(fromPos, toPos);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        }

        //禁止长按view拖拽
        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        //禁止滑动
        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }
    }
}
