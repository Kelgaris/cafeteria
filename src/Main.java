public class Main{

    public static void main(String[] args){

        Cliente[] clientes = {
                new Cliente("David"),
                new Cliente("Marta"),
                new Cliente("Mael"),
                new Cliente("Alvaro"),
                new Cliente("Connor"),
                new Cliente("Bibi"),
                new Cliente("Robert"),
                new Cliente("Agustin"),
                new Cliente("Begoña"),
                new Cliente("Francisco"),
                new Cliente("Fito"),
                new Cliente("Dori"),
                new Cliente("Jose"),
                new Cliente("Antonio"),
                new Cliente("Angeles")
        };

        ColaClientes cola = new ColaClientes(clientes);

        Camarero camarero1 = new Camarero("Santi", cola);
        Camarero camarero2 = new Camarero("Jacobo", cola);

        camarero1.start();
        camarero2.start();

        try{
            camarero1.join();
            camarero2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Todos los camareros han terminado su jornada.");
    }
}