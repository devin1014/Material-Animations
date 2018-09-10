package liuwei.android.animation.demo;

import android.view.View;

import com.lgvalle.material_animations.R;

import butterknife.BindView;
import butterknife.OnClick;
import liuwei.android.animation.demo.core.ActivityTransactionCompat;

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

    @Override
    protected int getFragmentLayout()
    {
        return R.layout.fragment_transition2activity;
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
