package com.example.virtualtrendsmovile.actividades.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.example.virtualtrendsmovile.modelos.Usuario;
import com.example.virtualtrendsmovile.util.SessionManager;

public class CuentaAdmin extends AppCompatActivity {
    Button login;
    EditText email, password, codigo;
    private DatabaseHelper dbHelper;
    SessionManager sessionManager;

    TextView txtAdminRegistro = findViewById(R.id.txt_admin_registro);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_admin);

        login = findViewById(R.id.bt_admin_login);
        email = findViewById(R.id.et_admin_email);
        password = findViewById(R.id.et_admin_password);
        codigo = findViewById(R.id.et_admin_codigo);
        dbHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pass = password.getText().toString();
                //check login
                boolean log = dbHelper.login(em, pass);
                Usuario user = dbHelper.checkUser(em);
                if(!log){
                    Toast.makeText(CuentaAdmin.this, "Cuenta no encontrada.", Toast.LENGTH_SHORT).show();
                }else{
                    if(codigo.getText().toString().equals(user.getCodigo_admin())){
                        Toast.makeText(CuentaAdmin.this, "Bienvenido " +user.getNombreCompleto() , Toast.LENGTH_SHORT).show();
                        sessionManager.createSession(user.getIdUsuario(), user.getNombreCompleto(), user.getUserType());
                        startActivity(new Intent(getApplicationContext(), Administrar.class));
                        finish();
                    }else {
                        Toast.makeText(CuentaAdmin.this, "Codigo Invalido.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /*reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CuentaAdmin.this, RegistroAdminActivity.class));
            }
        });*/

        //NUEVO MÉTODO PARA ACCEDER A LA PANTALLA DE REGISTRO DESDE EL TEXTO "¿No tenés una cuenta? Registráte"
        txtAdminRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí puedes abrir la pantalla de registro de administradores
                Intent intent = new Intent(CuentaAdmin.this, RegistroAdminActivity.class);
                startActivity(intent);
            }
        });



    }

}