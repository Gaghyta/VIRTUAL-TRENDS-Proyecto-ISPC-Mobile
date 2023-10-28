package com.example.virtualtrendsmovile.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.example.virtualtrendsmovile.modelos.Usuario;
import com.example.virtualtrendsmovile.util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EditarPerfilActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private String userId;
    private DatabaseHelper dbHelper;

    private EditText nombre;
    private EditText direccion;
    private EditText correo;
    private EditText password;
    private Button enviar;
    private Button eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        dbHelper = new DatabaseHelper(this);

        sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.checkSession()) {
            userId = sessionManager.getSessionDetails("key_session_id");
            loadUserData(userId);
        }




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
                    startActivity(new Intent(getApplicationContext(), SelectorActivity.class));
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

    private void loadUserData(String userId) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Realiza una consulta a la base de datos para obtener los datos del usuario
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios WHERE id = ?", new String[]{userId});

        if (cursor.moveToFirst()) {

            Log.d("CursorData", DatabaseUtils.dumpCursorToString(cursor));

            // Obtiene los valores del cursor
            @SuppressLint("Range") String nombreUsuario = cursor.getString(cursor.getColumnIndex("nombre"));
            @SuppressLint("Range") String direccionUsuario = cursor.getString(cursor.getColumnIndex("direccion"));
            @SuppressLint("Range") String correoUsuario = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String passwordUsuario = cursor.getString(cursor.getColumnIndex("password"));


            // Initialize EditText fields
            nombre = findViewById(R.id.input_nombre);
            direccion = findViewById(R.id.input_direccion);
            correo = findViewById(R.id.input_correo);
            password = findViewById(R.id.input_password);
            enviar = findViewById(R.id.btn_confirmar);
            eliminar = findViewById(R.id.btn_eliminar);

            // Establece los valores en los campos EditText
            nombre.setHint(nombreUsuario);
            direccion.setHint(direccionUsuario);
            correo.setHint(correoUsuario);
            password.setHint(passwordUsuario);
        }

        cursor.close();
    }


    private void updateUser() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String textNombre = nombre.getText().toString();
        String textDireccion = direccion.getText().toString();
        String textCorreo = correo.getText().toString();
        String textPassword = password.getText().toString();

        if(TextUtils.isEmpty(textNombre)  ||TextUtils.isEmpty(textDireccion) || TextUtils.isEmpty(textCorreo)
                || TextUtils.isEmpty(textPassword)){
            Toast.makeText(getApplicationContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();

        }else {
            if (db != null) {
                Usuario check = dbHelper.checkUser(textCorreo);
                if (check == null) {
                    boolean update = dbHelper.updateUser(userId,textNombre, textDireccion, textCorreo, textPassword);
                    if (update == true) {
                        Toast.makeText(getApplicationContext(), "Usuario actualizado correctamente.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this,SelectorActivity.class);
                        startActivity(i);
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

            SQLiteDatabase db = dbHelper.getReadableDatabase();

            if (db != null) {
                    boolean delete = dbHelper.deleteUser(userId);
                    if (delete == true) {
                        Toast.makeText(getApplicationContext(), "Cuenta eliminada correctamente.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, InicioActivity.class);
                        sessionManager.logout();
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }

