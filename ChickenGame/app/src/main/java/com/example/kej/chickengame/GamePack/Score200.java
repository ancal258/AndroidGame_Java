package com.example.kej.chickengame.GamePack;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-19.
 */

public class Score200 extends ScorePop {
    public Score200() {
        super(AppManager.getInstance().getBitmap(R.drawable.score_p200));
        InitSpriteData(70,220,1,1);
        this.SetPosition(700,100);
    }
}
