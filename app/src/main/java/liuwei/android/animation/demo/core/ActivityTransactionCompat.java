package liuwei.android.animation.demo.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import liuwei.android.animation.demo.TransitionUtils;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-13
 * Time: 11:11
 */
public class ActivityTransactionCompat
{
    public static void startActivity(Activity activity, Class<?> targetActivity)
    {
        startActivity(activity, targetActivity, null);
    }

    public static void startActivity(Activity activity, Class<?> targetActivity, Bundle bundle)
    {
        startActivity(activity, targetActivity, bundle, activity.getWindow().getDecorView());
    }

    public static void startActivity(Activity activity, Class<?> targetActivity, Bundle bundle, View sharedView)
    {
        startActivityWithSharedElement(activity, targetActivity, bundle, TransitionUtils.parseTransitionView2Pair(sharedView));
    }

    private static void startActivityWithSharedElement(Activity activity, Class<?> targetActivity, Bundle bundle, Pair<View, String>[] sharedElements)
    {
        if (activity != null)
        {
            Intent intent = new Intent(activity, targetActivity);

            if (bundle != null)
            {
                intent.putExtras(bundle);
            }

            Bundle options = null;

            if (sharedElements != null && sharedElements.length > 0)
            {
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElements).toBundle();
            }

            activity.startActivity(intent, options);
        }
    }
}
