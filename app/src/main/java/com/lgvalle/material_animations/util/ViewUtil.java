package com.lgvalle.material_animations.util;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 19:26
 */
public class ViewUtil
{
    public static LayoutParams newLayoutParams(View view, int width, int height)
    {
        LayoutParams layoutParams = view.getLayoutParams();

        LayoutParams params;

        if (layoutParams instanceof LinearLayout.LayoutParams)
        {
            params = new LinearLayout.LayoutParams(layoutParams);
        }
        else if (layoutParams instanceof RelativeLayout.LayoutParams)
        {
            params = new RelativeLayout.LayoutParams(width, height);
        }
        else if (layoutParams instanceof FrameLayout.LayoutParams)
        {
            params = new FrameLayout.LayoutParams(width, height);
        }
        else
        {
            throw new IllegalArgumentException("view parent not Li");
        }

        params.width = width;

        params.height = height;

        return params;
    }
}
