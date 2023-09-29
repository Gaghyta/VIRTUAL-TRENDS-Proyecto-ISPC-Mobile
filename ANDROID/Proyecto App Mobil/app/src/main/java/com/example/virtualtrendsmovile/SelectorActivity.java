package com.example.virtualtrendsmovile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);


        Button botonIrANuestroServicio = findViewById(R.id.botonIrANuestroServicio);

        botonIrANuestroServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad NuestroServicio cuando se haga clic en el botón.
                Intent intent = new Intent(SelectorActivity.this, NuestroServicio.class);
                startActivity(intent);
            }
        });

    }




}