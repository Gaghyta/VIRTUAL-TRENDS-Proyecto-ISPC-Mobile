package com.example.virtualtrendsmovile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
   public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            launchHomeActivity();
            return true;
        } else if (id == R.id.menu_editar_perfil) {
            launchEditarPerfilActivity();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    public void launchHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void launchEditarPerfilActivity() {
        Intent intent = new Intent(this, EditarPerfilActivity.class);
        startActivity(intent);
    }
}