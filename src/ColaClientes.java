public class ColaClientes {
    private Cliente[] clientes;
    private int indice = 0;

    public ColaClientes(Cliente[] clientes){
        this.clientes = clientes;
    }

    public synchronized Cliente siguienteCliente(){
        if(indice < clientes.length){
            Cliente c = clientes[indice];
            indice ++;
            return c;
        }
        return null;
    }
}
