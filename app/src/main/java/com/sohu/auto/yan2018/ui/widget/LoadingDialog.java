package com.sohu.auto.yan2018.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sohu.auto.yan2018.R;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class LoadingDialog extends Dialog {

    private ImageView ivLoading;

    public LoadingDialog(Context context) {
        super(context, R.style.NLoadingDialog);

        View contentView = LayoutInflater.from(context).inflate(R.layout.view_loading, null);
        ivLoading = (ImageView) contentView.findViewById(R.id.iv_loading);
        setContentView(contentView);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.transparent));
        Glide.with(context)
                .load(R.drawable.anim_news_loading)
                .into(ivLoading);
    }
}
