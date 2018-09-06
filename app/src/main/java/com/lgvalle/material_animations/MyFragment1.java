package com.lgvalle.material_animations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 15:19
 */
public class MyFragment1 extends Fragment
{
    private static final String EXTRA_SAMPLE = "sample";

    public static MyFragment1 newInstance(Sample sample)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        MyFragment1 fragment = new MyFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);

        final ImageView squareBlue = view.findViewById(R.id.image);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.color);

        final View contentView = view.findViewById(R.id.shared_target_content);

        view.findViewById(R.id.shared_target_content).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MyFragment2 sharedElementFragment2 = MyFragment2.newInstance(sample);

                //Explode enterTransition = new Explode();
                //enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
                Slide enterTransition = new Slide(Gravity.TOP);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
                Slide exitTransition = new Slide(Gravity.BOTTOM);
                exitTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

                //ChangeTransform change = new ChangeTransform();
                //ChangeImageTransform change = new ChangeImageTransform();
                ChangeBounds change = new ChangeBounds();
                //ChangeClipBounds change = new ChangeClipBounds();
                change.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

                sharedElementFragment2.setEnterTransition(enterTransition);
                sharedElementFragment2.setExitTransition(enterTransition);
                sharedElementFragment2.setAllowEnterTransitionOverlap(true);
                sharedElementFragment2.setAllowReturnTransitionOverlap(true);
                sharedElementFragment2.setSharedElementEnterTransition(change);
                sharedElementFragment2.setSharedElementReturnTransition(change);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, MyFragment3.newInstance(sample, v))
                        .addToBackStack(null)
                        //.addSharedElement(contentView, ViewCompat.getTransitionName(contentView))
                        .commit();
            }
        });
    }
}
