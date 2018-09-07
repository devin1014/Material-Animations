package liuwei.android.animation.demo;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-06
 * Time: 14:54
 */
public class DataBindingAdapterCompat
{
    @BindingAdapter({"color"})
    public static void setColor(ImageView imageView, int color)
    {
        imageView.setBackgroundColor(color);
    }

    @BindingAdapter({"backgroundColor"})
    public static void setBackgroundColor(View view, int color)
    {
        view.setBackgroundColor(color);
    }
}
