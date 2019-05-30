package com.example.projectofindecurso.Model;

public class Vehiculo {
    private String uid;
private String calle;
private String CP;
private String telfContacto;
private String precio;

    public Vehiculo() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getTelfContacto() {
        return telfContacto;
    }

    public void setTelfContacto(String telfContacto) {
        this.telfContacto = telfContacto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return
               "Calle: " + calle + '\n' +
                      "Precio: " + precio;
    }
}
