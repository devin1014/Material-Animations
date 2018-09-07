package liuwei.android.animation.demo.transitionfragment;

import android.os.Bundle;

import com.lgvalle.material_animations.BaseDetailActivity;
import com.lgvalle.material_animations.R;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-04
 * Time: 15:15
 */
public class TransitionFragmentActivity extends BaseDetailActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transition_fragment);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, new TransitionFragment1())
                .commit();
    }
}
