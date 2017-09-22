package com.ccl.perfectisshit.parallaxrecyclerview.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ccl.perfectisshit.parallaxrecyclerview.utils.Util;

/**
 * Created by ccl on 2017/9/21.
 */

public class ParallaxItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int currentChildPosition = parent.getChildLayoutPosition(view);
        int lastItemPosition = state.getItemCount() - 1;
        if (currentChildPosition != lastItemPosition) {
            outRect.bottom = -Util.dp2Px(parent.getContext(), 5);
        }
    }
}
