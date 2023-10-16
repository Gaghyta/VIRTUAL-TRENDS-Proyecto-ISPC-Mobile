/*package com.example.virtualtrendsmovile;

import android.content.ContentValues;
import android.content.Context;
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
       db.execSQL("create table personas(dni int primary key,nombres text,direccion text," +
               "telefono text,password text,correo text)");

   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

   }

}*/


