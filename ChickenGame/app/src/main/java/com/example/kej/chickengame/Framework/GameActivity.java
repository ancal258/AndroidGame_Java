package com.example.kej.chickengame.Framework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kej.chickengame.GamePack.GameState;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        //WriteFile("0");
        //LoadFile();
        setContentView(new GameView(this));
    }


    public void WriteFile(String str) {
        File file = new File(getFilesDir() + "SaveTest.txt");
        FileWriter fw = null;
        BufferedWriter bufwr = null;
        try {
            fw = new FileWriter(file);
            bufwr = new BufferedWriter(fw);

            bufwr.write(str);
            bufwr.flush();
            Toast.makeText(this, "저장", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "저장실패", Toast.LENGTH_SHORT).show();

        }
        try {
            if (bufwr != null)
                bufwr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void LoadFile(){
        File file = new File(getFilesDir() + "SaveTest.txt");
        FileReader fr = null;
        BufferedReader bufrd = null;
        String str;

        if(file.exists()){
            try{
                fr = new FileReader(file);
                bufrd = new BufferedReader(fr);
                str = bufrd.readLine();
                //saveMaxScore = str;

                bufrd.close();
                fr.close();
                Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
                //return Integer.parseInt(str);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "로드실패", Toast.LENGTH_SHORT).show();
                //return 0;
            }
        }
        //return 0;
    }


}

