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

    public Camarero(String nombre, Cliente[] listaClientes, VBox contenedorMensajes) {
        this.nombre = nombre;
        this.contenedorMensajes = contenedorMensajes;
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
            int tiempoPreparacion = (int) (Math.random() * 4000 + 1000);

            mostrarMensaje("Cliente: " + cliente.getNombre() + " llegó al restaurante");
            mostrarMensaje("Camarero " + nombre + " atiende a " + cliente.getNombre());

            Thread.sleep(tiempoPreparacion);

            if (tiempoPreparacion > cliente.getTiempoEspera()) {
                mostrarMensaje("😞 " + cliente.getNombre() + " se fue sin esperar");
            } else {
                mostrarMensaje("✅ " + cliente.getNombre() + " recibió su café en " +
                        (tiempoPreparacion / 1000.0) + " segundos");
            }

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
