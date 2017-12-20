package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.Framework.GraphicObject;
import com.example.kej.chickengame.Framework.SpriteAnimation;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-19.
 */

public class BackGround_Score extends SpriteAnimation {
    public BackGround_Score(Bitmap bitmap) {
        super(bitmap);
        this.InitSpriteData(1080,1950,5,4);
    }
}
