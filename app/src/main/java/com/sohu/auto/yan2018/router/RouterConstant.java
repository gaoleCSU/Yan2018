package com.sohu.auto.yan2018.router;

/**
 * 路由常数规则：
 * 1、目标页面无请求参数，且无返回参数，则直接写页面Path常数
 * 2、目标页面若有请求参数或者返回参数，则需用接口封装页面所需参数，规则如下：
 * a、页面路径：ACTION
 * b、请求参数：EXTRA_参数值类型_参数名
 * c、返回参数：RESULT_参数值类型_参数名
 * d、接口命名：页面名+Const
 * 注意：页面间参数传递，原则上只支持基本数据类型。
 */

public interface RouterConstant {
    interface MainActivityConst{
        String PATH = "/activity/MainActivity";
    }
}
