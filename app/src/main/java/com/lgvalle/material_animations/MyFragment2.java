package com.lgvalle.material_animations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 15:26
 */
public class MyFragment2 extends Fragment
{
    private static final String EXTRA_SAMPLE = "sample";

    public static MyFragment2 newInstance(Sample sample)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, sample);
        MyFragment2 fragment = new MyFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);
        ImageView squareBlue = view.findViewById(R.id.image);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.color);
    }
}
