package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.SpriteAnimation;

/**
 * Created by kej on 2017-06-19.
 */

public class Fire2 extends SpriteAnimation {
    public Fire2(Bitmap bitmap) {
        super(bitmap);
        InitSpriteData(217,150,10,5);
        this.SetPosition(1500,780);
    }
}
