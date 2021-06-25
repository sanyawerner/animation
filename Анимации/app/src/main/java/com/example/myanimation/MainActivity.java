package com.example.myanimation;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button;
    private SoundPool mSoundPool;
    private int mSoundCollision=1;
    private int mStreamId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewSoundPool() ;
        Button button = (Button) findViewById(R.id.button);
        mSoundPool.load(this,R.raw.blaster,1);
        button.setOnClickListener(onPlayButtonClickListener);
// Получим ссылку на часы
        //ImageView clockImageView = (ImageView) findViewById(R.id.clock);
// анимация для вращения часов
        Animation clockTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
        //clockImageView.startAnimation(clockTurnAnimation);
        // Получим ссылку на солнце
        ImageView sunImageView = (ImageView) findViewById(R.id.sun);
        // Анимация для восхода солнца
        Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
        // Подключаем анимацию к нужному View
        sunImageView.startAnimation(sunRiseAnimation);
    }
    Button.OnClickListener onPlayButtonClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            mStreamId = mSoundPool.play(mSoundCollision,1,1,1,0,1);
            //Toast.makeText(getApplicationContext(), "soundPool.play()", Toast.LENGTH_LONG).show();
        }
    };
    @TargetApi(Build.VERSION_CODES.LOLLIPOP) //вызов класса SoundPool.Builder
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }
}