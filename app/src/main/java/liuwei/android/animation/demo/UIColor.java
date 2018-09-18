package liuwei.android.animation.demo;

import android.support.annotation.ColorInt;

import java.io.Serializable;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-06
 * Time: 14:30
 */
public class UIColor implements Serializable
{
    private String data;

    private String image;

    @ColorInt
    private int background;

    public UIColor(String data, String image, @ColorInt int background)
    {
        this.data = data;

        this.image = image;

        this.background = background;
    }

    public int getBackground()
    {
        return background;
    }

    public String getData()
    {
        return data;
    }

    public String getImage()
    {
        return image;
    }
}
