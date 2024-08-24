package com.kravchenkovadim.a57lesson_audio;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button playButton;
    SeekBar volumeSeebar;
    AudioManager audioManager;

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


        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volumeSeebar = findViewById(R.id.volumeSeekBar);
        volumeSeebar.setMax(maxVolume);
        volumeSeebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.d("Progress changed: ", "" + progress);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        playButton = findViewById(R.id.playButton);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playButton.getText().equals("Play")) {
                    mediaPlayer.start();
                    playButton.setText("Pause");
                } else if (playButton.getText().equals("Pause")) {
                    mediaPlayer.pause();
                    playButton.setText("Play");
                }
            }
        });
    }
}