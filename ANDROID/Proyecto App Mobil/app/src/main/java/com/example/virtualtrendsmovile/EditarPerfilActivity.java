package com.example.virtualtrendsmovile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class EditarPerfilActivity extends AppCompatActivity {


    // obtenemos el id (dni) del usuario
    SharedPreferences sharedPreferences = getSharedPreferences("MiPref", MODE_PRIVATE);
    int dni = sharedPreferences.getInt("dni", 0);


    // obtenemos controles
    EditText nombre = findViewById(R.id.input_nombre);
    EditText direccion = findViewById(R.id.input_direccion);
    EditText correo = findViewById(R.id.input_correo);
    EditText password = findViewById(R.id.input_password);
    EditText enviar = findViewById(R.id.btn_confirmar);

    // abrimos bd
    AdminSQLOpenHelper dbp = new AdminSQLOpenHelper(this, "administracion", null, 1);
    SQLiteDatabase db =  dbp.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        // editar datos usuario
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
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

}