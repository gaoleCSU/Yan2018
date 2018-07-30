package com.sohu.auto.yan2018.router;

import com.alibaba.android.arouter.facade.Postcard;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class Poster {
    private Postcard mPostcard;

    Poster(Postcard postcard){
        mPostcard = postcard;
    }

    public Postcard addFlag(int... flags){
        int addingFlag = mPostcard.getFlags();
        for (int temp : flags){
            addingFlag |= temp;
        }
        return mPostcard.withFlags(addingFlag);
    }
}
