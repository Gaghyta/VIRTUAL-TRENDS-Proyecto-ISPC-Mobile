package com.example.virtualtrendsmovile.modelos;

import java.util.UUID;

public class Usuario {

    // ATRIBUTOS
    private String idUsuario;
    private String nombreCompleto;
    private String dni;
    private String direccion;
    private String email;
    private String password;
    private String userType;
    private String codigo_admin;


    // constructot vacío
    public Usuario() {

    }

    // constructor de objeto que poseee todos los atributos menos el id (a configurar como autoincremental)
    public Usuario(String nombreCompleto, String dni, String direccion, String email, String password,
                   String userType,String codigo_admin) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.userType= userType;
        this.codigo_admin= codigo_admin;
    }

    // GETTERS Y SETTERS

    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }


    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCodigo_admin() {
        return codigo_admin;
    }

    public void setCodigo_admin(String codigo_admin) {
        this.codigo_admin = codigo_admin;
    }
}
