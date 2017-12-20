package com.example.kej.chickengame.GamePack;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-19.
 */

public class Score150 extends ScorePop {
    public Score150() {
        super(AppManager.getInstance().getBitmap(R.drawable.score_m150));
        InitSpriteData(70,220,1,1);
        this.SetPosition(500,100);
    }
}
