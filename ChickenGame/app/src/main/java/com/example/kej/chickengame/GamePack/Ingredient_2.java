package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-11.
 */

public class Ingredient_2 extends Ingredient {
    public Ingredient_2() {
        super(AppManager.getInstance().getBitmap(R.drawable.ingre_2));
        this.InitSpriteData(507,360,1,1);
        this.SetPosition(800,0);
        speed = 10f + speedUP;
        localNumber = 2;
        movetype = Ingredient.MOVE_SPEED_3;
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        m_BoundBox.set(m_x,m_y+20,m_x+153,m_y+260);
    }
}
