package command;

import facade.PedidoFacade;
import model.Pedido;

public class CancelarPedido implements Command {
    private PedidoFacade facade;
    private Pedido pedido;

    public CancelarPedido(PedidoFacade facade, Pedido pedido){
        this.facade = facade;
        this.pedido = pedido;
    }
    @Override
    public void executar() {
        facade.atualizarStatus(pedido, "Cancelado");
    }
}
