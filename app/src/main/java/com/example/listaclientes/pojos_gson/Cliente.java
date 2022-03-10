package com.example.listaclientes.pojos_gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cliente {
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("visitado")
    @Expose
    private Boolean visitado;

    public Cliente() {
    }

    public Cliente(String nombre, String telefono, String email, Boolean visitado) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.visitado = visitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(Boolean visitado) {
        this.visitado = visitado;
    }

}
