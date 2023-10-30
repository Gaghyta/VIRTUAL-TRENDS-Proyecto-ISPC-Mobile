package com.example.virtualtrendsmovile.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.virtualtrendsmovile.R;
import com.example.virtualtrendsmovile.database.DatabaseHelper;
import com.example.virtualtrendsmovile.util.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TurneroActivity extends AppCompatActivity {

    private CalendarView calendarView;
    Calendar calendar;
    String fechaTurno;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnero);
        sessionManager = new SessionManager(getApplicationContext());
        //calendario
        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();

        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelectedItemId(R.id.back);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.back){
                    startActivity(new Intent(getApplicationContext(), TurnosActivity.class));
                } else if (id==R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                }else if (id== R.id.map){
                    startActivity(new Intent(getApplicationContext(), DondeEstamosActivity.class));
                } else if (id==R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), NuestroServicio.class));
                } else if (id==R.id.logout) {
                    sessionManager.logout();
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                }
                return false;
            }
        });

        //calendario
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                calendar.set(year, month, day);
                fechaTurno = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
                //Toast.makeText(TurneroActivity.this, fechaTurno, Toast.LENGTH_SHORT).show();
                System.out.println(fechaTurno);
            }
        });

    }
    public void ejecutarTurno(View view){
        /*long fechaSeleccionada = calendarView.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD", Locale.getDefault());
        String fechaFormateada = sdf.format(new Date(fechaSeleccionada));
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FECHA, fechaFormateada);
        long newRowId = db.insert("Calendario", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Su fecha se guardó con exito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al seleccionar fecha", Toast.LENGTH_SHORT).show();
        }

        db.close(); */
        Intent intent = new Intent(getApplicationContext(), HorariosTurnosActivity.class);
        intent.putExtra("fecha", fechaTurno);
        startActivity(intent);
        finish();
    }

    

}