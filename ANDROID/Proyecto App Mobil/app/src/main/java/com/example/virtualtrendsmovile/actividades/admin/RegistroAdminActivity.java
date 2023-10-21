package com.example.virtualtrendsmovile.actividades.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.example.virtualtrendsmovile.modelos.Usuario;

public class RegistroAdminActivity extends AppCompatActivity {
    Button register;
    EditText nombre, dni, direccion, email, password, codigo;
    private DatabaseHelper dbHelper;
    private final String TIPO_USUARIO = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_admin);
        //init
        register = findViewById(R.id.bt_admin_regEnviar);
        nombre = findViewById(R.id.et_admin_regNombre);
        dni = findViewById(R.id.et_admin_regDni);
        direccion = findViewById(R.id.et_admin_regDireccion);
        email = findViewById(R.id.et_admin_regEmail);
        password = findViewById(R.id.et_admin_regPassword);
        codigo = findViewById(R.id.et_admin_regCodigo);
        dbHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAdmin = nombre.getText().toString();
                String dniAdmin = dni.getText().toString();
                String dirAdmin = direccion.getText().toString();
                String emailAdmin = email.getText().toString();
                String passAdmin = password.getText().toString();
                String codAdmin = codigo.getText().toString();

                Usuario u = new Usuario(nombreAdmin, dniAdmin, dirAdmin, emailAdmin, passAdmin,TIPO_USUARIO, codAdmin);

                if (TextUtils.isEmpty(nombreAdmin) || TextUtils.isEmpty(dniAdmin) || TextUtils.isEmpty(dirAdmin) ||
                        TextUtils.isEmpty(emailAdmin) || TextUtils.isEmpty(passAdmin) || TextUtils.isEmpty(codAdmin)) {
                    Toast.makeText(getApplicationContext(), "Llena todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Usuario check = dbHelper.checkUser(emailAdmin);
                    if (check == null) {
                        boolean insert = dbHelper.insertUser(u);
                        if (!insert) {
                            Toast.makeText(getApplicationContext(), "Intenta nuevamente", Toast.LENGTH_SHORT).show();
                        }//end ins
                        else {
                            Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Cuenta_admin.class));
                            finish();
                        }
                    } //end check
                    else {
                        Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}