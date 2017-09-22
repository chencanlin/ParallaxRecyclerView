package com.ccl.perfectisshit.parallaxrecyclerview.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.ccl.perfectisshit.parallaxrecyclerview.utils.Util;

import java.lang.ref.SoftReference;

/**
 * Created by ccl on 2017/9/21.
 */

public class ParallaxRecyclerView extends RecyclerView {


    public ParallaxRecyclerView(Context context) {
        this(context, null);
    }

    public ParallaxRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addOnScrollListener(new OnScrollListener(getContext()));
    }


    private class OnScrollListener extends RecyclerView.OnScrollListener {
        private SoftReference<Context> contextReference;

        OnScrollListener(Context context) {
            contextReference = new SoftReference<>(context);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (contextReference != null && contextReference.get() != null) {
                LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager && ((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                    int elevation = 1;
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                        View view = linearLayoutManager.findViewByPosition(i);
                        if (view != null) {
                            if (view instanceof CardView) {
                                CardView cardView = (CardView) view;
                                cardView.setCardElevation(Util.dp2Px(contextReference.get(), elevation));
                                elevation += 5;
                            }

                            float translationY = view.getTranslationY();
                            if (i > firstVisibleItemPosition) {
                                if (translationY != 0) {
                                    view.setTranslationY(0);
                                }
                            } else {
                                int top = view.getTop();
                                view.setTranslationY(-top / 2f);
                            }
                        }
                    }
                }
            }
        }
    }
}
