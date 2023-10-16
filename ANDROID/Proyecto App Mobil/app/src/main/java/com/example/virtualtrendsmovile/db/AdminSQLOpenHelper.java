package com.example.virtualtrendsmovile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLOpenHelper extends SQLiteOpenHelper {

    public AdminSQLOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table admin3(usuario text primary key,password text)");
        String usuario="admin";
        String password="12345";
        ContentValues datosAdmin= new ContentValues();
        datosAdmin.put("usuario",usuario);
        datosAdmin.put("password",password);
        db.insert("admin3","(usuario,password)",datosAdmin);
        db.execSQL("create table Personas(dni int primary key,nombres text,direccion text," +
                "correo text,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Personas");
    }
    public boolean insertUser(int dni, String nombres, String direccion, String correo, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put("dni", dni);
        datos.put("nombres", nombres);
        datos.put("direccion", direccion);
        datos.put("correo", correo);
        datos.put("password", password);

        long result = db.insert("Personas",null, datos);
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


