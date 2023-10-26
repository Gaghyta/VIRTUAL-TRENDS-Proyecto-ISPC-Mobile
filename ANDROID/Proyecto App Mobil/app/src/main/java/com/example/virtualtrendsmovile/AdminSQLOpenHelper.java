package com.example.virtualtrendsmovile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLOpenHelper extends SQLiteOpenHelper {

    String sqlCreateTablePersonas = "CREATE TABLE Personas (dni INTEGER PRIMARY KEY, nombre text,direccion text, password text,correo text)";
    public AdminSQLOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateTablePersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Personas");
        db.execSQL(sqlCreateTablePersonas);
    }
    public boolean insertUser(int dni, String nombre, String direccion, String correo, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put("dni", dni);
        datos.put("nombre", nombre);
        datos.put("direccion", direccion);
        datos.put("correo", correo);
        datos.put("password", password);

        long result = db.insert("Personas",null, datos);
        db.close();
        return result != -1;
    }

    public Cursor getUserData(int dni) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {"nombre", "direccion", "correo", "password"};

        // Realiza la consulta para seleccionar los datos del usuario según el dni
        String selection = "dni = ?";
        String[] selectionArgs = {String.valueOf(dni)};

        Cursor cursor = db.query("personas", columns, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public boolean updateUser( int dni, String nombre, String direccion, String correo, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put("nombre", nombre);
        datos.put("direccion", direccion);
        datos.put("correo", correo);
        datos.put("password", password);

        long result = db.update("Personas",datos,"dni="+dni, null);
        if(result ==1){
            return false;
        }else{return true;}
    }

    public boolean deleteUser( int dni) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete("Personas","dni="+dni, null);
        if(result ==1){
            return false;
        }else{return true;}
    }
    public boolean checkUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Personas where correo=?", new String[]{email});
        if(cursor.getCount() > 0){
            return true;
        }else{return false;}
    }
    public boolean checkUserPassword(String usuario, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Personas where usuario=? and password=?", new String[]{usuario, password});
        if(cursor.getCount() > 0){
            return true;
        }else{return false;}
    }

}


