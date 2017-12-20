package com.example.kej.chickengame.GamePack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.kej.chickengame.Framework.AppManager;
import com.example.kej.chickengame.Framework.CollisionManager;
import com.example.kej.chickengame.Framework.GameActivity;
import com.example.kej.chickengame.Framework.IState;
import com.example.kej.chickengame.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.GZIPInputStream;

/**
 * Created by kej on 2017-06-08.
 */

public class GameState extends IState {
//임시
    private  int touchX;
    private  int touchY;

    //파일 입출력
    public String saveMaxScore = "0";

    //폰트
    Typeface typeface;

    //SoundPool
    SoundPool sound = new SoundPool(20, AudioManager.STREAM_MUSIC,0);

    private  int score = 0;
    private BackGround m_background;
    private BackGround_2 m_background_2;
    private BackGround_2 m_background_3;
    private BackGround_Score m_background_4;
    private BackGround_UI m_background_5;
    Context context;
    private Smoke m_smoke;
    private Fire m_fire;
    private Fire2 m_fire2;
    private FireGage2 m_firegage2;
    private Player m_player;
    private FireGage m_firegage;

    public static final int STATE_READY = 0;    // 게임 전 준비 상태
    public static final int STATE_PICK = 1;     // 게임 중 - 재료 꽂기
    public static final int STATE_FIRE = 2;     // 게임 중 - 꼬치 굽기
    public static final int STATE_END = 3;      // 게임 종료


    private int m_state = STATE_READY;
    //private int m_x;    //터치 x좌표
    //private int m_y;    //터치 y좌표
    private long startTime;
    private int timer;
    public  int maxScore = 0;

    ArrayList<Ingredient> m_ingrelist = new ArrayList<Ingredient>();
    ArrayList<BakedIngredient> m_bakedlist = new ArrayList<BakedIngredient>();
    ArrayList<ScorePop> m_scorepop = new ArrayList<ScorePop>();

    long LastRegenIngre = System.currentTimeMillis();
    long LastRegenScore = System.currentTimeMillis();

    Random randIngre = new Random();
    Random randScore = new Random();

    int soundID1;
    int soundID2;
    int soundID3;
    int soundID4;
    int soundID5;
    int soundID6;
    int soundID7;
    int soundID8;

    int sound_1;
    int sound_2;
    int sound_3;
    int sound_4;
    int sound_5;
    int sound_6;
    int sound_7;
    int sound_8;


    boolean start = false;
    //나중에 배열로 바꾸자.. 테스트용 일단 ㄱ

