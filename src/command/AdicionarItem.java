package command;

import facade.PedidoFacade;
import model.ItemPedido;
import model.Pedido;

public class AdicionarItem implements Command{
    private PedidoFacade facade;
    private Pedido pedido;
    private ItemPedido item;

    public AdicionarItem(PedidoFacade facade, Pedido pedido, ItemPedido item){
        this.facade = facade;
        this.pedido = pedido;
        this.item = item;
    }
    @Override
    public void executar() {
        facade.adicionarItem(pedido, item);
    }
}
