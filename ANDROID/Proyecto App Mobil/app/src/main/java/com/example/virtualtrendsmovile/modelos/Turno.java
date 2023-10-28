package com.example.virtualtrendsmovile.modelos;

public class Turno {
    private String idTurno;
    private String fecha;
    private String franjaHoraria;
    private String comprobante;
    private String idUsuario;

    public Turno(String fecha, String franjaHoraria, String comprobante, String idUsuario) {
        this.fecha = fecha;
        this.franjaHoraria = franjaHoraria;
        this.comprobante = comprobante;
        this.idUsuario = idUsuario;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
