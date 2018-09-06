package com.lgvalle.material_animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.lgvalle.material_animations.util.ViewUtil;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 18:30
 */
public class MyFragment3 extends Fragment
{
    private static final String EXTRA_SAMPLE = "sample";
    private static final String EXTRA_LOCATION = "location";

    public static MyFragment3 newInstance(Sample sample, View targetView)
    {
        MyFragment3 fragment = new MyFragment3();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        args.putSerializable(EXTRA_LOCATION, new ViewPosition(targetView));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);
        ImageView squareBlue = view.findViewById(R.id.image);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.color);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        ViewPosition position = (ViewPosition) getArguments().getSerializable(EXTRA_LOCATION);

        View targetView = getView().findViewById(R.id.shared_target_content);
        LayoutParams layoutParams = targetView.getLayoutParams();
        targetView.setLayoutParams(ViewUtil.newLayoutParams(targetView, position.getWith(), position.getHeight()));
        int[] location = new int[2];
        targetView.getLocationInWindow(location);

        float startX = position.getWith() * 1f / targetView.getMeasuredWidth();
        float startY = position.getHeight() * 1f / targetView.getMeasuredHeight();

        final long duration = 5000;

        AnimatorSet animationSet = new AnimatorSet();

        ObjectAnimator alpha = ObjectAnimator.ofFloat(targetView, "alpha", 0.5f, 1f);
        alpha.setInterpolator(new AccelerateDecelerateInterpolator());
        alpha.setDuration(duration);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(targetView, "scaleX", startX, 1);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(targetView, "scaleY", startY, 1);
        animationSet.setDuration(duration);
        animationSet.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator translateY = ObjectAnimator.ofFloat(targetView, "Y", position.getLocationInWindow()[1] - position.getHeight(), 0);
        translateY.setInterpolator(new AccelerateInterpolator());
        translateY.setDuration(duration);
        translateY.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
            }
        });

        animationSet.play(alpha).with(translateY).with(scaleXAnimator).with(scaleYAnimator);
        animationSet.start();
    }
}
