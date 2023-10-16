package com.example.virtualtrendsmovile.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    EditText editTextNombreCompleto;
    EditText editTextDNI;
    EditText editTextDireccion;
    EditText editTextEmailAddress;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);
        dbHelper = new DatabaseHelper(this);

        editTextNombreCompleto = findViewById(R.id.editTextNombreCompleto);
        editTextDNI = findViewById(R.id.editTextDNI);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);

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

   /* public void launchSelectorActivity(View view) {
        Intent intent = new Intent(this, SelectorActivity.class);
        startActivity(intent);
    }*/

    // AREA PARA GUARDAR DATOS EN LA BASE

    // ...
    public void launchSelectorActivity(View view) {

        // Obtén los datos del formulario
        String nombreCompleto = editTextNombreCompleto.getText().toString();
        String dni = editTextDNI.getText().toString();
        String direccion = editTextDireccion.getText().toString();
        String email = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();


        // Inserta los datos en la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, nombreCompleto);
        values.put(DatabaseHelper.COLUMN_DNI, dni);
        values.put(DatabaseHelper.COLUMN_DIRECCION, direccion);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        long newRowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        if (newRowId != -1) {
            // El método toast nos permite dar un mensaje de Éxito, en este caso: Usuario registrado con éxito
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
            // Aquí puedes iniciar la siguiente actividad o realizar otras acciones
        } else {
            // En esta opción hay un Error al insertar datos
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }




}