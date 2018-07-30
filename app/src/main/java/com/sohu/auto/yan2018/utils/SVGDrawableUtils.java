package com.sohu.auto.yan2018.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class SVGDrawableUtils {

    /**
     * 因为AppCompat v23.3.0 移除了对StateList的Vector支持,需要自己实现
     *
     * @param context   app context
     * @param idNormal  vector resource for normal state
     * @param idPressed vector resource for pressed/selected/focused states
     * @return
     */
    public static StateListDrawable vectorStateListDrawable(Context context, @DrawableRes int idNormal, @DrawableRes int idPressed) {

        StateListDrawable drawable = new StateListDrawable();

        Drawable pressed = context.getResources().getDrawable(idPressed);
        Drawable normal = context.getResources().getDrawable(idNormal);

        drawable.addState(new int[]{android.R.attr.state_selected}, pressed);
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        drawable.addState(new int[]{android.R.attr.state_enabled}, normal);
        drawable.addState(new int[]{}, normal);
        return drawable;
    }


    /**
     * Unlike the support library app:srcCompat, this will ONLY work with vectors.
     *
     * @param imageView
     * @param resourceId
     */
    public static void setImage(ImageView imageView, int resourceId) {
        Drawable drawable = VectorDrawableCompat.create(imageView.getResources(), resourceId, imageView.getContext().getTheme());
        imageView.setImageDrawable(drawable);
    }

    /**
     * Unlike the support library app:srcCompat, this will ONLY work with vectors.
     *
     * @param textView
     * @param resourceId
     */
    public static void setDrawableRight(TextView textView, int resourceId) {
        Drawable drawable = VectorDrawableCompat.create(textView.getResources(), resourceId, textView.getContext().getTheme());
        Drawable[] drawables = textView.getCompoundDrawables();
        textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0],
                drawables[1], drawable, drawables[3]);
    }
}
