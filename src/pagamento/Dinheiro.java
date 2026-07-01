package pagamento;

public class Dinheiro implements Pagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento em dinheiro no valor de: R$"+ valor);
    }
}
