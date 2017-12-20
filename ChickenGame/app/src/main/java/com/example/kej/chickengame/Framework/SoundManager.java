package com.example.kej.chickengame.Framework;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by kej on 2017-06-07.
 */

public class SoundManager {
    private SoundPool m_SoundPool;
    private HashMap m_SoundPoolMap;
    private AudioManager m_AudioManager;
    private Context m_Activity;

    public void Init(Context _context) {
        m_SoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        m_SoundPoolMap = new HashMap();
        m_AudioManager = (AudioManager)_context.getSystemService(
                Context.AUDIO_SERVICE);
        m_Activity = _context;
    }

    public void addSound(int _index, int _soundID) {
        int id = m_SoundPool.load(m_Activity, _soundID, 1);
        m_SoundPoolMap.put(_index,id);
    }

    public void play(int _index) {
        // 사운드 재생
        float streamVolume = m_AudioManager.getStreamVolume(
                AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume
                (AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer) m_SoundPoolMap.get(_index),
                streamVolume, streamVolume, 1,-1, 1f);
    }

    public void playLooped(int _index) {
        // 사운드 반복 재생
        float streamVolume = m_AudioManager.getStreamVolume(
                AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume(
                AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer) m_SoundPoolMap.get(_index),
                streamVolume, streamVolume, 1, -1, 1f);
   }

   // 싱글턴 패턴 적용
    private  static SoundManager s_instance;
    public static SoundManager getInstance() {
        if(s_instance == null) {
            s_instance = new SoundManager();
        }
        return s_instance;
    }

}
