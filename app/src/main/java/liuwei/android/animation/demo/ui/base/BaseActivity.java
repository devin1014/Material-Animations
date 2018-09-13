package liuwei.android.animation.demo.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lgvalle.material_animations.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 18:56
 */
public abstract class BaseActivity extends AppCompatActivity
{
    private Unbinder mButterKnife;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(getActivityLayout());

        mButterKnife = ButterKnife.bind(this);

        setupToolbar();
    }

    protected abstract int getActivityLayout();

    protected void setupToolbar()
    {
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();

        return true;
    }

    @Override
    protected void onDestroy()
    {
        mButterKnife.unbind();

        super.onDestroy();
    }
}
