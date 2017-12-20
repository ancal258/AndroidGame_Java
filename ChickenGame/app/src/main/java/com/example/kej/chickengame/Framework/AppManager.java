package com.example.kej.chickengame.Framework;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

/**
 * Created by kej on 2017-06-07.
 */

public class AppManager {
    private static AppManager s_instance;
    public static AppManager getInstance(){
        if(s_instance == null){
            s_instance = new AppManager();
        }
        return s_instance;
    }

    private GameView m_gameview;
    private Resources m_resources;
    private Context   m_context;
    void setGameView(GameView _gameview){
        m_gameview = _gameview;
    }
    void setResources(Resources _resources ){
        m_resources = _resources;
    }
    void setContext(Context context)
    {
        m_context = context;
    }
    public Context getContext()
    {
        return m_context;
    }

    public GameView getGameView(){
        return m_gameview;
    }
    public Resources getResources(){
        return m_resources;
    }

    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(m_resources,r);
    }

}
