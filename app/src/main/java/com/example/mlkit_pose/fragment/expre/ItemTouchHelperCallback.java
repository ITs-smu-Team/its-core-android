package com.example.mlkit_pose.fragment.expre;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wlsdud.choi on 2016-04-04.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public final String TAG = "[Simple][ItemTouchHelperCallback]";
    ItemTouchHelperListener listener;

    public ItemTouchHelperCallback(ItemTouchHelperListener listener){
        this.listener = listener;
    }

    // 각 view에서 어떤 user action이 가능한지 정의
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END ;

        return makeMovementFlags(0, swipeFlags);
    }


    // user가 item을 drag할 때, ItemTouchHelper가 onMove()를 호출
    @SuppressLint("LongLogTag")
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove. sourcePosition, targetPosition"+source.getAdapterPosition()+","+target.getAdapterPosition());

        listener.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    // view가 swipe될 때, ItemTouchHelper는 뷰가 사라질때까지 animate한 후, onSwiped()를 호출
    @SuppressLint("LongLogTag")
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped");
        listener.onItemRemove(viewHolder.getAdapterPosition());
    }
}