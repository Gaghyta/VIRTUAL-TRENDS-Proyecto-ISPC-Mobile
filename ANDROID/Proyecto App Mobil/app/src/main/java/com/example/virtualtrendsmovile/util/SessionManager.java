package com.example.virtualtrendsmovile.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.virtualtrendsmovile.actividades.admin.GetStartedActivity;

public class SessionManager {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private final String PREFS_SESSION = "session";
    private final String KEY_ID = "key_session_id";
    private final String KEY_NOMBRE = "key_session_nombre";
    private final String KEY_USER_TYPE = "key_session_userType";
    private final String KEY_IS_LOGGED = "key_session_isLogged";

    //Constructor que necesita declarar las pantallas Login-Admin, Login_cliente
    public SessionManager(Context context){
        this.context = context;
        sp= context.getSharedPreferences(PREFS_SESSION,Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    //creacion de session en donde necesita el saber el id, nombre y tipo de usuario para guardar la session
    public void createSession(String id, String nombre, String userType){
        editor.putString(KEY_ID,id);
        editor.putString(KEY_NOMBRE, nombre);
        editor.putString(KEY_USER_TYPE, userType);
        editor.putBoolean(KEY_IS_LOGGED,true);
        editor.commit();
    }
    //para saber si hubo un inicio de sesion previo
    public boolean checkSession(){
        if(sp.contains(KEY_IS_LOGGED)){
            return true;
        }else{
            return false;
        }
    }
    public String getSessionDetails(String key){
        String value = sp.getString(key, null);
        return value;
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, GetStartedActivity.class);
        context.startActivity(intent);
    }
}
