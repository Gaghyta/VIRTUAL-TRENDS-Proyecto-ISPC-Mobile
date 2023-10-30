package com.example.virtualtrendsmovile.actividades.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.virtualtrendsmovile.R;

public class AdminUsuarioActivity extends AppCompatActivity {
    TextView txt_admin_atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuario);
        txt_admin_atras= findViewById(R.id.txt_admin_atras);


        txt_admin_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( AdminUsuarioActivity.this, Administrar.class );
                startActivity(intent);
            }
        });
    }

}