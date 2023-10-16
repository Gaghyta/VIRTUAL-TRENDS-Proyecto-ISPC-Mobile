package com.example.virtualtrendsmovile.modelos;

public class Usuario {

    // ATRIBUTOS
    private String idUsuario;
    private String nombreCompleto;
    private String dni;
    private String direccion;
    private String email;
    private String password;


    // constructot vacío
    public Usuario() {

    }

    // constructor de objeto que poseee todos los atributos menos el id (a configurar como autoincremental)
    public Usuario(String nombreCompleto, String dni, String direccion, String email, String password) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
    }

    // GETTERS Y SETTERS
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
}
