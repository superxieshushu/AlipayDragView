package com.saltedfish.alipaydrag;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by SaltedFish on 2017/8/26.
 * 拖拽计算辅助类
 */

public class DragHelp {
    private Context mContext;

    public DragHelp(Context context) {
        mContext = context;
    }

    public int getPosition(float x, float y) {
        int screenWidth = getDisplayWidth();
        if (x >= 0 && x <= screenWidth / 4) {
            if (screenWidth / 4 >= y) {
                return 0;
            } else if (screenWidth / 2 >= y) {
                return 4;
            } else {
                return 8;
            }
        } else if (x > screenWidth / 4 && x <= screenWidth / 2) {
            if (screenWidth / 4 >= y) {
                return 1;
            } else if (screenWidth / 2 >= y) {
                return 5;
            } else {
                return 9;
            }
        } else if (x > screenWidth / 2 && x <= 3 * screenWidth / 4) {
            if (screenWidth / 4 >= y) {
                return 2;
            } else if (screenWidth / 2 >= y) {
                return 6;
            } else {
                return 10;
            }
        } else if (x > 3 * screenWidth / 4 && x <= screenWidth) {
            if (screenWidth / 4 >= y) {
                return 3;
            } else if (screenWidth / 2 >= y) {
                return 7;
            } else {
                return 11;
            }
        } else {
            return -1;
        }
    }

    private int getDisplayWidth() {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    private int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
