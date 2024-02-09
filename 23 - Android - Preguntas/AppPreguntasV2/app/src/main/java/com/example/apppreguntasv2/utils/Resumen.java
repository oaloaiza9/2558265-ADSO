package com.example.apppreguntasv2.utils;

public class Resumen {

    String id;
    String fecha_inicio;
    String fecha_fin;
    String cant_preguntas;
    String cant_ok;
    String cant_error;

    public Resumen(String id, String fecha_inicio, String fecha_fin, String cant_preguntas, String cant_ok, String cant_error) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.cant_preguntas = cant_preguntas;
        this.cant_ok = cant_ok;
        this.cant_error = cant_error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getCant_preguntas() {
        return cant_preguntas;
    }

    public void setCant_preguntas(String cant_preguntas) {
        this.cant_preguntas = cant_preguntas;
    }

    public String getCant_ok() {
        return cant_ok;
    }

    public void setCant_ok(String cant_ok) {
        this.cant_ok = cant_ok;
    }

    public String getCant_error() {
        return cant_error;
    }

    public void setCant_error(String cant_error) {
        this.cant_error = cant_error;
    }
}
