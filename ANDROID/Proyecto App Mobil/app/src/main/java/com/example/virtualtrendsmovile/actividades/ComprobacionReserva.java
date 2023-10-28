package com.example.virtualtrendsmovile.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.example.virtualtrendsmovile.modelos.Turno;
import com.example.virtualtrendsmovile.util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ComprobacionReserva extends AppCompatActivity {

    SessionManager sessionManager;
    String fecha, horario, comprobante;
    EditText etComprobante;
    private DatabaseHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobacion_reserva);
        etComprobante = findViewById(R.id.et_turno_comprobante);
        //intent
        Intent i = getIntent();
        fecha = i.getStringExtra("fecha");
        horario = i.getStringExtra("horario");
        //bd
        dbHelper = new DatabaseHelper(this);
        comprobante = etComprobante.getText().toString();

        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.back) {
                    startActivity(new Intent(getApplicationContext(), ConfirmarDatosPersonales.class));
                } else if (id == R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                } else if (id == R.id.map) {
                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                } else if (id == R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
                } else if (id == R.id.logout) {
                    sessionManager.logout();
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                }

                return false;
            }

        });

    }
    public void comprobacion_reserva(View view){
        Intent intent = new Intent(this, ConfirmacionFinal.class);
        //guardar en bd el turno completo
        SessionManager ss = new SessionManager(this);
        String id = ss.getSessionDetails("key_session_id");
        Turno turno =  new Turno(fecha, horario,comprobante, id);
        boolean b = dbHelper.agregarTurno(turno);
        if(!b){
            Toast.makeText(getApplicationContext(), "Intenta nuevamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Turno creado con exito", Toast.LENGTH_SHORT).show();
            intent.putExtra("fecha", fecha);
            intent.putExtra("horario", horario);
        }
        startActivity(intent);
        finish();
    }
}
