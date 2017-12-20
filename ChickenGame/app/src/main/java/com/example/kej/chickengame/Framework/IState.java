package com.example.kej.chickengame.Framework;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by kej on 2017-06-07.
 */

public class IState {
    // 이 상태로 바뀌었을 때 실행할 것들
    public void Init(){}

    // 다른 상태로 바뀔 때 실행할 것들
    public void Destroy(){}

    // 지속적으로 수행할 것들
    public void Update(){}

    // 그려야 할 것들
    public void Render(Canvas canvas){}

    // 키 입력처리
    public boolean onKeyDown(int keyCode, KeyEvent event){
        return true;
    }

    // 터치 입력 처리
    public boolean onTouchEvent(MotionEvent event){
        return true;
    }


}
