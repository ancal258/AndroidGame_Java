package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.SpriteAnimation;

/**
 * Created by kej on 2017-06-19.
 */

public class Smoke extends SpriteAnimation {
    public Smoke(Bitmap bitmap) {
        super(bitmap);
        this.InitSpriteData(187,379,5,4);
        this.SetPosition(1200,180);
    }
}
