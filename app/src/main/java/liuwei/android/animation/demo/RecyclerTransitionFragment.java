package liuwei.android.animation.demo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lgvalle.material_animations.R;
import com.lgvalle.material_animations.databinding.AdapterListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-06
 * Time: 15:58
 */
public class RecyclerTransitionFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_recycler_transition, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rc_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(new MyListAdapter());
    }

    private class MyListAdapter extends Adapter<MyHolder>
    {
        private List<UIColor> mDataList = new ArrayList<>(30);

        private LayoutInflater mLayoutInflater = getLayoutInflater();

        private int[] mColors = new int[10];

        MyListAdapter()
        {
            mColors[0] = getResources().getColor(R.color.theme_blue_background);
            mColors[1] = getResources().getColor(R.color.theme_green_background);
            mColors[2] = getResources().getColor(R.color.theme_purple_background);
            mColors[3] = getResources().getColor(R.color.theme_red_background);
            mColors[4] = getResources().getColor(R.color.theme_yellow_background);
            mColors[5] = getResources().getColor(R.color.theme_blue_primary);
            mColors[6] = getResources().getColor(R.color.theme_green_primary);
            mColors[7] = getResources().getColor(R.color.theme_purple_primary);
            mColors[8] = getResources().getColor(R.color.theme_red_primary);
            mColors[9] = getResources().getColor(R.color.theme_yellow_primary);

            Random random = new Random(10);

            for (int i = 0; i < 30; i++)
            {
                int index = random.nextInt(10);

                mDataList.add(new UIColor(String.valueOf(i), mColors[index], mColors[(index + 1) % 10], mColors[(index + 2) % 10]));
            }
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new MyHolder(DataBindingUtil.inflate(mLayoutInflater, R.layout.adapter_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position)
        {
            holder.setData(mDataList.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDataList.size();
        }
    }

    private class MyHolder extends ViewHolder implements OnClickListener
    {
        private ViewDataBinding viewDataBinding;

        private UIColor color;

        MyHolder(ViewDataBinding viewDataBinding)
        {
            super(viewDataBinding.getRoot());

            this.viewDataBinding = viewDataBinding;

            this.viewDataBinding.getRoot().setOnClickListener(this);
        }

        public void setData(UIColor data)
        {
            color = data;

            ((AdapterListItemBinding) viewDataBinding).setData(data);

            viewDataBinding.executePendingBindings();

            itemView.findViewById(R.id.list_content).setTransitionName(getTransitionName("content", data));

            itemView.findViewById(R.id.image_left).setTransitionName(getTransitionName("leftImage", data));

            itemView.findViewById(R.id.image_right).setTransitionName(getTransitionName("rightImage", data));
        }

        @Override
        public void onClick(View v)
        {
            showFragment(v, "content", color);
        }
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

    private void showFragment(View transitionView, String name, UIColor color)
    {
        setExitTransition(new Fade());
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
        transitionSet
                .addTransition(new ChangeBounds())
                .addTransition(new ChangeTransform())
                .addTransition(new ChangeImageTransform());

        DetailFragment fragment = DetailFragment.newInstance(color);
        fragment.setEnterTransition(new Fade());
        fragment.setSharedElementEnterTransition(transitionSet);
        fragment.setSharedElementReturnTransition(transitionSet);
        fragment.setAllowEnterTransitionOverlap(true);
        fragment.setAllowReturnTransitionOverlap(false);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, fragment)
                .addToBackStack(null)
                .addSharedElement(transitionView, getTransitionName(name, color))
                .commit();
    }
}
