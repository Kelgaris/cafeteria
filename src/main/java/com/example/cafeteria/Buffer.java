package com.example.cafeteria;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private final int capacidad;
    private final Queue<String> buffer = new LinkedList<>();

    public Buffer(int capacidad){
        this.capacidad = capacidad;
    }

    public synchronized void producir(String cafe) throws InterruptedException{
        while(buffer.size() == capacidad){
            wait();
        }
        buffer.add(cafe);
        notifyAll();
    }

    public synchronized String consumir() throws InterruptedException{
        while(buffer.isEmpty()){
            wait();
        }
        String cafe = buffer.poll();
        notifyAll();
        return cafe;
    }
}
