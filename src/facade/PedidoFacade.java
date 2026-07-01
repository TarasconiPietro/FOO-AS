package facade;

import factory.PagamentoFactory;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import pagamento.Pagamento;

public class PedidoFacade {

    private PagamentoFactory pagamentoFactory;

    public PedidoFacade() {

        this.pagamentoFactory = new PagamentoFactory();

    }

    public Pedido criarPedido(int id, Cliente cliente) {

        Pedido pedido = new Pedido(id, cliente);
        return pedido;

    }

    public void adicionarItem(Pedido pedido, ItemPedido item) {

        pedido.adicionarItem(item);

    }

    public void finalizarPedido(Pedido pedido, String formaPagamento) {

        Pagamento pagamento =
                pagamentoFactory.criarPagamento(formaPagamento);
        pagamento.pagar(pedido.getValorTotal());
        pedido.atualizarStatus("Pago");

    }

    public void atualizarStatus(Pedido pedido, String status) {

        pedido.atualizarStatus(status);

    }
}
