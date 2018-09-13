package liuwei.android.animation.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lgvalle.material_animations.R;

import butterknife.BindView;
import liuwei.android.animation.demo.ui.base.BaseActivity;
import liuwei.android.animation.demo.core.FragmentTransactionCompat;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 15:26
 */
public class CustomTransitionActivity extends BaseActivity
{
    @BindView(R.id.transition_group_load)
    RadioGroup mLoadRadioGroup;

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_custom_transition;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initComponent();
    }

    private void initComponent()
    {
        mLoadRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);

        showFragment(new Transition2ActivityFragment());
    }

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            switch (checkedId)
            {
                case R.id.transition_btn_load_activity:

                    showFragment(new Transition2ActivityFragment());

                    break;

                case R.id.transition_btn_load_fragment:

                    showFragment(new Transition2FragmentFragment());

                    break;

                case R.id.transition_btn_load_recycler:

                    showFragment(new Transition2RecyclerFragment());

                    break;
            }
        }
    };

    private void showFragment(Fragment fragment)
    {
        FragmentTransactionCompat.getInstance(this)
                .replaceWithSharedElement(R.id.fragment_content, fragment)
                .commit();
    }
}
