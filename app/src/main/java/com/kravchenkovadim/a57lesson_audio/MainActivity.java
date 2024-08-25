package com.kravchenkovadim.a57lesson_audio;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;
    SeekBar seekBar;
    Boolean stateMusic = false;
    int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Устанавливаем заголовок из android:label, указанного в манифесте
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.dark_orange));
        playPauseIcon = findViewById(R.id.playButton);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0, 1000);
    }



    public void playTrack(View view) {
        if (seekBar.getProgress() == mediaPlayer.getDuration()){
            mediaPlayer.seekTo(0);

        }
        if(!stateMusic){
            playPauseIcon.setImageResource(R.drawable.pause);
        }

        if(stateMusic){
            playPauseIcon.setImageResource(R.drawable.play);
        }
        Log.d("mylog", "proverka");
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.play);
            stateMusic = true;
        } else {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.pause);
            stateMusic = false;
        }
    }

    public void skipRightButton(View view) {

        //mediaPlayer.stop();
        //mediaPlayer = MediaPlayer.create(this, R.raw.stuff);
        //seekBar.setMax(mediaPlayer.getDuration());
        //seekBar.setProgress(seekBar.getMax());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.pause();
        seekBar.setProgress(seekBar.getMax());
        if(!stateMusic){
            playPauseIcon.setImageResource(R.drawable.play);
            stateMusic = false;
        }

    }

    public void skipLeftButton(View view) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        seekBar.setProgress(0);
        mediaPlayer = MediaPlayer.create(this, R.raw.stuff);
        if(!stateMusic){
            playPauseIcon.setImageResource(R.drawable.play);
            stateMusic = false;
        }

    }
}