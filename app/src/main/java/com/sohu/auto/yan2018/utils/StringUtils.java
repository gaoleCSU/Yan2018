package com.sohu.auto.yan2018.utils;

public class StringUtils {
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNull(CharSequence cs) {
        return cs == null || cs.length() == 0 || cs.equals("null");
    }
}
