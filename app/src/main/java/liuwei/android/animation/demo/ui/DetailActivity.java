package liuwei.android.animation.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lgvalle.material_animations.R;

import butterknife.BindView;
import liuwei.android.animation.demo.Datas;
import liuwei.android.animation.demo.core.ActivityTransactionCompat;
import liuwei.android.animation.demo.ui.base.BaseActivity;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 15:39
 */
public class DetailActivity extends BaseActivity
{
    @BindView(R.id.image)
    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        ActivityTransactionCompat.requestActivityContentTransitionFeature(this);

        super.onCreate(savedInstanceState);

        ActivityTransactionCompat.resetActivityLayoutTransitionName(this);

        Glide.with(this)
                .load(Datas.IMAGE_URL_1)
                .into(mImageView);
    }

    @Override
    protected int getActivityLayout()
    {
        return R.layout.activity_detail;
    }
}
