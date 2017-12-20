package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.kej.chickengame.Framework.SpriteAnimation;

/**
 * Created by kej on 2017-06-11.
 */

public class Player extends SpriteAnimation {

    Rect m_BoundBox = new Rect();
    public int p_GetPositionX;
    public int p_GetPositionY;
    boolean touchOn = false;
    boolean fireOn = false;
    public Player(Bitmap bitmap) {
        super(bitmap);
        this.InitSpriteData(44,700,1,1);
        this.SetPosition(-300,800);
    }

    void Move() {
        if(fireOn == true){
            m_x = 1000;
            m_y = 500;
        }
        else {
            if(m_x > 300)
                m_x = -300;
            m_y = 800;
            if (touchOn == true) {
                m_x += 150;

                if (m_x > 300) {
                    m_x = -300;
                    touchOn = false;
                }
            }
        }
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();
        p_GetPositionX = m_x;
        p_GetPositionY = m_y;
        m_BoundBox.set(m_x+670,m_y,m_x+700,m_y+40);
    }
}
