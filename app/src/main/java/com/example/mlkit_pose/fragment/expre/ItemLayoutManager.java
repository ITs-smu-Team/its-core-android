package com.example.mlkit_pose.fragment.expre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wlsdud.choi on 2016-04-06.
 */
class ItemLayoutManager extends LinearLayoutManager {
    public final String TAG = "[Simple][ItemLayoutManager]";
    public ItemLayoutManager(Context context) {
        super(context);
    }

    public ItemLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ItemLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
//
//    @SuppressLint("LongLogTag")
//    @Override
//    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
//        Log.i(TAG, "onLayoutChildren");
//    }

    @SuppressLint("LongLogTag")
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        Log.i(TAG, "onMeasure");
    }


    @SuppressLint("LongLogTag")
    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
        Log.i(TAG, "scrollToPosition. position : "+position);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void scrollToPositionWithOffset(int position, int offset) {
        super.scrollToPositionWithOffset(position, offset);
        Log.i(TAG, "scrollToPositionWithOffset. position : "+position+", offset : "+offset);

    }

    @SuppressLint("LongLogTag")
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.i(TAG, "scrollVerticallyBy");
        return super.scrollVerticallyBy(dy, recycler, state);
    }
}