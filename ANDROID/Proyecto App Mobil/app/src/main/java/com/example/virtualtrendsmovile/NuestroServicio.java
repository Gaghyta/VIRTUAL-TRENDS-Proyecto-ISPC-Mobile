package com.example.virtualtrendsmovile;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NuestroServicio extends AppCompatActivity {

    //private static final String VIDEO_SAMPLE = "body_scanner_3d_1";

    private VideoView videoView;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestro_servicio);
        videoView=findViewById(R.id.videoView);

        try {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/"+R.raw.body_scanner_3d_1));
            videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw"+R.raw.body_scanner_3d_1)); /*identificador del video R.raw va a la carpeta*/
        //videoView.start();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Verifica la orientación del dispositivo
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Orientación horizontal
            // Aquí puedes realizar ajustes específicos para la orientación horizontal
            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            ));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Orientación vertical
            // Aquí puedes realizar ajustes específicos para la orientación vertical
            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            ));
        }
    }

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
    }*/



}