package com.example.virtualtrendsmovile;

import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NuestroServicio extends AppCompatActivity {

    //private static final String VIDEO_SAMPLE = "body_scanner_3d_1";

    private VideoView videoView;
    //private VideoView videoViewLand;

    private Button playPauseButton;

    private Button stopButton;

    private SeekBar volumeSeekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestro_servicio);

        videoView=findViewById(R.id.videoView);
        //videoViewLand = findViewById(R.id.videoViewLand);
        playPauseButton = findViewById(R.id.playPauseButton);
        stopButton = findViewById(R.id.stopButton);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);

        try {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/"+R.raw.body_scanner_3d_1));
            videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw"+R.raw.body_scanner_3d_1)); /*identificador del video R.raw va a la carpeta*/
        //videoView.start();

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    videoView.seekTo(0);
                    playPauseButton.setText("Play");
                }
            }
        });

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Establecer el volumen del audio
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int volume = (int) (maxVolume * (progress / 100.0f));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Método requerido pero no utilizado en este ejemplo
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Método requerido pero no utilizado en este ejemplo
            }
        });
    }


    }

















    /*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Verifica la orientación del dispositivo
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            ));
            Log.d("MyApp", "Orientación: Horizontal");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            ));
            Log.d("MyApp", "Orientación: Vertical");
        }
    }*/



    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Verifica la orientación del dispositivo
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Orientación horizontal
            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            ));
            videoView.setVisibility(View.GONE); // Oculta el videoView vertical
            videoViewLand.setVisibility(View.VISIBLE); // Muestra el videoView horizontal
            Log.d("MyApp", "Orientación: Horizontal");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Orientación vertical
            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            ));
            videoView.setVisibility(View.VISIBLE); // Muestra el videoView vertical
            videoViewLand.setVisibility(View.GONE); // Oculta el videoView horizontal
            Log.d("MyApp", "Orientación: Vertical");
        }
    }*/


    /*private Uri getMedia(String mediaName){
        return Uri.parse("android.resource://"+getPackageName()+"/raw"+mediaName);
    }*/

    /*private void initializePlayer(){
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    private void releasePlayer(){
        videoView.stopPlayback();
    }

    @Override
    protected void onStart(){
        super.onStart();
        initializePlayer();
    }

    @Override
    protected void onStop(){
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N){
            videoView.pause();
        }
    }



}*/