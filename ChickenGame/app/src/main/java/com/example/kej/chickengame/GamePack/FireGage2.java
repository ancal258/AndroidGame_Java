package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;
import android.icu.util.GregorianCalendar;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.Framework.SpriteAnimation;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-18.
 */

public class FireGage2 extends SpriteAnimation{
    public boolean fireGage2On = false;

    private  boolean switch1 = false;
    public FireGage2(Bitmap bitmap) {
        super(bitmap);
        this.InitSpriteData(68,188,1,1);
        this.SetPosition(1720,1000);
    }

    void Move() {
        if(fireGage2On == true){
            if(switch1 == false){
                m_y += 1;
            }
            if(switch1 == true){
                m_y -=1;
            }
            if(m_y > 1020)
                switch1 = true;
            if(m_y < 1000)
                switch1 = false;
        }
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();

    }
}