    @Override
    public void Init() {
        m_background = new BackGround(AppManager.getInstance().getBitmap(R.drawable.background_animation));
        m_background_2 = new BackGround_2(AppManager.getInstance().getBitmap(R.drawable.background_startani));
        m_background_3 = new BackGround_2(AppManager.getInstance().getBitmap(R.drawable.background_overani));
        m_background_4 = new BackGround_Score(AppManager.getInstance().getBitmap(R.drawable.background_overscore));
        m_background_5 = new BackGround_UI();
        m_smoke = new Smoke(AppManager.getInstance().getBitmap(R.drawable.smoke));
        m_fire = new Fire(AppManager.getInstance().getBitmap(R.drawable.fire));
        m_fire2 = new Fire2(AppManager.getInstance().getBitmap(R.drawable.fire));

        m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.stick));
        m_firegage = new FireGage(AppManager.getInstance().getBitmap(R.drawable.gagebar));
        m_firegage2 = new FireGage2(AppManager.getInstance().getBitmap(R.drawable.touch));


        soundID1 = sound.load(AppManager.getInstance().getContext(),R.raw.ggochi,1);
        soundID2 = sound.load(AppManager.getInstance().getContext(),R.raw.gogigubgi,1);
        soundID3 = sound.load(AppManager.getInstance().getContext(),R.raw.bakgee,1);
        soundID4 = sound.load(AppManager.getInstance().getContext(),R.raw.firehukhuk,1);
        soundID5 = sound.load(AppManager.getInstance().getContext(),R.raw.coinddorong,1);
        soundID6 = sound.load(AppManager.getInstance().getContext(),R.raw.backsound,1);
        soundID7 = sound.load(AppManager.getInstance().getContext(),R.raw.twotwo,1);
        soundID8 = sound.load(AppManager.getInstance().getContext(),R.raw.endback,1);

        typeface = Typeface.createFromAsset(AppManager.getInstance().getContext().getAssets(),"sabo.otf");

        LoadFile();
    }

    @Override
    public void Destroy() {

    }

    public void WriteFile(String str) {

        File file = new File(AppManager.getInstance().getContext().getFilesDir() + "SaveTest.txt");
        FileWriter fw = null;
        BufferedWriter bufwr = null;
        try {
            fw = new FileWriter(file);
            bufwr = new BufferedWriter(fw);

            bufwr.write(str);
            bufwr.flush();
            //Toast.makeText(AppManager.getInstance().getContext(), "저장", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(AppManager.getInstance().getContext(), "저장실패", Toast.LENGTH_SHORT).show();

        }
        try {
            if (bufwr != null)
                bufwr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void LoadFile() {
        File file = new File(AppManager.getInstance().getContext().getFilesDir() + "SaveTest.txt");
        FileReader fr = null;
        BufferedReader bufrd = null;
        String str;


        if (file.exists()) {
            try {
                fr = new FileReader(file);
                bufrd = new BufferedReader(fr);
                str = bufrd.readLine();
                saveMaxScore = str;

                bufrd.close();
                fr.close();
                //Toast.makeText(AppManager.getInstance().getContext() ,str, Toast.LENGTH_SHORT).show();
                //return Integer.parseInt(str);
            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(AppManager.getInstance().getContext(), "로드실패", Toast.LENGTH_SHORT).show();
                //return 0;
            }
        }

        //return 0;
    }

    @Override
    public void Update() {
        if(start == false){
            sound_6 = sound.play(soundID6,1.0F,1.0F,1,-1,1.0F);
            start = true;
        }

        long GameTime = System.currentTimeMillis();

        timer = 150 +((int)(startTime - GameTime)/1000);
        if(timer < 0) {
            if(score > Integer.parseInt(saveMaxScore)) {
                saveMaxScore = Integer.toString(score);

                WriteFile(saveMaxScore);
                //getActivityWrite(saveMaxScore);
                //((GameActivity)GameActivity.mContext).WriteFile(saveMaxScore);
            }
            if(m_state == STATE_PICK) {
                sound_8 = sound.play(soundID8,1.0F,1.0F,1,-1,1.0F);
            }
            if(m_state == STATE_FIRE) {
                sound.stop(sound_2);
                sound_8 = sound.play(soundID8,1.0F,1.0F,1,-1,1.0F);
            }
            m_state = STATE_END;
        }
        if(m_state == STATE_READY){
            m_background_2.Update(GameTime);
        }
        if(m_state == STATE_END){

            m_background_3.Update(GameTime);

        }

        if(m_state == STATE_PICK || m_state == STATE_FIRE) {
            m_background.Update(GameTime);
            m_player.Update(GameTime);
            m_fire.Update(GameTime);
            m_fire2.Update(GameTime);
            m_smoke.Update(GameTime);
            m_firegage.Update(GameTime);
            m_firegage2.Update(GameTime);

            if (m_firegage.gageUp < 200) {
                for (int i = m_ingrelist.size() - 1; i >= 0; i--) {
                    if (m_ingrelist.get(i).ggot == true) {
                        m_ingrelist.get(i).bake = true;
                        int m_x = m_ingrelist.get(i).GetX();
                        int m_y = m_ingrelist.get(i).GetY();
                        if (m_ingrelist.get(i).localNumber == 1) {
                            BakedIngredient _baked = new BakedIngre_1(m_x, m_y);
                            m_bakedlist.add(_baked);
                        } else if (m_ingrelist.get(i).localNumber == 2) {
                            BakedIngredient _baked = new BakedIngre_2(m_x, m_y);
                            m_bakedlist.add(_baked);
                        } else if (m_ingrelist.get(i).localNumber == 3) {
                            BakedIngredient _baked = new BakedIngre_3(m_x, m_y);
                            m_bakedlist.add(_baked);
                        } else if (m_ingrelist.get(i).localNumber == 4) {
                            BakedIngredient _baked = new BakedIngre_4(m_x, m_y);
                            m_bakedlist.add(_baked);
                        }
                        if (m_ingrelist.get(i).localNumber < 3)
                            m_ingrelist.remove(i);
                    }
                }
                m_firegage.gageUp += 3;
            } else {
                m_firegage.gageUp += 2;
            }
            for(int i = m_scorepop.size() - 1; i >= 0 ; i--) {
                if (m_scorepop.get(i).moveCount > 60){
                    m_scorepop.remove(i);
                }
            }

            if (m_firegage.gageUp < 0) {
                sound_7 = sound.play(soundID7,1.0F,1.0F,1,0,1.0F);

                m_firegage.gageUp = 900;
                m_player.fireOn = false;
                m_firegage2.fireGage2On = false;
                m_player.touchOn = false;
                score += 200;
                sound.stop(sound_2);
                MakeScorePop(4,1710,100);
                for (int i = m_ingrelist.size() - 1; i >= 0; i--) {
                    if (m_ingrelist.get(i).ggot == true) {
                        m_ingrelist.remove(i);
                    }
                }
                for (int i = m_bakedlist.size() - 1; i >= 0; i--) {
                    m_bakedlist.remove(i);
                }

                m_state = STATE_PICK;
            }

            for (Ingredient ingre : m_ingrelist) {
                ingre.Update(GameTime);
            }

            for (ScorePop scorePop : m_scorepop) {
                scorePop.Update(GameTime);
            }

            MakeIngredient();
            //MakeScorePop();
            CheckCollision();
        }
    }

    @Override
    public void Render(Canvas canvas) {
        if(m_state == STATE_READY){
            m_background_2.Draw(canvas);
        }

        if(m_state == STATE_END){
            m_background_3.Draw(canvas);
            m_background_4.Draw(canvas);

            Paint p = new Paint();
            p.setTypeface(typeface);
            p.setTextSize(100);
            //p.setShadowLayer(1,1,1,1);
            p.setColor(Color.BLACK);
            //p.setTypeface(AppManager.getInstance().typeface);
            p.setTextAlign(Paint.Align.CENTER);
            //canvas.drawText("현재 점수 : " + score + " / 최고 점수 : " + maxScore, 960, 600, p);
            canvas.drawText(score + " / " + saveMaxScore, 960, 600, p);


            Paint p2 = new Paint();

            p2.setTypeface(typeface);

            p2.setTextSize(100);
           // p2.setTypeface(AppManager.getInstance().typeface);

            //p.setShadowLayer(1,1,1,1);
            p2.setColor(Color.WHITE);
            p2.setTextAlign(Paint.Align.CENTER);
            //canvas.drawText("현재 점수 : " + score + " / 최고 점수 : " + maxScore, 960, 600, p);
            canvas.drawText(score + " / " + saveMaxScore, 955, 595, p2);
        }

        if(m_state == STATE_PICK || m_state == STATE_FIRE) {

            m_background.Draw(canvas);
            m_background_5.Draw(canvas);

            m_player.Draw(canvas);
            m_firegage.Draw(canvas);
            m_firegage2.Draw(canvas);


            for (Ingredient ingre : m_ingrelist) {
                ingre.Draw(canvas);
            }

            for (BakedIngredient Bingre : m_bakedlist) {
                Bingre.Draw(canvas);
            }

            for(ScorePop scorePop : m_scorepop) {
                scorePop.Draw(canvas);
            }

            if(m_player.fireOn == true)
                m_smoke.Draw(canvas);
            m_fire.Draw(canvas);
            m_fire2.Draw(canvas);

            Paint p = new Paint();

            p.setTypeface(typeface);
            //p.setTypeface(AppManager.getInstance().typeface);
            p.setTextSize(60);
            p.setColor(Color.BLACK);
            canvas.drawText("SCORE : " + score, 100, 90, p);

            Paint p1 = new Paint();
            //p1.setTypeface(AppManager.getInstance().typeface);

            p1.setTextSize(60);

            p1.setTypeface(typeface);
            p1.setColor(Color.BLACK);
            canvas.drawText("TIME : " + timer, 100, 150, p1);

            Paint p2 = new Paint();
            //p2.setTypeface(AppManager.getInstance().typeface);

            p2.setTextSize(60);
            p2.setTypeface(typeface);
            p2.setColor(Color.WHITE);
            canvas.drawText("SCORE : " + score, 95, 85, p2);

            Paint p3 = new Paint();
            //p3.setTypeface(AppManager.getInstance().typeface);

            p3.setTextSize(60);
            p3.setTypeface(typeface);
            p3.setColor(Color.WHITE);
            canvas.drawText("TIME : " + timer, 95, 145, p3);
        }
    }

    public void CheckCollision() {
        for(int i=m_ingrelist.size() -1; i>=0;i--) {
            m_ingrelist.get(i).getPlayerX = m_player.p_GetPositionX;
            m_ingrelist.get(i).getPlayerY = m_player.p_GetPositionY;

            if(CollisionManager.CheckBoxToBox(m_player.m_BoundBox,
                    m_ingrelist.get(i).m_BoundBox)) {
                m_ingrelist.get(i).ggot = true;
                m_ingrelist.get(i).prev_y = m_ingrelist.get(i).GetY();
                if(m_ingrelist.get(i).curIngreType <= 2)
                    sound_1 = sound.play(soundID1,1.0F,1.0F,1,0,1.0F);
                else{
                    sound_3 = sound.play(soundID3,1.0F,1.0F,1,0,1.0F);
                }
                MakeScorePop(m_ingrelist.get(i).localNumber - 1,m_ingrelist.get(i).GetX(),m_ingrelist.get(i).GetY() - 20);
                //MakeScorePop(종류,X좌표,Y좌표)
                m_ingrelist.get(i)._ggotCount = m_ingrelist.get(i).ggotCount;
                m_ingrelist.get(i).ggotCount++;
                if(m_ingrelist.get(i).ggotCount == 3){
                    m_player.fireOn = true;
                    m_firegage2.fireGage2On = true;
                    sound_2 = sound.play(soundID2,0.5F,0.5F,1,-1,1.0F);
                    m_state = STATE_FIRE;
                }

                if(m_ingrelist.get(i).curIngreType == 1)
                    score += 50;
                else if(m_ingrelist.get(i).curIngreType == 2)
                    score += 100;
                else if(m_ingrelist.get(i).curIngreType == 3)
                    score -= 150;
                else if(m_ingrelist.get(i).curIngreType == 4)
                    score -= 150;
                return;
            }
        }
    }

    public void MakeScorePop(int _score, int x, int y) {
            int scoretype = _score;
            ScorePop scorepop = null;

            if(scoretype == 0)
                scorepop = new Score50();
            else if(scoretype == 1)
                scorepop = new Score100();
            else if(scoretype == 2)
                scorepop = new Score150();
            else if(scoretype == 3)
                scorepop = new Score150();
            else if(scoretype == 4)
                scorepop = new Score200();

            scorepop.SetPosition(x,y);
            m_scorepop.add(scorepop);
    }

    public void MakeIngredient() {
        if(System.currentTimeMillis() - LastRegenIngre >= 3000) {
            if(m_state == STATE_PICK) {
                LastRegenIngre = System.currentTimeMillis();

                int ingretype = randIngre.nextInt(6);
                Ingredient ingre = null;

                if (ingretype == 0) {
                    ingre = new Ingredient_1();
                    ingre.curIngreType = 1;
                } else if (ingretype == 1) {
                    ingre = new Ingredient_2();
                    ingre.curIngreType = 2;
                } else if (ingretype == 2) {
                    ingre = new Ingredient_3();
                    ingre.curIngreType = 3;
                } else if (ingretype == 3) {
                    ingre = new Ingredient_4();
                    ingre.curIngreType = 4;
                }else if (ingretype == 4){
                    ingre = new Ingredient_1();
                    ingre.curIngreType = 1;
                }else if (ingretype == 5){
                    ingre = new Ingredient_2();
                    ingre.curIngreType = 2;
                }

                ingre.SetPosition(400 + randIngre.nextInt(400), -60);
                ingre.movetype = randIngre.nextInt(3);

                m_ingrelist.add(ingre);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(m_state != STATE_READY && m_state != STATE_END) {
            if (m_state == STATE_PICK) {
                if (m_player.touchOn == false) { // 꽂기 터치
                    m_player.touchOn = true;
                }
            }
            if (m_state == STATE_FIRE) { // 굽기 터치

                m_firegage.gageUp -= 25;
                sound_4 = sound.play(soundID4,1F,1F,1,0,1.0F);

            }
        }
        if(m_state == STATE_READY){
            touchX = (int)event.getX();
            touchY = (int)event.getY();

            Rect boundBox = new Rect(706,682,1195,924);
            if(boundBox.contains(touchX,touchY)){
                startTime = System.currentTimeMillis();
                sound.stop(sound_6);
                sound_5 = sound.play(soundID5,1.0F,1.0F,1,0,1.0F);
                if(score > maxScore) {
                    maxScore = score;
                    saveMaxScore = Integer.toString(maxScore);
                    //getActivityWrite(saveMaxScore);
                }
                m_state = STATE_PICK;

            }
            //event.getX()
        }
        if(m_state == STATE_END){
            touchX = (int)event.getX();
            touchY = (int)event.getY();
            m_firegage.gageUp = 900;
            Rect boundBox = new Rect(706,682,1195,924);
            if(boundBox.contains(touchX,touchY)) {
                sound.stop(sound_8);
                sound_5 = sound.play(soundID5,1.0F,1.0F,1,0,1.0F);
                sound_6 = sound.play(soundID6,1.0F,1.0F,1,-1,1.0F);

                m_player.SetPosition(-300,800);
                m_player.fireOn = false;
                m_player.touchOn = false;

                m_bakedlist.clear();
                for(int i=m_ingrelist.size() -1; i>=0;i--){
                    m_ingrelist.get(i).ggotCount = 0;
                }
                m_ingrelist.clear();
                startTime = System.currentTimeMillis();
                timer = 1000000;
                score = 0;
                m_state = STATE_READY;
            }
        }

        return false;
    }
}
