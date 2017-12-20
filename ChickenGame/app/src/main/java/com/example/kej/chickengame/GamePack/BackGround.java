package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.Framework.GraphicObject;
import com.example.kej.chickengame.Framework.SpriteAnimation;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-08.
 */

public class BackGround extends SpriteAnimation {
    public BackGround(Bitmap bitmap) {
        super(bitmap);
        this.InitSpriteData(1080,1920,5,6);
        //this.SetPosition(0,0);
    }
}
