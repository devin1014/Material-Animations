package liuwei.android.animation.demo;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * User: liuwei(wei.liu@neulion.com.com)
 * Date: 2018-09-10
 * Time: 15:46
 */
public class TransitionUtils
{
    // ------------------------------------------------------------------------------------------------------------
    // - Parse view
    // ------------------------------------------------------------------------------------------------------------
    public static List<View> parseTransitionView(@NonNull View rootView)
    {
        List<View> list = new ArrayList<>();

        if (rootView instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup) rootView;

            for (int i = 0; i < viewGroup.getChildCount(); i++)
            {
                list.addAll(parseTransitionView(viewGroup.getChildAt(i)));
            }
        }
        else if (!TextUtils.isEmpty(ViewCompat.getTransitionName(rootView)))
        {
            list.add(rootView);
        }

        return list;
    }

    public static Pair<View, String>[] parseTransitionView2Pair(@NonNull View rootView)
    {
        List<View> list = parseTransitionView(rootView);

        return makeTransitionPair(list);
    }

    @SuppressWarnings("unchecked")
    public static Pair<View, String>[] makeTransitionPair(List<View> views)
    {
        if (views == null || views.size() == 0)
        {
            return null;
        }

        List<Pair<View, String>> list = new ArrayList<>(views.size());

        for (View v : views)
        {
            list.add(Pair.create(v, ViewCompat.getTransitionName(v)));
        }

        return list.toArray(new Pair[list.size()]);
    }

    // ------------------------------------------------------------------------------------------------------------
    // - RecyclerView
    // ------------------------------------------------------------------------------------------------------------
    public static void resetTransitionNameForRecyclerView(View view, int position)
    {
        resetTransitionNameForRecyclerView(view, String.valueOf(position));
    }

    public static void resetTransitionNameForRecyclerView(View view, String suffix)
    {
        if (view instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup) view;

            for (int i = 0; i < viewGroup.getChildCount(); i++)
            {
                View child = viewGroup.getChildAt(i);

                resetTransitionNameForRecyclerView(child, suffix);
            }
        }
        else
        {
            String transitionName = ViewCompat.getTransitionName(view);

            if (!TextUtils.isEmpty(transitionName))
            {
                ViewCompat.setTransitionName(view, transitionName + "#" + suffix);
            }
        }
    }
}
