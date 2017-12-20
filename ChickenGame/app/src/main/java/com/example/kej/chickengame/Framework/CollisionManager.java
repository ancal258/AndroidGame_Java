package com.example.kej.chickengame.Framework;

import android.graphics.Rect;

/**
 * Created by kej on 2017-06-17.
 */

public class CollisionManager {
    public static boolean CheckBoxToBox(Rect _rt1, Rect _rt2) {
        if(_rt1.intersect(_rt2))
            return true;
        return false;
    }
}
