package com.example.kej.chickengame.Framework;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.kej.chickengame.GamePack.GameState;
import com.example.kej.chickengame.R;

/**
 * Created by kej on 2017-06-07.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameViewThread m_thread;
    private IState m_state;
    private GraphicObject m_Image;
    private SpriteAnimation m_spr;

    //@Override
    protected void MyOnDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        m_state.Render(canvas);
        //m_Image.Draw(canvas);
        //m_spr.Draw(canvas);
    }

    public GameView(Context context) {
        super(context);
        setFocusable(true);

        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());
        AppManager.getInstance().setContext(context);

        getHolder().addCallback(this);

        ChangeGameState(new GameState());
        m_thread = new GameViewThread(getHolder(),this);
        //m_Image = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.front_blue));
        //m_spr = new SpriteAnimation(BitmapFactory.decodeResource(getResources(),R.drawable.explosion));
        //m_spr.InitSpriteData(104,66,10,6); // 보여야 할 이미지 세로크기, 보여야 할 이미지 가로크기, 속도(FPS), n컷
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_thread.setRunning(true);
        m_thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        m_thread.setRunning(false);
        while(retry) {
            try {
                m_thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void Update() {
        //m_state.Update();
        long GameTime = System.currentTimeMillis();
        m_state.Update();
        //m_spr.Update(GameTime);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return m_state.onKeyDown(keyCode,event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return m_state.onTouchEvent(event);
    }

    public void ChangeGameState(IState _state){
        if(m_state != null)
            m_state.Destroy();
        _state.Init();
        m_state = _state;
    }
}
