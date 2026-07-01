package command;

import facade.PedidoFacade;
import model.Cliente;
import model.Pedido;

public class CriarPedido implements Command{
    private PedidoFacade facade;
    private Cliente cliente;
    private Pedido pedido;
    private int id;

    public CriarPedido(PedidoFacade facade, Cliente cliente, int id) {
        this.facade = facade;
        this.cliente = cliente;
        this.id = id;
    }

    @Override
    public void executar() {
        pedido = facade.criarPedido(id, cliente);
    }
    public Pedido getPedido(){
        return pedido;
    }
}
