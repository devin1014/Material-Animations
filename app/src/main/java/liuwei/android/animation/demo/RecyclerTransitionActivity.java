package liuwei.android.animation.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lgvalle.material_animations.BaseDetailActivity;
import com.lgvalle.material_animations.R;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-06
 * Time: 13:59
 */
public class RecyclerTransitionActivity extends BaseDetailActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_transition);

        setupToolbar();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_content, new RecyclerTransitionFragment())
                .commit();
    }

}
