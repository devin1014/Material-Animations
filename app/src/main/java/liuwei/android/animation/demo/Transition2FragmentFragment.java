package liuwei.android.animation.demo;

import android.view.View;

import com.lgvalle.material_animations.R;

import butterknife.BindView;
import butterknife.OnClick;
import liuwei.android.animation.demo.core.FragmentTransactionCompat;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 19:31
 */
public class Transition2FragmentFragment extends BaseFragment
{
    @BindView(R.id.image_parent)
    View mImageContainer;
    @BindView(R.id.image_group_parent)
    View mImageGroupContainer;

    @Override
    protected int getFragmentLayout()
    {
        return R.layout.fragment_transition2fragment;
    }

    @OnClick(R.id.btn_share_all_element)
    public void onShareAllElementClick()
    {
        FragmentTransactionCompat.getInstance(getFragmentManager())
                .replaceWithSharedElement(R.id.fragment_content, new DetailFragment())
                .addToBackStack("DetailFragment")
                .commit();
    }

    @OnClick(R.id.image_parent)
    public void onImageParentClick()
    {
        FragmentTransactionCompat.getInstance(getFragmentManager())
                .replaceWithSharedElement(R.id.fragment_content, new DetailFragment(), mImageContainer)
                .addToBackStack("DetailFragment")
                .commit();
    }

    @OnClick(R.id.image_group_parent)
    public void onImageGroupParentClick()
    {
        FragmentTransactionCompat.getInstance(getFragmentManager())
                .replaceWithSharedElement(R.id.fragment_content, new DetailFragment(), mImageGroupContainer)
                .addToBackStack("DetailFragment")
                .commit();
    }
}
