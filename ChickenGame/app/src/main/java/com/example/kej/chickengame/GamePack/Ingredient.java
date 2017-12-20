package com.example.kej.chickengame.GamePack;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.kej.chickengame.Framework.GraphicObject;
import com.example.kej.chickengame.Framework.SpriteAnimation;

/**
 * Created by kej on 2017-06-08.
 */

public class Ingredient extends SpriteAnimation {


    public static final int MOVE_SPEED_1 = 0;
    public static final int MOVE_SPEED_2 = 1;
    public static final int MOVE_SPEED_3 = 2;

    public int localNumber;

    public int curIngreType = 0;
    public static int ggotCount = 0;
    public int _ggotCount;

    public int prev_y;

    public  boolean bake = true;
    //public boolean ggotState = false;

    public static final int GGOT_COUNT_1 = 0;
    public static final int GGOT_COUNT_2 = 1;
    public static final int GGOT_COUNT_3 = 2;
    protected float speed ;
    protected int movetype;
    protected int speedUP = 0;
    Rect m_BoundBox = new Rect();

    public boolean ggot = false;
    public int getPlayerX;
    public int getPlayerY;
    public Ingredient(Bitmap bitmap) {
        super(bitmap);
    }


    void Move() {
        if(ggot == false) {
            // 움직이는 로직
            if (movetype == MOVE_SPEED_1) {
                // 스피드 타입 1 = 보통 빠르게
                if (m_y <= 100) {
                    m_y += speed;
                } else {
                    m_y += speed * 2;
                }
            } else if (movetype == MOVE_SPEED_2) {
                // 스피드 타입 2 = 보통
                m_y += speed;
            } else if (movetype == MOVE_SPEED_3) {
                // 스피드 타입 3 = 매우 빠르게
                if (m_y <= 100) {
                    m_y += speed;
                } else {
                    m_y += speed * 4;
                }
            }
        }
        else{
            if(_ggotCount == GGOT_COUNT_1) {
                m_x = getPlayerX + 153;
                m_y = prev_y + getPlayerY - 800;
            }
            else if(_ggotCount == GGOT_COUNT_2) {
                m_x = getPlayerX + 2 * 153;
                m_y = prev_y + getPlayerY - 800;
            }
            else if(_ggotCount == GGOT_COUNT_3) {
                m_x = getPlayerX + 3 * 153;
                m_y = prev_y + getPlayerY - 800;
            }
        }
    }

    @Override
    public void Update(long GameTime) {
        super.Update(GameTime);
        Move();
        if(ggotCount > 2)
            ggotCount = 0;
    }
}
