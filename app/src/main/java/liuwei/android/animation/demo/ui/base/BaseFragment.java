package liuwei.android.animation.demo.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import liuwei.android.animation.demo.core.FragmentTransactionCompat;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 18:46
 */
public abstract class BaseFragment extends Fragment
{
    private Unbinder mButterKnife;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    protected abstract int getFragmentLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        FragmentTransactionCompat.resetFragmentLayoutTransitionName(this);

        mButterKnife = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView()
    {
        mButterKnife.unbind();

        super.onDestroyView();
    }
}
