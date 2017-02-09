package com.sean.android.mvvmsample.ui.issuedetail;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

/**
 * Created by sean on 2017. 1. 8..
 */

public class CommentsRecyclerViewLayoutManager extends LinearLayoutManager {

    private Context context;

    public CommentsRecyclerViewLayoutManager(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, context.getResources().getDisplayMetrics());

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,10,10,0);

        return layoutParams;
    }


}
