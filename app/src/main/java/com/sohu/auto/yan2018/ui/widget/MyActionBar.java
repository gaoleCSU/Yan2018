package com.sohu.auto.yan2018.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sohu.auto.yan2018.R;
import com.sohu.auto.yan2018.utils.StringUtils;

/**
 * desc : 通用actionar    img tv title tv img  形式
 */

public class MyActionBar extends FrameLayout {
    private Context mContext;
    private TextView mTitleTv;
    private TextView mLeftTv;
    private ImageView mLeftIv;
    private CharSequence mTitle;
    private TextView mRightTv;
    private ImageView mRightIv;
    private CharSequence mLeftTx;
    private int mLeftImg;
    private CharSequence mRightTx;
    private int mRightImg;

    private ActionBarListener mListener;
    private boolean mInterruptBackEvent;

    public MyActionBar(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public MyActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.actionbar_common_pinned, this, true);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mLeftTv = (TextView) findViewById(R.id.left_btn_tv);
        mLeftIv = (ImageView) findViewById(R.id.left_btn_iv);
        mRightTv = (TextView) findViewById(R.id.right_btn_tv);
        mRightIv = (ImageView) findViewById(R.id.right_btn_iv);

        if(attrs == null) return;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyActionBar);

        mTitle = ta.getText(R.styleable.MyActionBar_actionbar_title);
        mLeftTx = ta.getText(R.styleable.MyActionBar_actionbar_left_tv);
        mLeftImg = ta.getResourceId(R.styleable.MyActionBar_actionbar_left_iv, R.drawable.v_system_back);
        mRightTx = ta.getText(R.styleable.MyActionBar_actionbar_right_tv);
        mRightImg = ta.getResourceId(R.styleable.MyActionBar_actionbar_right_iv, 0);
        ta.recycle();

        mInterruptBackEvent = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                (int) getResources().getDimension(R.dimen.action_bar_height), MeasureSpec.EXACTLY));
    }

    /**
     * 内容设置结束.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setTitle(mTitle);
        setLeftTx(mLeftTx);
        setRightTx(mRightTx);
        if (TextUtils.isEmpty(mLeftTx)) {
            mLeftIv.setVisibility(View.VISIBLE);
            mLeftIv.setImageResource(mLeftImg);
            if (mContext instanceof Activity) {
                mLeftIv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Activity) mContext).finish();
                    }
                });
            }
        } else {
            mLeftIv.setVisibility(View.GONE);
        }
        setRightImg(mRightImg);
    }

    public void setTitle(CharSequence title) {
        if (null != mTitleTv) {
            mTitle = title;
            mTitleTv.setText(title);
        }
    }

    private void setLeftTx(CharSequence text) {
        if (!StringUtils.isEmpty(text) && mLeftTv != null) {
            mLeftTv.setVisibility(View.VISIBLE);
            mLeftTv.setText(text);
        }
    }

    public void setRightTx(CharSequence text) {
        if (!StringUtils.isEmpty(text) && mRightTv != null) {
            mRightTv.setVisibility(View.VISIBLE);
            mRightTv.setText(text);
        }
    }

    public void setRightImg(int id) {
        if (id != 0 && mRightIv != null) {
            mRightIv.setVisibility(View.VISIBLE);
            mRightIv.setImageResource(id);
        }
    }

    public void setInterruptBackEvent(boolean interrupt) {
        mInterruptBackEvent = interrupt;
    }

    public void setListener(final ActionBarListener listener) {
        if (listener == null) {
            return;
        }
        mListener = listener;

        mRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEvent(ActionBarEvent.RIGHT_TEXT_CLICK);
            }
        });

        mLeftTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEvent(ActionBarEvent.LEFT_TEXT_CLICK);
            }
        });

        mRightIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEvent(ActionBarEvent.RIGHT_IMG_CLICK);
            }
        });

        mLeftIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof Activity && !mInterruptBackEvent) {
                    ((Activity) mContext).onBackPressed();
                } else {
                    listener.onEvent(ActionBarEvent.LEFT_IMG_CLICK);
                }
            }
        });
    }

    public interface ActionBarListener {
        /**
         * 布局点击回调接口
         *
         * @param event 使用ActionBarEvent枚举分类，客户端通过event进行相应回调事件调用
         */
        void onEvent(final ActionBarEvent event);
    }

    public enum ActionBarEvent {
        LEFT_TEXT_CLICK,
        LEFT_IMG_CLICK,
        RIGHT_TEXT_CLICK,
        RIGHT_IMG_CLICK,
    }
}
