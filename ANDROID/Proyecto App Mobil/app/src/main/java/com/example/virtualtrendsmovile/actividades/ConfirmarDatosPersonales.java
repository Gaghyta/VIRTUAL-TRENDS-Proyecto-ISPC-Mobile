package com.example.virtualtrendsmovile.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ConfirmarDatosPersonales extends AppCompatActivity {

    SessionManager sessionManager;
    String fecha, horario;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos_personales);

        //intent
        Intent i = getIntent();
        fecha = i.getStringExtra("fecha");
        horario = i.getStringExtra("horario");

        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.back){
                    startActivity(new Intent(getApplicationContext(), HorariosTurnosActivity.class));
                } else if (id==R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                }else if (id== R.id.map){
                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                } else if (id==R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
                } else if (id==R.id.logout) {
                    sessionManager.logout();
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                }

                return false;
            }

        });
    }

    public void confirmar_datos(View view){
        Intent intent = new Intent(this, ComprobacionReserva.class);
        intent.putExtra("fecha", fecha);
        intent.putExtra("horario", horario);
        startActivity(intent);
        finish();
    }
    public void ejecutar_modificar(View view){
        Intent intent = new Intent(this, TurnosActivity.class);
        startActivity(intent);
        finish();
    }

}
