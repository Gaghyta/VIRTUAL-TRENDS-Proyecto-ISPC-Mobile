package com.example.virtualtrendsmovile;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
    }

    public void enviar(View view) {

        Snackbar.make(view, "Alert temporal", Snackbar.LENGTH_SHORT).show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ContactoActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        }, 1500);
    }

}
