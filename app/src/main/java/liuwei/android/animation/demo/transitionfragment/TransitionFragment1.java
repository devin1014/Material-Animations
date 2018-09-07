package liuwei.android.animation.demo.transitionfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.lgvalle.material_animations.R;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 15:19
 */
public class TransitionFragment1 extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_transition1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        View sharedView = view.findViewById(R.id.shared_target);

        sharedView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Slide enterTransition = new Slide(Gravity.BOTTOM);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
                ChangeBounds change = new ChangeBounds();
                change.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

                TransitionFragment2 fragment2 = new TransitionFragment2();
                fragment2.setEnterTransition(enterTransition);
                fragment2.setAllowEnterTransitionOverlap(false);
                fragment2.setAllowReturnTransitionOverlap(false);
                fragment2.setSharedElementEnterTransition(change);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, fragment2)
                        .addToBackStack(null)
                        .addSharedElement(v, getString(R.string.transition_image))
                        .commit();
            }
        });
    }
}
