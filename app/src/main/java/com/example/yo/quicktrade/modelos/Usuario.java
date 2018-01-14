package com.example.yo.quicktrade.modelos;

/**
 * Created by yo on 14/01/2018.
 */

public class Usuario {
    private String user, nombre, direccion, apellidos;

    public Usuario() {

    }

    public Usuario(String user, String nombre, String direccion, String apellidos) {
        this.user = user;
        this.nombre = nombre;
        this.direccion = direccion;
        this.apellidos = apellidos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
