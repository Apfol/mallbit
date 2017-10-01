/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mallbit.genero;

/**
 *
 * @author Andres Ramos
 */
public class Genero {
    private int id;
    private String tipo;

    public Genero(int id, String nombre) {
        this.id = id;
        this.tipo = nombre;
    }

    public Genero(String nombre) {
        this.tipo = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String nombre) {
        this.tipo = nombre;
    }
    
    
}
