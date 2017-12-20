package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.Framework.GraphicObject;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-19.
 */

public class BackGround_UI extends GraphicObject {
    public BackGround_UI() {
        super(AppManager.getInstance().getBitmap(R.drawable.scoreui));
        this.SetPosition(30,30);
    }
}
