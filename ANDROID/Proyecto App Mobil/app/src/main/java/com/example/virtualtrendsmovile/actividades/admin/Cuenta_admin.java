package com.example.virtualtrendsmovile.actividades.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.actividades.ComprobacionReserva;
import com.example.virtualtrendsmovile.actividades.ContactoActivity;
import com.example.virtualtrendsmovile.actividades.DondeEstamosActivity;
import com.example.virtualtrendsmovile.actividades.InicioActivity;
import com.example.virtualtrendsmovile.actividades.NuestroServicio;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.example.virtualtrendsmovile.modelos.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Cuenta_admin extends AppCompatActivity {
    Button login;
    EditText email, password, codigo;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_admin);

        login = findViewById(R.id.bt_admin_login);
        email = findViewById(R.id.et_admin_email);
        password = findViewById(R.id.et_admin_password);
        codigo = findViewById(R.id.et_admin_codigo);
        dbHelper = new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adminCod = "qwerty12";
                String em = email.getText().toString();
                String pass = password.getText().toString();
                //check login
                boolean log = dbHelper.login(em, pass);
                Usuario user = dbHelper.checkUser(em);
                if(!log){
                    Toast.makeText(Cuenta_admin.this, "Cuenta no encontrada.", Toast.LENGTH_SHORT).show();
                }else{
                    if(codigo.getText().toString().equals(adminCod)){
                        Toast.makeText(Cuenta_admin.this, "Bienvenido " +user.getNombreCompleto() , Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), Administrar.class));
                        finish();
                    }
                }
            }
        });


    }

}