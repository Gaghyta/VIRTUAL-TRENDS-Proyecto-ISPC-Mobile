package com.example.virtualtrendsmovile.actividades.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.MenuItem;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.actividades.ContactoActivity;
import com.example.virtualtrendsmovile.actividades.DondeEstamosActivity;
import com.example.virtualtrendsmovile.actividades.InicioActivity;
import com.example.virtualtrendsmovile.actividades.NuestroServicio;
import com.example.virtualtrendsmovile.actividades.TurnosActivity;
import com.example.virtualtrendsmovile.util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Administrar extends AppCompatActivity {
    AlertDialog.Builder builder;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);
        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelectedItemId(R.id.back);
        sessionManager = new SessionManager(getApplicationContext());


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.back){
                    startActivity(new Intent(getApplicationContext(), TurnosActivity.class));
                } else if (id==R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                }else if (id== R.id.map){
                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                } else if (id==R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
                } else if (id==R.id.logout) {
                    builder = new AlertDialog.Builder(Administrar.this);
                    builder.setMessage("¿Desea cerrar sesión?");
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sessionManager.logout();
                            startActivity(new Intent(getApplicationContext(), GetStartedActivity.class));
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                   AlertDialog alertDialog = builder.create();
                   alertDialog.show();
                }//end logout
                return false;
            }
        });

    }
}