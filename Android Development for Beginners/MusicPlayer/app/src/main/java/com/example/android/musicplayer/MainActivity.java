package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toast playToast;
    Toast pauseToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.miami_soul);

        playToast = Toast.makeText(this, "Play", Toast.LENGTH_SHORT);
        pauseToast = Toast.makeText(this, "Pause", Toast.LENGTH_SHORT);

        Button playButton = findViewById(R.id.play);
        Button pauseButton = findViewById(R.id.pause);
        Button skipButton = findViewById(R.id.skip);
        Button restartButton = findViewById(R.id.restart);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseToast.cancel();
                playToast.show();

                mediaPlayer.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playToast.cancel();
                pauseToast.show();

                mediaPlayer.pause();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.seekTo(0);
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int songLength = mediaPlayer.getDuration();
                mediaPlayer.seekTo(songLength / 2);
            }
        });
    }
}
