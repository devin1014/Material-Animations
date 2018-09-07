package liuwei.android.animation.demo;

import java.io.Serializable;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-06
 * Time: 14:30
 */
public class UIColor implements Serializable
{
    private int background;

    private int left;

    private int right;

    private String data;

    public UIColor(String data, int background, int left, int right)
    {
        this.data = data;

        this.background = background;

        this.left = left;

        this.right = right;
    }

    public int getBackground()
    {
        return background;
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return right;
    }

    public String getData()
    {
        return data;
    }
}
