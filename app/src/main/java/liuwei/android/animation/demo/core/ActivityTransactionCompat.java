package liuwei.android.animation.demo.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.Window;

/**
 * User: liuwei
 * Date: 2018-09-13
 * Time: 11:11
 */
public class ActivityTransactionCompat implements ITransitionCompat
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
        startActivity(activity, targetActivity, bundle, sharedView, -1);
    }

    public static void startActivity(Activity activity, Class<?> targetActivity, Bundle bundle, View sharedView, int transitionNameTagSuffix)
    {
        if (transitionNameTagSuffix > -1)
        {
            TransitionUtils.resetTransitionNameForRecyclerView(sharedView, transitionNameTagSuffix);

            if (bundle == null)
            {
                bundle = new Bundle();
            }

            bundle.putInt(KEY_EXTRA_TRANSITION_TAG_SUFFIX, transitionNameTagSuffix);
        }

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

    public static void requestActivityContentTransitionFeature(FragmentActivity fragmentActivity)
    {
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP)
        {
            fragmentActivity.requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
    }

    public static void resetActivityLayoutTransitionName(FragmentActivity fragmentActivity)
    {
        int transitionNameTagSuffix = fragmentActivity.getIntent().getIntExtra(KEY_EXTRA_TRANSITION_TAG_SUFFIX, -1);

        TransitionUtils.resetTransitionNameForRecyclerView(fragmentActivity.getWindow().getDecorView(), transitionNameTagSuffix);
    }


}
