package com.sohu.auto.yan2018.repository;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class BaseRepository {
    protected RxLifecycleBinder mLifecycleBinder;

    public BaseRepository(RxLifecycleBinder binder) {
        mLifecycleBinder = binder;
    }

    @Deprecated
    public BaseRepository() {
    }

    public <T> Observable.Transformer<T, T> defaultRxConfig() {
        if (null == mLifecycleBinder) {
            return tObservable -> tObservable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        }
        return tObservable -> tObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .compose(mLifecycleBinder.bindLifecycle());
    }
}
