package com.ccl.perfectisshit.parallaxrecyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccl.perfectisshit.parallaxrecyclerview.widget.ParallaxItemDecoration;
import com.ccl.perfectisshit.parallaxrecyclerview.widget.ParallaxRecyclerView;

import java.util.Random;

public class MainActivity extends Activity {

    private ParallaxRecyclerView mParallaxRecyclerView;
    private SparseArrayCompat<String> data = new SparseArrayCompat<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initData();
        setView();
    }

    private void setView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mParallaxRecyclerView.setLayoutManager(linearLayoutManager);
        mParallaxRecyclerView.setAdapter(new ParallaxRecyclerViewAdapter(data));
        mParallaxRecyclerView.addItemDecoration(new ParallaxItemDecoration());
        mParallaxRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            data.put(i, String.valueOf(i));
        }
    }

    private void initView() {
        mParallaxRecyclerView = findViewById(R.id.prv);
    }

    private static class ParallaxRecyclerViewAdapter extends RecyclerView.Adapter<ParallaxRecyclerViewAdapter.ViewHolder> {

        private final Random mRandom;
        private SparseArrayCompat<String> mData;
        private LayoutInflater mLayoutInflater;

        ParallaxRecyclerViewAdapter(SparseArrayCompat<String> data) {
            mData = data;
            mRandom = new Random();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mLayoutInflater == null) {
                mLayoutInflater = LayoutInflater.from(parent.getContext());
            }
            return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_parallax_rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int backGroundColor = Color.argb(255, mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
            holder.mCardView.setCardBackgroundColor(backGroundColor);
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private final CardView mCardView;

            ViewHolder(View itemView) {
                super(itemView);
                mCardView = itemView.findViewById(R.id.card_view);
            }
        }
    }
}
