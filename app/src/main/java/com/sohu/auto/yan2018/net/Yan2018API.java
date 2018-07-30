package com.sohu.auto.yan2018.net;

import com.sohu.auto.yan2018.config.DebugConfig;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class Yan2018API {
    private static class InstanceHolder {
        static Api INSTANCE = ServiceFactory.createService(DebugConfig.ENDPOINT, Api.class);
    }

    public interface Api {
        //
    }

    public static Api getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
