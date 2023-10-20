package com.example.virtualtrendsmovile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.virtualtrendsmovile.modelos.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "virtual_db";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla
    public static final String TABLE_USERS = "Usuarios";

    // Columnas de la tabla
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DNI = "dni";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";


    // Sentencia SQL para crear la tabla
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_DNI + " TEXT, " +
                    COLUMN_DIRECCION + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT " +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public boolean login(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Usuarios where email = ? and password=?", new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public Usuario checkUser(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Usuarios where email=?",new String[]{email});
        if(cursor.getCount()>0){
            Usuario u = new Usuario();
            cursor.moveToFirst();
            u.setIdUsuario(cursor.getString(0));
            u.setNombreCompleto(cursor.getString(1));
            u.setDni(cursor.getString(2));
            u.setDireccion(cursor.getString(3));
            u.setEmail(cursor.getString(4));
            u.setPassword(cursor.getString(5));
            return u;
        }else{
            return null;
        }
    }
    public Usuario selectUser(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Usuarios where id=?",new String[]{id});
        if(cursor.getCount()>0){
            Usuario u = new Usuario();
            cursor.moveToFirst();
            u.setIdUsuario(cursor.getString(0));
            u.setNombreCompleto(cursor.getString(1));
            u.setDni(cursor.getString(2));
            u.setDireccion(cursor.getString(3));
            u.setEmail(cursor.getString(4));
            u.setPassword(cursor.getString(5));
            return u;
        }else{
            return null;
        }
    }

}
