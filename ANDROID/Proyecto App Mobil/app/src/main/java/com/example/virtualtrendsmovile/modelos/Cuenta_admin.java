package com.example.virtualtrendsmovile.modelos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.actividades.ComprobacionReserva;
import com.example.virtualtrendsmovile.actividades.ContactoActivity;
import com.example.virtualtrendsmovile.actividades.DondeEstamosActivity;
import com.example.virtualtrendsmovile.actividades.InicioActivity;
import com.example.virtualtrendsmovile.actividades.NuestroServicio;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Cuenta_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_admin);
        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id==R.id.back){
                    startActivity(new Intent(getApplicationContext(), ComprobacionReserva.class));
                } else if (id==R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                }else if (id== R.id.map){
                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                } else if (id==R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
                } else if (id==R.id.logout) {
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                }

                return false;
            }

        });

    }

}