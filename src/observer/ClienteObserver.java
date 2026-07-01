package observer;

import model.Cliente;

public class ClienteObserver implements Observer{
    private Cliente cliente;

    public ClienteObserver(Cliente cliente){
        this.cliente = cliente;
    }

    @Override
    public void atualizar(String mensagem){
        System.out.println("Notificação para " + cliente.getNome() + ": " + mensagem);
    }
}
