package com.example.virtualtrendsmovile.actividades.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.actividades.NuestroServicio;
import com.example.virtualtrendsmovile.actividades.SelectorActivity;
import com.example.virtualtrendsmovile.util.SessionManager;

public class Administrar extends AppCompatActivity implements View.OnClickListener {
    AlertDialog.Builder builder;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);

        Button bt_admin_usuario= findViewById(R.id.bt_admin_usuario);
        bt_admin_usuario.setOnClickListener(this);
        Button bt_admin_turno= findViewById(R.id.bt_admin_turno);
        bt_admin_turno.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_admin_usuario) {
                Intent intent = new Intent(Administrar.this, AdminUsuarioActivity.class);
                startActivity(intent);
        } else if (view.getId() == R.id.bt_admin_turno) {
            Intent intent = new Intent(Administrar.this, AdminTurnoActivity.class);
            startActivity(intent);

    }
}}

//        bt_admin_usuario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Administrar.this, UsuariosAdmin.class);
//                startActivity(intent);
//            }
//        });
//        bt_admin_turno.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Administrar.this, TurnosAdmin.class);
//                startActivity(intent);
//            }
//        });
//            Button bt_admin_turno= findViewById(R.id.bt_admin_turno);
//            bt_admin_turno.setOnClickListener(new View.OnClickListener()
//            public void turno_admin (View view){
//                Intent intent = new Intent(Administrar.this, TurnosAdmin.class);
//                startActivity(intent);
//                finish();
//            }







       // BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        //nav.setSelectedItemId(R.id.back);



        //nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if(id==R.id.back){
//                    startActivity(new Intent(getApplicationContext(), TurnosActivity.class));
//                } else if (id==R.id.info) {
//                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
//                }else if (id== R.id.map){
//                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
//                } else if (id==R.id.turn) {
//                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
//                } else if (id==R.id.logout) {
//                    builder = new AlertDialog.Builder(Administrar.this);
//                    builder.setMessage("¿Desea cerrar sesión?");
//                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            sessionManager.logout();
//                            startActivity(new Intent(getApplicationContext(), GetStartedActivity.class));
//                        }
//                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//                   AlertDialog alertDialog = builder.create();
//                   alertDialog.show();
//                }//end logout
//                return false;
//            }
//        });

