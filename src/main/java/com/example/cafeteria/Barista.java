package com.example.cafeteria;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class Barista extends  Thread{

    private Buffer buffer;
    private boolean activo = true;
    private VBox contenedorMensajes;

    public Barista(Buffer buffer, VBox contenedorMensajes){
        this.buffer = buffer;
        this.contenedorMensajes = contenedorMensajes;
    }

    public void detener(){
        activo = false;
        interrupt();
    }

    private void mostrarMensaje(String msg){
        Platform.runLater(() ->{
            Label lbl = new Label(msg);
            lbl.getStyleClass().add("mensaje");
            contenedorMensajes.getChildren().add(lbl);
        });
    }

    @Override
    public void run(){
        int contador = 1;

        try{
            while (activo){
                String cafe = "Cafe #"+contador++;
                buffer.producir(cafe);
                mostrarMensaje("☕ Barista produjo → " + cafe);

                Thread.sleep((int)(Math.random()*2000 + 1000));

                mostrarMensaje("🛌 Barista esperando… (buffer lleno)");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
