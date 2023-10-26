package com.example.virtualtrendsmovile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class RegisterActivity extends AppCompatActivity {
    EditText nombres, dni, direccion, correo, password;
    Button enviar;
    AdminSQLOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //asignar a parte grafica
        nombres = findViewById(R.id.et_reg_nombres);
        dni = findViewById(R.id.et_reg_dni);
        direccion = findViewById(R.id.et_reg_direccion);
        correo = findViewById(R.id.et_reg_email);
        password = findViewById(R.id.et_reg_password);
        enviar = findViewById(R.id.btn_reg_enviar);
        //bd
        db= new AdminSQLOpenHelper(this, "administracion", null,1);
        //boton
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.back){
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
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

    private void registrar() {
        String textNombres = nombres.getText().toString();
        String textDni = dni.getText().toString();
        String textDireccion = direccion.getText().toString();
        String textCorreo = correo.getText().toString();
        String textPassword = password.getText().toString();

        if(TextUtils.isEmpty(textNombres) || TextUtils.isEmpty(textDni) ||TextUtils.isEmpty(textDireccion) || TextUtils.isEmpty(textCorreo)
                || TextUtils.isEmpty(textPassword)){
            Toast.makeText(getApplicationContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();

        }else {//end validacion campos nulos
            boolean check = db.checkUser(textCorreo);
            if(check == false) {
                boolean insert = db.insertUser(Integer.parseInt(textDni), textNombres, textDireccion, textCorreo, textPassword);
                if (insert == true) {
                    Toast.makeText(getApplicationContext(), "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show();
                } //usuario ya reg
                else {
                    Toast.makeText(getApplicationContext(), "Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                }
            }//end check
            else{
                Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void launchSelectorActivity(View view) {
        Intent intent = new Intent(this, SelectorActivity.class);
        startActivity(intent);
    }
}