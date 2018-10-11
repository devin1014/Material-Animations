package liuwei.android.animation.demo.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestFutureTarget;
import com.lgvalle.material_animations.R;

import butterknife.BindView;
import liuwei.android.animation.demo.ui.base.BaseFragment;

/**
 * User: liuwei
 * Date: 2018-09-11
 * Time: 11:58
 */
public class DetailFragment extends BaseFragment
{
    private static final String KEY_EXTRA_IMAGE = "DetailFragment.key.extra.image";

    public static DetailFragment newInstance(String imageUrl)
    {
        DetailFragment fragment = new DetailFragment();

        Bundle bundle = new Bundle();

        bundle.putString(KEY_EXTRA_IMAGE, imageUrl);

        fragment.setArguments(bundle);

        return fragment;
    }

    @BindView(R.id.image)
    ImageView mImageView;

    @Override
    protected int getFragmentLayout()
    {
        return R.layout.fragment_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        String imageUrl = getArguments().getString(KEY_EXTRA_IMAGE);

        if (!TextUtils.isEmpty(imageUrl))
        {
            Glide.with(this)
                    .load(imageUrl)
                    .listener(new RequestFutureTarget<Drawable>(new Handler(), 400, 400)
                    {
                    })
                    .into(mImageView);
        }
    }
}
