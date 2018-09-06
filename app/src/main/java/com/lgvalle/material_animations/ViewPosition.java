package com.lgvalle.material_animations;

import android.view.View;

import java.io.Serializable;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 18:32
 */
public class ViewPosition implements Serializable
{
    private int mWith;

    private int mHeight;

    private int[] mLocationInWindow = new int[2];

    private int[] mLocationOnScreen = new int[2];

    public ViewPosition(View targetView)
    {
        mWith = targetView.getWidth();

        mHeight = targetView.getHeight();

        targetView.getLocationInWindow(mLocationInWindow);

        targetView.getLocationOnScreen(mLocationOnScreen);
    }

    public int getWith()
    {
        return mWith;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int[] getLocationInWindow()
    {
        return mLocationInWindow;
    }

    public int[] getLocationOnScreen()
    {
        return mLocationOnScreen;
    }
}
