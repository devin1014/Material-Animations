package liuwei.android.animation.demo.core;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * User: liuwei
 * Date: 2018-09-12
 * Time: 15:46
 */
class FragmentTransactionWrapper extends FragmentTransaction
{
    private FragmentManager mFragmentManager;

    private FragmentTransaction mTransaction;

    @SuppressLint("CommitTransaction")
    FragmentTransactionWrapper(FragmentManager manager)
    {
        mFragmentManager = manager;

        mTransaction = manager.beginTransaction();
    }

    @Override
    public FragmentTransaction add(Fragment fragment, String tag)
    {
        mTransaction.add(fragment, tag);

        return this;
    }

    @Override
    public FragmentTransaction add(int containerViewId, Fragment fragment)
    {
        mTransaction.add(containerViewId, fragment);

        return this;
    }

    @Override
    public FragmentTransaction add(int containerViewId, Fragment fragment, @Nullable String tag)
    {
        mTransaction.add(containerViewId, fragment, tag);

        return this;
    }

    @Override
    public FragmentTransaction replace(int containerViewId, Fragment fragment)
    {
        mTransaction.replace(containerViewId, fragment);

        return this;
    }

    @Override
    public FragmentTransaction replace(int containerViewId, Fragment fragment, @Nullable String tag)
    {
        mTransaction.replace(containerViewId, fragment, tag);

        return this;
    }

    @Override
    public FragmentTransaction remove(Fragment fragment)
    {
        mTransaction.remove(fragment);

        return this;
    }

    @Override
    public FragmentTransaction hide(Fragment fragment)
    {
        mTransaction.hide(fragment);

        return this;
    }

    @Override
    public FragmentTransaction show(Fragment fragment)
    {
        mTransaction.show(fragment);

        return this;
    }

    @Override
    public FragmentTransaction detach(Fragment fragment)
    {
        mTransaction.detach(fragment);

        return this;
    }

    @Override
    public FragmentTransaction attach(Fragment fragment)
    {
        mTransaction.attach(fragment);

        return this;
    }

    @Override
    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment)
    {
        mTransaction.setPrimaryNavigationFragment(fragment);

        return this;
    }

    @Override
    public boolean isEmpty()
    {
        return mTransaction.isEmpty();
    }

    @Override
    public FragmentTransaction setCustomAnimations(int enter, int exit)
    {
        mTransaction.setCustomAnimations(enter, exit);

        return this;
    }

    @Override
    public FragmentTransaction setCustomAnimations(int enter, int exit, int popEnter, int popExit)
    {
        mTransaction.setCustomAnimations(enter, exit, popEnter, popExit);

        return this;
    }

    @Override
    public FragmentTransaction addSharedElement(View sharedElement, String name)
    {
        mTransaction.addSharedElement(sharedElement, name);

        return this;
    }

    @Override
    public FragmentTransaction setTransition(int transit)
    {
        mTransaction.setTransition(transit);

        return this;
    }

    @Override
    public FragmentTransaction setTransitionStyle(int styleRes)
    {
        mTransaction.setTransitionStyle(styleRes);

        return this;
    }

    @Override
    public FragmentTransaction addToBackStack(@Nullable String name)
    {
        mTransaction.addToBackStack(name);

        return this;
    }

    @Override
    public boolean isAddToBackStackAllowed()
    {
        return mTransaction.isAddToBackStackAllowed();
    }

    @Override
    public FragmentTransaction disallowAddToBackStack()
    {
        mTransaction.disallowAddToBackStack();

        return this;
    }

    @Override
    public FragmentTransaction setBreadCrumbTitle(int res)
    {
        mTransaction.setBreadCrumbTitle(res);

        return this;
    }

    @Override
    public FragmentTransaction setBreadCrumbTitle(CharSequence text)
    {
        mTransaction.setBreadCrumbTitle(text);

        return this;
    }

    @Override
    public FragmentTransaction setBreadCrumbShortTitle(int res)
    {
        mTransaction.setBreadCrumbShortTitle(res);

        return this;
    }

    @Override
    public FragmentTransaction setBreadCrumbShortTitle(CharSequence text)
    {
        mTransaction.setBreadCrumbShortTitle(text);

        return this;
    }

    @Override
    public FragmentTransaction setReorderingAllowed(boolean reorderingAllowed)
    {
        mTransaction.setReorderingAllowed(reorderingAllowed);

        return this;
    }

    @Override
    @Deprecated
    public FragmentTransaction setAllowOptimization(boolean allowOptimization)
    {
        mTransaction.setAllowOptimization(allowOptimization);

        return this;
    }

    @Override
    public FragmentTransaction runOnCommit(Runnable runnable)
    {
        mTransaction.runOnCommit(runnable);

        return this;
    }

    @Override
    public int commit()
    {
        return mTransaction.commit();
    }

    @Override
    public int commitAllowingStateLoss()
    {
        return mTransaction.commitAllowingStateLoss();
    }

    @Override
    public void commitNow()
    {
        mTransaction.commitNow();
    }

    @Override
    public void commitNowAllowingStateLoss()
    {
        mTransaction.commitNowAllowingStateLoss();
    }

    public final FragmentManager getFragmentManager()
    {
        return mFragmentManager;
    }
}
