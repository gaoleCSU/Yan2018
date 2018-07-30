package com.sohu.auto.yan2018.entity;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class BaseEntity implements Serializable {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
