import java.util.Random;

public class Cliente extends Thread {
    private String nombre;
    private int tiempoEspera;


    public Cliente(String nombreCliente){
        this.nombre = nombreCliente;
        this.tiempoEspera = (int)(Math.random() * 3000 + 1000);
    }


    public String getNombre() {
        return nombre;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }


}