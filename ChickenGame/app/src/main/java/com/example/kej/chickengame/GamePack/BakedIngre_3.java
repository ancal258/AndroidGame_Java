package com.example.kej.chickengame.GamePack;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-18.
 */

public class BakedIngre_3 extends BakedIngredient {
    public BakedIngre_3(int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.ingre_33));
        this.InitSpriteData(507,360,1,1);
        this.SetPosition(x,y);
    }
}
