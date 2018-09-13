package liuwei.android.animation.demo.core;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
public class FragmentTransactionCompat extends FragmentTransactionWrapper
{
    @SuppressLint("CommitTransaction")
    public static FragmentTransactionCompat getInstance(AppCompatActivity activity)
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

    private FragmentTransactionCompat(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public FragmentTransaction replace(int containerViewId, Fragment fragment)
    {
        return replace(containerViewId, fragment, getFragmentTag(fragment));
    }

    @Override
    public FragmentTransaction replace(int containerViewId, Fragment fragment, @Nullable String tag)
    {
        return super.replace(containerViewId, fragment, tag);
    }

    @Override
    public FragmentTransaction addSharedElement(View sharedElement, String name)
    {
        return super.addSharedElement(sharedElement, name);
    }

    public FragmentTransaction addSharedElementWithTransitionName(View sharedElement)
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

        return this;
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

    private static final String KEY_EXTRA_RECYCLER_ITEM_POSITION = "FragmentTransactionCompat.key.extra.itemPosition";

    public FragmentTransaction replaceWithSharedElement(int containerViewId, Fragment fragment, View sharedElement, int recyclerItemPosition)
    {
        resetFragmentTransitionObject(fragment);

        if (recyclerItemPosition > -1)
        {
            Bundle bundle = new Bundle();

            if (fragment.getArguments() != null)
            {
                bundle.putAll(fragment.getArguments());
            }

            bundle.putInt(KEY_EXTRA_RECYCLER_ITEM_POSITION, recyclerItemPosition);

            fragment.setArguments(bundle);
        }

        replace(containerViewId, fragment);

        addSharedElementWithTransitionName(sharedElement);

        return this;
    }

    public static int getRecyclerItemPosition(Fragment fragment)
    {
        if (fragment != null && fragment.getArguments() != null)
        {
            Bundle bundle = fragment.getArguments();

            if (bundle.containsKey(KEY_EXTRA_RECYCLER_ITEM_POSITION))
            {
                return bundle.getInt(KEY_EXTRA_RECYCLER_ITEM_POSITION, -1);
            }
        }

        return -1;
    }

    private void resetFragmentTransitionObject(Fragment fragment)
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

    private String getFragmentTag(Fragment fragment)
    {
        if (fragment == null)
        {
            return null;
        }

        String tag = fragment.getTag();

        if (TextUtils.isEmpty(tag))
        {
            tag = fragment.getClass().getSimpleName();
        }

        return tag;
    }

}
