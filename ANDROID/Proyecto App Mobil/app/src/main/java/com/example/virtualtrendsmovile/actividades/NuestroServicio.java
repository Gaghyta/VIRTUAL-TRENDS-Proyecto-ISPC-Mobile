package com.example.virtualtrendsmovile.actividades;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class NuestroServicio extends AppCompatActivity {

    //private static final String VIDEO_SAMPLE = "body_scanner_3d_1";

    SessionManager sessionManager;
    private VideoView videoView;
    //private VideoView videoViewLand;

    private Button playPauseButton;

    private Button stopButton;

    private SeekBar volumeSeekBar;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNav;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //private Class<?> actividadAnterior;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_nuestro_servicio);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.home){
                    startActivity(new Intent(getApplicationContext(), SelectorActivity.class));
                }  else if (id==R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), EditarPerfilActivity.class));
                }  else if (id==R.id.calendar) {
                    startActivity(new Intent(getApplicationContext(), TurnosActivity.class));
                }
                return false;
            }
        });

        videoView = findViewById(R.id.videoView);
        //videoViewLand = findViewById(R.id.videoViewLand);
        playPauseButton = findViewById(R.id.playPauseButton);
        stopButton = findViewById(R.id.stopButton);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);

        try {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.body_scanner_3d_1));
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


        //actividadAnterior = getClass();
        BottomNavigationView nav = findViewById(R.id.bottomNavigationView);
        nav.setSelectedItemId(R.id.turn);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.back) {
                    startActivity(new Intent(getApplicationContext(), SelectorActivity.class));

                    // TODO prueba multi-flow
                    /*if (actividadAnterior == NuestroServicio.class) {
                        // Si la actividad anterior era InicioActivity, no hagas nada o toma alguna acción personalizada
                    } else if (actividadAnterior == InicioActivity.class){
                        startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                    }else if (actividadAnterior == DondeEstamosActivity.class) {
                        startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                    }else if (actividadAnterior == TurnosActivity.class){
                        startActivity(new Intent(getApplicationContext(), TurnosActivity.class));
                    } else if (actividadAnterior == TurneroActivity.class) {
                        startActivity(new Intent(getApplicationContext(), TurneroActivity.class));
                    } else if (actividadAnterior == SelectorActivity.class) {
                        startActivity(new Intent(getApplicationContext(), SelectorActivity.class));
                    }*/

                } else if (id == R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                } else if (id == R.id.map) {
                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                } else if (id == R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
                } else if (id == R.id.logout) {
                    sessionManager.logout();
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                }
                return true;
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