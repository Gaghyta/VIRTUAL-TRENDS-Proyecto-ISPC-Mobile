package com.example.virtualtrendsmovile.actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.example.virtualtrendsmovile.modelos.Usuario;
import com.example.virtualtrendsmovile.util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnConfirmar;
    SessionManager ss;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etext_Email);
        etPassword = (EditText) findViewById(R.id.etext_Password);
        btnConfirmar = (Button) findViewById(R.id.btnIniciar_sesion);
        ss = new SessionManager(getApplicationContext());
        dbHelper = new DatabaseHelper(getApplicationContext());


        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
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

    final DatabaseHelper helper = new DatabaseHelper(this);

    public void login(){

        SQLiteDatabase db = helper.getReadableDatabase();

        try {

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            Cursor fila = db.rawQuery("Select email,password from Usuarios where email='" + email + "' and password='"
                    + password + "'", null);

            if (fila.moveToFirst()) {

                Intent i = new Intent(this,SelectorActivity.class);
                Usuario u = dbHelper.checkUser(email);
                ss.createSession(u.getIdUsuario(), u.getNombreCompleto(),u.getUserType());
                i.putExtra("email",email);
                startActivity(i);

            }else {

                etPassword.setText("");
                Toast.makeText(this, "Usuario o contraseña incorrectos Intente de nuevo", Toast.LENGTH_LONG).
                        show();
                db.close();
            }
        }catch(Exception e){
            Toast.makeText(this,"Error en database"+e.toString(),Toast.LENGTH_LONG).show();
        }


    }

  /*  public void launchSelectorActivity(View view) {
        Intent intent = new Intent(this, SelectorActivity.class);
        startActivity(intent);
    }*/

}