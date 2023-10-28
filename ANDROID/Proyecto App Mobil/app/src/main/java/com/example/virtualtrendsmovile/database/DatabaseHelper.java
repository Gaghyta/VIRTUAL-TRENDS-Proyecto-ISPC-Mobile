package com.example.virtualtrendsmovile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.virtualtrendsmovile.modelos.Turno;
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
    public static final String COLUMN_USERTYPE = "usertype";
    public static final String COLUMN_CODIGO_ADMIN = "codigo_admin";

    // Tabla de Contacto
    public static final String TABLE_CONTACT = "Contacto";
    public static final String COLUMN_ID_CONTACTO = "id_contacto";
    public static final String COLUMN_ASUNTO = "asunto";
    public static final String COLUMN_MENSAJE = "mensaje";
    public static final String COLUMN_EMAIL_CONTACT = "email_contacto";

    // tabla de turnero

    public static final String TABLE_TURNERO = "Calendario";
    public static final String COLUMN_ID_CALENDARIO ="id_calendario";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_HORA = "horario";
    public static final String COLUMN_COMPROBANTE = "comprobante";
    public static final String COLUMN_IDUSUARIO = "idUsuario";




    // Sentencia SQL para crear la tabla
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_DNI + " TEXT, " +
                    COLUMN_DIRECCION + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_USERTYPE + " TEXT, " +
                    COLUMN_CODIGO_ADMIN + " TEXT " +
                    ")";

    // Sentencia SQL para crear la tabla de Contacto
    private static final String TABLE_CONTACT_CREATE =
            "CREATE TABLE " + TABLE_CONTACT + " (" +
                    COLUMN_ID_CONTACTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL_CONTACT + " TEXT, " +
                    COLUMN_ASUNTO + " TEXT, " +
                    COLUMN_MENSAJE + " TEXT" +
                    ")";

    // Sentencia SQL para crear la tabla de turnero
    private static final String TABLE_TURNERO_CREATE =
            "CREATE TABLE " + TABLE_TURNERO + " (" +
                    COLUMN_ID_CALENDARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FECHA + " TEXT, " +
                    COLUMN_HORA + " TEXT, " +
                    COLUMN_COMPROBANTE + " TEXT, " +
                    COLUMN_IDUSUARIO + " TEXT " +

                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); // Crear tabla Usuarios
        db.execSQL(TABLE_CONTACT_CREATE); // Crear tabla Mensajes
        db.execSQL(TABLE_TURNERO_CREATE); // Crear tabla Turnero
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TURNERO );
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
    public boolean insertUser(Usuario user){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NOMBRE, user.getNombreCompleto());
        contentValues.put(COLUMN_DNI, user.getDni());
        contentValues.put(COLUMN_DIRECCION, user.getDireccion());
        contentValues.put(COLUMN_EMAIL, user.getEmail());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_USERTYPE, user.getUserType());
        contentValues.put(COLUMN_CODIGO_ADMIN, user.getCodigo_admin());
        db.insert(TABLE_USERS, null, contentValues);
        return true;
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
    public boolean agregarTurno(Turno t){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FECHA, t.getFecha());
        contentValues.put(COLUMN_HORA, t.getFranjaHoraria());
        contentValues.put(COLUMN_COMPROBANTE, t.getComprobante());
        contentValues.put(COLUMN_IDUSUARIO, t.getIdUsuario());
        db.insert(TABLE_TURNERO, null, contentValues);
        return true;
    }

}
