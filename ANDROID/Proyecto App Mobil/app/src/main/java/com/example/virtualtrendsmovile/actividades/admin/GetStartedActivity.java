package com.example.virtualtrendsmovile.actividades.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.virtualtrendsmovile.R;

public class GetStartedActivity extends AppCompatActivity {
    Button admin, cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        admin = findViewById(R.id.bt_administrador);
        cliente = findViewById(R.id.bt_cliente);


    }
}