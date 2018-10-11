package liuwei.android.animation.demo.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.lgvalle.material_animations.BR;
import com.lgvalle.material_animations.R;

import java.util.ArrayList;
import java.util.List;

import liuwei.android.animation.demo.UIColor;
import liuwei.android.animation.demo.core.FragmentTransactionCompat;
import liuwei.android.animation.demo.ui.base.BaseFragment;

/**
 * User: liuwei
 * Date: 2018-09-10
 * Time: 19:33
 */
public class Transition2RecyclerFragment extends BaseFragment
{
    private String[] mImages = new String[5];

    {
        mImages[0] = "https://ak-static.cms.nba.com/wp-content/uploads/sites/45/2018/09/booker1440.jpg";
        mImages[1] = "https://ak-static.cms.nba.com/wp-content/uploads/sites/45/2018/09/Lakers1440.jpg";
        mImages[2] = "https://ak-static.cms.nba.com/wp-content/uploads/sites/45/2018/09/knox1440.jpg";
        mImages[3] = "https://ak-static.cms.nba.com/wp-content/uploads/sites/45/2018/09/cavss1440.jpg";
        mImages[4] = "https://ak-static.cms.nba.com/wp-content/uploads/sites/45/2018/09/russell1440.jpg";
    }

    @Override
    protected int getFragmentLayout()
    {
        return R.layout.fragment_recycler_transition;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rc_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(new MyListAdapter());
    }

    public String getImage(int position)
    {
        return mImages[position % mImages.length];
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
                mDataList.add(new UIColor(String.valueOf(i), mImages[i % mImages.length], mColors[i % mColors.length]));
            }
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
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
        public void onBindViewHolder(@NonNull MyHolder holder, int position)
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
        private ImageView image;

        MyHolder(ViewDataBinding viewDataBinding)
        {
            super(viewDataBinding.getRoot());

            this.viewDataBinding = viewDataBinding;

            this.viewDataBinding.getRoot().setOnClickListener(this);

            this.image = itemView.findViewById(R.id.image);
        }

        public void setData(UIColor data, int position)
        {
            FragmentTransactionCompat.resetLayoutTransitionName(itemView, position);

            viewDataBinding.setVariable(BR.data, data);

            viewDataBinding.executePendingBindings();

            if (image != null)
            {
                //noinspection ConstantConditions
                Glide.with(getActivity())
                        .load(data.getImage())
                        .into(image);
            }
        }

        @Override
        public void onClick(View v)
        {
            FragmentTransactionCompat.getInstance(getFragmentManager())
                    .replaceWithSharedElement(R.id.fragment_content, DetailFragment.newInstance(getImage(getAdapterPosition())), v, getLayoutPosition())
                    .addToBackStack("DetailFragment")
                    .commit();
        }
    }
}
