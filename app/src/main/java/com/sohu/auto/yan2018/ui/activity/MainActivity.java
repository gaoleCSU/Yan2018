package com.sohu.auto.yan2018.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sohu.auto.yan2018.R;
import com.sohu.auto.yan2018.router.RouterConstant;
import com.sohu.auto.yan2018.router.RouterManager;
import com.sohu.auto.yan2018.ui.fragment.MainFragment;
import com.sohu.auto.yan2018.ui.fragment.MeFragment;
import com.sohu.auto.yan2018.utils.SVGDrawableUtils;
import com.sohu.auto.yan2018.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by legao215985 on 2018/7/30.
 */
@Route(path = RouterConstant.MainActivityConst.PATH)
public class MainActivity extends BaseActivity {
    FragmentTabHost mTabHost;
    private static final int TAB1 = 0;
    private static final int TAB2 = 1;
    private static final int TAB3 = 2;
    private static final int TAB4 = 3;
    @DrawableRes
    private int[] mTabIconNormal;
    @DrawableRes
    private int[] mTabIconSelected;
    private String[] mFragmentTags;
    private Class[] mFragments;
    private String sCurrentTab;
    private TabWidget mTabWidget;
    // 第一次按下返回键的事件
    private long firstPressedTime;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onInitView() {
        EventBus.getDefault().register(this);
        mTabIconNormal = new int[]{
                R.mipmap.home_me_normal,
                R.mipmap.home_me_normal,
                R.mipmap.home_me_normal,
                R.mipmap.home_me_normal
        };

        mTabIconSelected = new int[]{
                R.mipmap.home_me_selected,
                R.mipmap.home_me_selected,
                R.mipmap.home_me_selected,
                R.mipmap.home_me_selected
        };

        mFragmentTags = new String[]{
                getString(R.string.tab_1),
                getString(R.string.tab_2),
                getString(R.string.tab_3),
                getString(R.string.tab_4)
        };
        mFragments = new Class[]{
                MeFragment.class,
                MeFragment.class,
                MeFragment.class,
                MeFragment.class
        };

        sCurrentTab = mFragmentTags[0];
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null); // 去掉分割线
        mTabWidget = mTabHost.getTabWidget();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTabHost.setElevation(8);
        }

        for (int i = 0; i < mFragmentTags.length; i++) {
            // Tab按钮添加文字和图片
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mFragmentTags[i]).setIndicator(getTabIndicatorView(i));
            // 添加Fragment
            mTabHost.addTab(tabSpec, mFragments[i], null);
        }
        setTabWidgetClickListener();
    }

    /**
     * 设置tab的点击事件
     */
    private void setTabWidgetClickListener() {
        mTabWidget.getChildTabViewAt(TAB1).setOnClickListener(v -> {
            sCurrentTab = mFragmentTags[TAB1];
            mTabHost.setCurrentTabByTag(mFragmentTags[TAB1]);
        });
        mTabWidget.getChildTabViewAt(TAB2).setOnClickListener(v -> {
            sCurrentTab = mFragmentTags[TAB2];
            mTabHost.setCurrentTabByTag(mFragmentTags[TAB2]);
        });
        mTabWidget.getChildTabViewAt(TAB3).setOnClickListener(v -> {
            sCurrentTab = mFragmentTags[TAB3];
            mTabHost.setCurrentTabByTag(mFragmentTags[TAB3]);
        });
        mTabWidget.getChildTabViewAt(TAB4).setOnClickListener(v -> {
            sCurrentTab = mFragmentTags[TAB4];
            mTabHost.setCurrentTabByTag(mFragmentTags[TAB4]);
        });
    }

    @Override
    protected void initConfigBeforeSetContentView() {
        super.initConfigBeforeSetContentView();
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    private View getTabIndicatorView(int index) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv_image);
        imageView.setImageDrawable(SVGDrawableUtils.vectorStateListDrawable(imageView.getContext(), mTabIconNormal[index], mTabIconSelected[index]));
        TextView tvTabText = (TextView) view.findViewById(R.id.tv_tab_main_activity_text);
        tvTabText.setText(mFragmentTags[index]);
        return view;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstPressedTime < 2000) {
            super.onBackPressed();
        } else {
            ToastUtils.show(MainActivity.this, "再按一次退出");
            firstPressedTime = System.currentTimeMillis();
        }
    }
}
