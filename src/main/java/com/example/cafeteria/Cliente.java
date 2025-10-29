package com.example.cafeteria;

public class Cliente {
    private String nombre;
    private int tiempoEspera;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.tiempoEspera = (int) (Math.random() * 3000 + 1000);
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }
}
