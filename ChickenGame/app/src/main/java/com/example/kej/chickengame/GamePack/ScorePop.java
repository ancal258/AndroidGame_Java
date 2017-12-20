package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;

import com.example.kej.chickengame.Framework.SpriteAnimation;

/**
 * Created by kej on 2017-06-19.
 */

public class ScorePop extends SpriteAnimation{

    public int moveUp = 2;
    public int moveCount = 0;
    public ScorePop(Bitmap bitmap) {
        super(bitmap);
    }

    void Move() {
        m_y -= moveUp;
        moveCount += 2;

    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();
    }
}
