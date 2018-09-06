package com.lgvalle.material_animations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.lgvalle.material_animations.databinding.ActivityShareElement2Binding;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 15:15
 */
public class ShareElementActivity2 extends BaseDetailActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        bindData(sample);
        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar();
    }

    private void bindData(Sample sample)
    {
        ActivityShareElement2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_share_element2);
        binding.setSharedSample(sample);
    }

    private void setupWindowAnimations()
    {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(Sample sample)
    {
        // Transition for fragment1
        //        Slide slideTransition = new Slide(Gravity.LEFT);
        //        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        MyFragment1 sharedElementFragment1 = MyFragment1.newInstance(sample);
        //        sharedElementFragment1.setReenterTransition(slideTransition);
        //        sharedElementFragment1.setExitTransition(slideTransition);
        //        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, sharedElementFragment1)
                .commit();
    }
}
