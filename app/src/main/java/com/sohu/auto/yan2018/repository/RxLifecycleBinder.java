package com.sohu.auto.yan2018.repository;

import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by legao215985 on 2018/7/30.
 */

public interface RxLifecycleBinder {
    <T> LifecycleTransformer<T> bindLifecycle();
}
