package com.example.virtualtrendsmovile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        //Funcionalidad de BottomNavigation
        BottomNavigationView nav = findViewById(R.id.btnNavSelector);
        nav.setSelected(true);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.back){
                    
                } else if (id==R.id.info) {
                    startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
                }else if (id== R.id.map){
                    
                } else if (id==R.id.turn) {
                    startActivity(new Intent(getApplicationContext(), TurneroActivity.class));
                } else if (id==R.id.logout) {

                }
                return false;
            }
        }); //fin BottomNav
    }

}