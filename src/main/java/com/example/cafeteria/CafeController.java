package com.example.cafeteria;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class CafeController {

    @FXML
    private VBox contenedorCamareros;

    @FXML
    public void iniciarSimulacion() {
        contenedorCamareros.getChildren().clear();

        Cliente[] clientes = {
                new Cliente("David"), new Cliente("Marta"),
                new Cliente("Mael"), new Cliente("Alvaro"),
                new Cliente("Connor"), new Cliente("Bibi")
        };


        crearTarjeta("Santi", clientes);
        crearTarjeta("Jacobo", clientes);
    }

    private void crearTarjeta(String nombreCamarero, Cliente[] clientes) {
        VBox tarjeta = new VBox(5);
        tarjeta.getStyleClass().add("tarjeta");

        Label lblNombre = new Label("👨‍🍳 Camarero: " + nombreCamarero);
        lblNombre.getStyleClass().add("nombreCamarero");

        VBox contenedorMensajes = new VBox(3);
        contenedorMensajes.getStyleClass().add("contenedorMensajes");

        tarjeta.getChildren().addAll(lblNombre, contenedorMensajes);
        contenedorCamareros.getChildren().add(tarjeta);


        Camarero camarero = new Camarero(nombreCamarero, clientes, contenedorMensajes);
        camarero.start();
    }




    @FXML
    public void limpiar() {
        contenedorCamareros.getChildren().clear();
    }
}
