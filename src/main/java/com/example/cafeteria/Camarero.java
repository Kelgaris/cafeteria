package com.example.cafeteria;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Camarero extends Thread {
    private String nombre;
    private static Cliente[] clientes;
    private static int indice = 0;
    private static final Object lock = new Object();
    private VBox contenedorMensajes;
    private Buffer buffer;

    public Camarero(String nombre, Cliente[] listaClientes, VBox contenedorMensajes, Buffer buffer) {
        this.nombre = nombre;
        this.contenedorMensajes = contenedorMensajes;
        this.buffer = buffer;
        if (Camarero.clientes == null) {
            Camarero.clientes = listaClientes;
        }
    }

    private static Cliente siguienteCliente() {
        synchronized (lock) {
            if (indice < clientes.length) {
                Cliente c = clientes[indice];
                indice++;
                return c;
            } else {
                return null;
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        Platform.runLater(() -> {
            Label lbl = new Label(mensaje);
            lbl.getStyleClass().add("mensaje");
            contenedorMensajes.getChildren().add(lbl);
        });
    }

    private void prepararCafe(Cliente cliente) {
        try {
            mostrarMensaje("👤 Cliente " + cliente.getNombre() + " llegó");
            mostrarMensaje("👨‍🍳 Camarero " + nombre + " atiende a " + cliente.getNombre());
            mostrarMensaje("⏳ " + nombre + " esperando un café del barista…");

            String cafe = buffer.consumir();

            mostrarMensaje("☕ " + nombre + " entregó " + cafe + " a " + cliente.getNombre());

        } catch (InterruptedException e) {
            mostrarMensaje("⚠️ " + cliente.getNombre() + " se fue sin ser atendido");
        }
    }

    @Override
    public void run() {
        Cliente cliente;
        while ((cliente = siguienteCliente()) != null) {
            prepararCafe(cliente);
        }
        mostrarMensaje("🏁 " + nombre + " ha terminado su turno");
    }
}
