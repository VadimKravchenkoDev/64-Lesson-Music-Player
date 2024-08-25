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

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;

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
        playPauseIcon = findViewById(R.id.playBack);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);

    }

    public void previousTrack(View view) {
    }

    public void nextTrack(View view) {
    }

    public void playTrack(View view) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.pause);
        } else {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.play);
        }
    }
}