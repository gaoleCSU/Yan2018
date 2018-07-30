package com.sohu.auto.yan2018.event;

/**
 * Created by legao215985 on 2018/7/30.
 */

public class NetConnectionChangeEvent {
    public final static int UNIDENTIFIED = 1;
    public final static int G2 = 2;
    public final static int G3 = 3;
    public final static int G4 = 4;
    public final static int WIFI = 5;
    public final static int UNKNOWN = 6;
    public final static int DISABLED = 7;
    public final static int NET_UNKNOWN = 8;

    public boolean isAvailable;
    public int networkState;
}
