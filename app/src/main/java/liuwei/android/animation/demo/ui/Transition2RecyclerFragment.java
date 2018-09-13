package liuwei.android.animation.demo.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lgvalle.material_animations.BR;
import com.lgvalle.material_animations.R;

import java.util.ArrayList;
import java.util.List;

import liuwei.android.animation.demo.ui.base.BaseFragment;
import liuwei.android.animation.demo.UIColor;
import liuwei.android.animation.demo.core.FragmentTransactionCompat;
import liuwei.android.animation.demo.core.TransitionUtils;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 19:33
 */
public class Transition2RecyclerFragment extends BaseFragment
{
    @Override
    protected int getFragmentLayout()
    {
        return R.layout.fragment_recycler_transition;
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

    // ---------------------------------------------------------------------------------------------------------
    // - Adapter
    // ---------------------------------------------------------------------------------------------------------
    private class MyListAdapter extends Adapter<MyHolder>
    {
        private static final int COUNT = 10;

        private List<UIColor> mDataList = new ArrayList<>(COUNT);

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

            for (int i = 0; i < COUNT; i++)
            {
                mDataList.add(new UIColor(String.valueOf(i), mColors[i % 10]));
            }
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            if (viewType % 2 == 0)
            {
                return new MyHolder(DataBindingUtil.inflate(mLayoutInflater, R.layout.adapter_list_item, parent, false));
            }
            else
            {
                return new MyHolder(DataBindingUtil.inflate(mLayoutInflater, R.layout.adapter_list_item_2, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position)
        {
            holder.setData(mDataList.get(position), position);
        }

        @Override
        public int getItemCount()
        {
            return mDataList.size();
        }

        @Override
        public int getItemViewType(int position)
        {
            return position;
        }
    }

    private class MyHolder extends ViewHolder implements OnClickListener
    {
        private ViewDataBinding viewDataBinding;

        MyHolder(ViewDataBinding viewDataBinding)
        {
            super(viewDataBinding.getRoot());

            this.viewDataBinding = viewDataBinding;

            this.viewDataBinding.getRoot().setOnClickListener(this);
        }

        public void setData(UIColor data, int position)
        {
            TransitionUtils.resetTransitionNameForRecyclerView(itemView, position);

            viewDataBinding.setVariable(BR.data, data);

            viewDataBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v)
        {
            FragmentTransactionCompat.getInstance(getFragmentManager())
                    .replaceWithSharedElement(R.id.fragment_content, new DetailFragment(), v, getLayoutPosition())
                    .addToBackStack("DetailFragment")
                    .commit();
        }
    }
}
