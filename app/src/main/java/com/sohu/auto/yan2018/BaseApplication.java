package com.sohu.auto.yan2018;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.sohu.auto.yan2018.config.DebugConfig;
import com.sohu.auto.yan2018.router.RouterManager;

import java.io.Serializable;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class BaseApplication extends Application implements Serializable {
    private static Application mApplication;

    private static Application getBaseApplication() {
        return mApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //将MultiDex install方法写在此方法内，此方法在onCreate调用之前调用
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initInMainProcess();
    }

    protected void initInMainProcess() {
        // 腾讯Bugly异常统计
        //CrashReport.initCrashReport(getApplicationContext(), "984810cb86", false);
        // 初始化路由
        RouterManager.init(DebugConfig.DEBUG, this);
    }
}
