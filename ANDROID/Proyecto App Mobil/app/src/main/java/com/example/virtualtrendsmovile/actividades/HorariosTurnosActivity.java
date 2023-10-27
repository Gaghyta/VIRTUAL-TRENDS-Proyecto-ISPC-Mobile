package com.example.virtualtrendsmovile.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.virtualtrendsmovile.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HorariosTurnosActivity extends AppCompatActivity {
    String fecha;
    Button btMañana, btmediodia, bttarde, btNoche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_turnos);
        intent
        Intent i = getIntent();
        fecha = i.getStringExtra("fecha");

        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.back){
                    startActivity(new Intent(getApplicationContext(), TurneroActivity.class));
                } else if (id==R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                }else if (id== R.id.map){

                } else if (id==R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), HorariosTurnosActivity.class));
                } else if (id==R.id.logout) {

                }
                return false;
            }
        });

    }

    public void ejecutar_horario(View view){
        Intent intent = new Intent(this, ConfirmarDatosPersonales.class);
        startActivity(intent);
        finish();
    }




}
