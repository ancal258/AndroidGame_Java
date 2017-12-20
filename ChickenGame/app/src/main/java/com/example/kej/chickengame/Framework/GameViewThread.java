package com.example.kej.chickengame.Framework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by kej on 2017-06-07.
 */

public class GameViewThread extends Thread {
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameview;
    private boolean m_run = false;

    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameview)
    {
        m_surfaceHolder = surfaceHolder;
        m_gameview = gameview;
    }
    public void setRunning(boolean run) {
        m_run = run;
    }

    @Override
    public void run() {
        Canvas _canvas;
        while(m_run) {
            _canvas = null;
            try{
                m_gameview.Update();
                _canvas = m_surfaceHolder.lockCanvas(null);
                synchronized (m_surfaceHolder) {
                    m_gameview.MyOnDraw(_canvas);
                }
            } finally {
                if(_canvas != null)
                    m_surfaceHolder.unlockCanvasAndPost(_canvas);
            }
        }
    }
}
