import com.sun.management.UnixOperatingSystemMXBean;

import java.util.Random;

public class Camarero extends Thread{
    private String nombre;
    private ColaClientes cola;


    public Camarero(String nombreCamarero, ColaClientes cola){
        this.nombre = nombreCamarero;
        this.cola = cola;
    }

    private void prepararCafe(Cliente cliente){
        try{
            System.out.println(cliente.getNombre() + " acaba de llegar al restaurante.");
            int tiempoPreparacion = (int)(Math.random() * 3000 + 1000);
            System.out.println("-> " + nombre + " está preparando el café de " + cliente.getNombre());

            Thread.sleep(tiempoPreparacion);

            if(tiempoPreparacion > cliente.getTiempoEspera()){
                System.out.println("# " + cliente.getNombre() + " se cansó de esperar y se ha ido.");
            }else{
                System.out.println("· " + cliente.getNombre() + " ha recibido su café después de " + (tiempoPreparacion/1000) + " segundos");

            }
        } catch (InterruptedException e) {
            System.out.println("* El cliente " + cliente.getNombre() + " se ha ido sin ni siquiera ser antendido.");
        }
    }

    @Override
    public void run(){
        Cliente cliente;
        while((cliente = cola.siguienteCliente()) != null){
            prepararCafe(cliente);
        }
        System.out.println(nombre + " ha terminado de atender a los clientes.");
    }
}
