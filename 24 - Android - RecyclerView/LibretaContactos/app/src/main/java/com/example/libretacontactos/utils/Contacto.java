package com.example.libretacontactos.utils;

public class Contacto {

    String nombres;
    String apellidos;
    String genero;
    String edad;
    String telefono;
    String direccion;
    String correo;
    String foto;

    public Contacto(String nombres, String apellidos, String genero, String edad, String telefono, String direccion, String correo, String foto) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.foto = foto;
    }

    public String toString(){
        return this.nombres+" "+this.apellidos+" - "+this.genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
