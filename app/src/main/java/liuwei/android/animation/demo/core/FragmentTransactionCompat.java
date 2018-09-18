package liuwei.android.animation.demo.core;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.View;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-12
 * Time: 15:59
 */
public class FragmentTransactionCompat extends FragmentTransactionWrapper implements ITransitionCompat
{
    @SuppressLint("CommitTransaction")
    public static FragmentTransactionCompat getInstance(FragmentActivity activity)
    {
        return new FragmentTransactionCompat(activity.getSupportFragmentManager());
    }

    @SuppressLint("CommitTransaction")
    public static FragmentTransactionCompat getInstance(Fragment fragment)
    {
        return new FragmentTransactionCompat(fragment.getFragmentManager());
    }

    @SuppressLint("CommitTransaction")
    public static FragmentTransactionCompat getInstance(FragmentManager fragmentManager)
    {
        return new FragmentTransactionCompat(fragmentManager);
    }

    // ---------------------------------------------------------------------------------------------------------
    // - content
    // ---------------------------------------------------------------------------------------------------------
    private FragmentTransactionCompat(FragmentManager manager)
    {
        super(manager);
    }

    public FragmentTransaction replaceWithSharedElement(int containerViewId, Fragment fragment)
    {
        Fragment currFragment = getFragmentManager().findFragmentById(containerViewId);

        return replaceWithSharedElement(containerViewId, fragment, currFragment != null ? currFragment.getView() : null);
    }

    public FragmentTransaction replaceWithSharedElement(int containerViewId, Fragment fragment, View sharedElement)
    {
        return replaceWithSharedElement(containerViewId, fragment, sharedElement, -1);
    }

    public FragmentTransaction replaceWithSharedElement(int containerViewId, Fragment fragment, View sharedElement, int transitionNameTagSuffix)
    {
        resetFragmentTransitionObject(fragment);

        if (transitionNameTagSuffix > -1)
        {
            TransitionUtils.resetTransitionNameForRecyclerView(sharedElement, transitionNameTagSuffix);

            Bundle bundle = new Bundle();

            if (fragment.getArguments() != null)
            {
                bundle.putAll(fragment.getArguments());
            }

            bundle.putInt(KEY_EXTRA_TRANSITION_TAG_SUFFIX, transitionNameTagSuffix);

            fragment.setArguments(bundle);
        }

        replace(containerViewId, fragment);

        addSharedElementWithTransitionName(sharedElement);

        return this;
    }

    private void resetFragmentTransitionObject(Fragment fragment)
    {
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP)
        {
            if (fragment != null && fragment.getSharedElementEnterTransition() == null)
            {
                TransitionSet transitionSet = new TransitionSet();
                transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
                transitionSet
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTransition(new ChangeImageTransform());

                fragment.setEnterTransition(new Fade());
                fragment.setExitTransition(new Fade());
                fragment.setSharedElementEnterTransition(transitionSet);
                fragment.setSharedElementReturnTransition(transitionSet);
                fragment.setAllowEnterTransitionOverlap(true);
                fragment.setAllowReturnTransitionOverlap(true);
            }
        }
    }

    private void addSharedElementWithTransitionName(View sharedElement)
    {
        if (sharedElement != null)
        {
            List<View> transitionViews = TransitionUtils.parseTransitionView(sharedElement);

            if (transitionViews != null && transitionViews.size() > 0)
            {
                Set<String> nameSet = new HashSet<>();

                for (View view : transitionViews)
                {
                    String transitionName = ViewCompat.getTransitionName(view);

                    if (!nameSet.contains(transitionName))
                    {
                        nameSet.add(transitionName);

                        addSharedElement(view, transitionName);
                    }
                }
            }
        }
    }

    public static void resetFragmentLayoutTransitionName(Fragment fragment)
    {
        if (fragment != null)
        {
            Bundle bundle = fragment.getArguments();

            int transitionNameTagSuffix = bundle != null ? bundle.getInt(KEY_EXTRA_TRANSITION_TAG_SUFFIX, -1) : -1;

            if (transitionNameTagSuffix == -1 && fragment.getActivity() != null)
            {
                transitionNameTagSuffix = fragment.getActivity().getIntent().getIntExtra(KEY_EXTRA_TRANSITION_TAG_SUFFIX, -1);
            }

            resetLayoutTransitionName(fragment.getView(), transitionNameTagSuffix);
        }
    }

    public static void resetLayoutTransitionName(View view, int transitionNameTagSuffix)
    {
        if (transitionNameTagSuffix > -1)
        {
            TransitionUtils.resetTransitionNameForRecyclerView(view, transitionNameTagSuffix);
        }
    }
}
