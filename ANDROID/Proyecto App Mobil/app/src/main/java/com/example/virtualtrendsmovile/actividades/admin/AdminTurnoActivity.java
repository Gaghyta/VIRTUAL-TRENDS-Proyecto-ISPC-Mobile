package com.example.virtualtrendsmovile.actividades.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.util.SessionManager;

public class AdminTurnoActivity extends AppCompatActivity {
    TextView txtAdminLogout;
    TextView txt_turno_atras;
    SessionManager s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_turno);
        txtAdminLogout = findViewById(R.id.txt_admin_logout);
        txt_turno_atras= findViewById(R.id.txt_turno_atras);
        s = new SessionManager(getApplicationContext());

        txtAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.logout();
                Intent intent = new Intent(AdminTurnoActivity.this, GetStartedActivity.class);
                startActivity(intent);
            }
        });

        txt_turno_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( AdminTurnoActivity.this, Administrar.class );
                startActivity(intent);
            }
        });

    }};
//fin del codigo


