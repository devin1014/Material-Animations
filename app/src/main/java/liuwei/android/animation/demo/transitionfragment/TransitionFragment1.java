package liuwei.android.animation.demo.transitionfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
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

        //setExitTransition(new Fade());

        View sharedView = view.findViewById(R.id.shared_target);

        sharedView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TransitionSet transitionSet = new TransitionSet();
                transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
                transitionSet
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTransition(new ChangeImageTransform());

                TransitionFragment2 detailFragment = new TransitionFragment2();
                //detailFragment.setEnterTransition(new Fade());
                detailFragment.setSharedElementEnterTransition(transitionSet);
                detailFragment.setSharedElementReturnTransition(transitionSet);
                detailFragment.setAllowEnterTransitionOverlap(true);
                detailFragment.setAllowReturnTransitionOverlap(true);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, detailFragment)
                        .addToBackStack(null)
                        .addSharedElement(v, getString(R.string.transition_group))
                        .commit();
            }
        });
    }
}
