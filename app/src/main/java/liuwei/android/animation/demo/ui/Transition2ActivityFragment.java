package liuwei.android.animation.demo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lgvalle.material_animations.R;

import butterknife.BindView;
import butterknife.OnClick;
import liuwei.android.animation.demo.Datas;
import liuwei.android.animation.demo.core.ActivityTransactionCompat;
import liuwei.android.animation.demo.ui.base.BaseFragment;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 18:44
 */
public class Transition2ActivityFragment extends BaseFragment
{
    @BindView(R.id.image_parent)
    View mImageContainer;
    @BindView(R.id.image_group_parent)
    View mImageGroupContainer;
    @BindView(R.id.image)
    ImageView mImageView;

    @Override
    protected int getFragmentLayout()
    {
        return R.layout.fragment_transition2activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //noinspection ConstantConditions
        Glide.with(getActivity())
                .load(Datas.IMAGE_URL_1)
                .into(mImageView);
    }

    @OnClick(R.id.btn_share_all_element)
    public void onShareAllElementClick()
    {
        ActivityTransactionCompat.startActivity(getActivity(), DetailActivity.class);
    }

    @OnClick(R.id.image_parent)
    public void onImageParentClick()
    {
        ActivityTransactionCompat.startActivity(getActivity(), DetailActivity.class, null, mImageContainer);
    }

    @OnClick(R.id.image_group_parent)
    public void onImageGroupParentClick()
    {
        ActivityTransactionCompat.startActivity(getActivity(), DetailActivity.class, null, mImageGroupContainer);
    }
}
