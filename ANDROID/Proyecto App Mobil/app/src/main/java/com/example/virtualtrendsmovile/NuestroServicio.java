package com.example.virtualtrendsmovile;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class NuestroServicio extends AppCompatActivity {
    //private static final String VIDEO_SAMPLE = "video_woman_scan";

    private VideoView videoView;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestro_servicio);
        videoView=findViewById(R.id.videoView);

        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw"+R.raw.body_scanner_3d_1)); //identificador del video R.raw va a la carpeta

        videoView.start();
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