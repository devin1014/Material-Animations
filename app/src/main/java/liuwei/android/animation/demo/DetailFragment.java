package liuwei.android.animation.demo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.lgvalle.material_animations.R;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-06
 * Time: 16:04
 */
public class DetailFragment extends Fragment
{
    private static final String KEY_EXTRA_COLOR = "DetailFragment.key.extra.color";

    public static DetailFragment newInstance(UIColor color)
    {
        DetailFragment fragment = new DetailFragment();

        Bundle arguments = new Bundle();

        arguments.putSerializable(KEY_EXTRA_COLOR, color);

        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        UIColor color = (UIColor) getArguments().getSerializable(KEY_EXTRA_COLOR);

        View transitionView = view.findViewById(R.id.list_content);

        View leftImage = view.findViewById(R.id.image_left);

        View rightImage = view.findViewById(R.id.image_right);

        transitionView.setTransitionName(getTransitionName("content", color));

        leftImage.setTransitionName(getTransitionName("leftImage", color));

        rightImage.setTransitionName(getTransitionName("rightImage", color));

        ViewDataBinding viewDataBinding = DataBindingUtil.bind(view);

        viewDataBinding.setVariable(BR.data, color);

        viewDataBinding.executePendingBindings();
    }

    private String TRANSITION_NAME;

    private String getTransitionName(String name, UIColor color)
    {
        if (TRANSITION_NAME == null)
        {
            TRANSITION_NAME = getResources().getString(R.string.transition_recycler);
        }

        return TRANSITION_NAME + name + color.getData();
    }
}
