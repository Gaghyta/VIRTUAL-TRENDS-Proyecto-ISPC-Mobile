package com.example.virtualtrendsmovile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnIngresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });


        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.back) {
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                } else if (id == R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                } else if (id == R.id.map) {
                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                } else if (id == R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
                } else if (id == R.id.logout) {
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                }
                return false;
            }
        });
    }

    public void launchSelectorActivity(View view) {
        Intent intent = new Intent(this, SelectorActivity.class);
        startActivity(intent);
    }

    public void ingresar() {
        try {
            AdminSQLOpenHelper admin = new AdminSQLOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String usuario = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            Cursor fila = bd.rawQuery("Select usuario,password from admin3 where usuario='" + usuario + "' and password='"
                    + password + "'", null);

            if (fila.moveToFirst()) {
                Intent i = new Intent(this,pageUsuarios.class);
                i.putExtra("cedula",usuario);
                startActivity(i);
            }else {
                Cursor fila2 = bd.rawQuery("Select cedula,nombres from personas where cedula='" + usuario + "' and nombres='"
                        + password + "'", null);
                if (fila2.moveToFirst()) {
                    Intent i = new Intent(this, pageUsuarios.class);
                    i.putExtra("cedula",usuario);
                    startActivity(i);
                } else {
                    etPassword.setText("");
                    Toast.makeText(this, "Usuario o contraseña incorrectos Intente de nuevo", Toast.LENGTH_LONG).
                            show();
                    bd.close();
                }
            }}catch(Exception e){
            Toast.makeText(this,"Error en database"+e.toString(),Toast.LENGTH_LONG).show();
        }


    }

}