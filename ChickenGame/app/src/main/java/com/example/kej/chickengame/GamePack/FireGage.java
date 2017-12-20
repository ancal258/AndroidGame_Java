package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.SpriteAnimation;

/**
 * Created by kej on 2017-06-17.
 */

public class FireGage extends SpriteAnimation {

    public int gageUp = 900;

    public FireGage(Bitmap bitmap) {
        super(bitmap);
        this.InitSpriteData(115,80,1,1);
        this.SetPosition(1780,900);
    }

    void Move() {
        if(gageUp >= 900) {
            gageUp = 900;
        }
        else if(gageUp < 900){
            m_y = gageUp;
        }
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();

    }
}
