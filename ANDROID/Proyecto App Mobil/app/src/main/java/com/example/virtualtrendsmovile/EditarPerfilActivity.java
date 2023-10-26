package com.example.virtualtrendsmovile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EditarPerfilActivity extends AppCompatActivity {



    private SharedPreferences sharedPreferences;
    int dni;

    private EditText nombre;
    private EditText direccion;
    private EditText correo;
    private EditText password;
    private Button enviar;
    private Button eliminar;

    private AdminSQLOpenHelper dbp;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        // obtenemos el id (dni) del usuario
        sharedPreferences = getSharedPreferences("MiPref", MODE_PRIVATE);
        dni = sharedPreferences.getInt("dni", 0);

        // Initialize EditText fields
        nombre = findViewById(R.id.input_nombre);
        direccion = findViewById(R.id.input_direccion);
        correo = findViewById(R.id.input_correo);
        password = findViewById(R.id.input_password);
        enviar = findViewById(R.id.btn_confirmar);
        eliminar = findViewById(R.id.btn_eliminar);

        // Initialize the database helper and database
        dbp = new AdminSQLOpenHelper(this, "administracion", null, 1);
        db = dbp.getWritableDatabase();

        loadUserData(dni);





        // editar datos usuario
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });

        BottomNavigationView nav = findViewById(R.id.btnNavEditar);
        nav.setSelected(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.back){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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

    private void loadUserData(int dni) {
        // Realiza una consulta a la base de datos para obtener los datos del usuario
        Cursor cursor = dbp.getUserData(dni); // Debes implementar el método getUserData en tu AdminSQLOpenHelper

        if (cursor.moveToFirst()) {

            Log.d("CursorData", DatabaseUtils.dumpCursorToString(cursor));

            // Obtiene los valores del cursor
            @SuppressLint("Range") String nombreUsuario = cursor.getString(cursor.getColumnIndex("nombre"));
            @SuppressLint("Range") String direccionUsuario = cursor.getString(cursor.getColumnIndex("direccion"));
            @SuppressLint("Range") String correoUsuario = cursor.getString(cursor.getColumnIndex("correo"));
            @SuppressLint("Range") String passwordUsuario = cursor.getString(cursor.getColumnIndex("password"));

            // Establece los valores en los campos EditText
            nombre.setText(nombreUsuario);
            direccion.setText(direccionUsuario);
            correo.setText(correoUsuario);
            password.setText(passwordUsuario);
        }

        cursor.close();
    }


    private void updateUser() {
        String textNombre = nombre.getText().toString();
        String textDireccion = direccion.getText().toString();
        String textCorreo = correo.getText().toString();
        String textPassword = password.getText().toString();

        if(TextUtils.isEmpty(textNombre)  ||TextUtils.isEmpty(textDireccion) || TextUtils.isEmpty(textCorreo)
                || TextUtils.isEmpty(textPassword)){
            Toast.makeText(getApplicationContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();

        }else {
            if (db != null) {
                boolean check = dbp.checkUser(textCorreo);
                if (check == false) {
                    boolean update = dbp.updateUser(dni,textNombre, textDireccion, textCorreo, textPassword);
                    if (update == true) {
                        Toast.makeText(getApplicationContext(), "Usuario actualizado correctamente.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                    }
                }//end check
                else {
                    Toast.makeText(getApplicationContext(), "Ya existe un usuario con ese correo, intenta con otro", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void deleteUser() {


            if (db != null) {
                    boolean delete = dbp.deleteUser(dni);
                    if (delete == true) {
                        Toast.makeText(getApplicationContext(), "Cuenta eliminada correctamente.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, InicioActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }

