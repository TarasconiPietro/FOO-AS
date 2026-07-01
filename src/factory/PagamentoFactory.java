package factory;

import pagamento.Cartao;
import pagamento.Dinheiro;
import pagamento.Pagamento;
import pagamento.Pix;

public class PagamentoFactory {
    public Pagamento criarPagamento(String tipo) {

        if (tipo.equalsIgnoreCase("pix")) {

            return new Pix();

        }

        if (tipo.equalsIgnoreCase("cartao")) {

            return new Cartao();

        }

        if (tipo.equalsIgnoreCase("dinheiro")) {

            return new Dinheiro();

        }

        throw new IllegalArgumentException(
                "Forma de pagamento inválida"
        );

    }
}
