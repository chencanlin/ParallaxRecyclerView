package com.ccl.perfectisshit.parallaxrecyclerview.utils;

import android.content.Context;

/**
 * Created by ccl on 2017/9/21.
 */

public class Util {

    public static int dp2Px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
}
