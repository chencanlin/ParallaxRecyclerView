# ParallaxRecyclerView
利用RecyclerView + CardView实现的卡片折叠效果


**效果展示：**

![](https://i.imgur.com/jWFEdeU.gif)

**卡片折叠效果**实际上是设置RecyclerView 的ItemDecoration 让每个条目的布局向上移

	 @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int currentChildPosition = parent.getChildLayoutPosition(view);
        int lastItemPosition = state.getItemCount() - 1;
        if (currentChildPosition != lastItemPosition) {
            outRect.bottom = -Util.dp2Px(parent.getContext(), 5);
        }
    }


**层叠效果是利用CardView的阴影效果制造的假象** ，cardView.setCardElevation()设置可见条目的Elevation逐条增加就产生了这种效果

	for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                        View view = linearLayoutManager.findViewByPosition(i);
                        if (view != null) {
                            if (view instanceof CardView) {
                                CardView cardView = (CardView) view;
                                cardView.setCardElevation(Util.dp2Px(contextReference.get(), elevation));
                                elevation += 5;
                            }
	｝

**滑动效果是添加RecyclerView的滑动监听，滑动的时候第一个可见条目滑动为每次向下移动离顶端距离的一半，其他条目正常滑动**

	int top = view.getTop();
                                view.setTranslationY(-	top / 2f);


