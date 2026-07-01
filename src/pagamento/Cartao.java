package pagamento;

public class Cartao implements Pagamento{
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento no cartão no valor de: R$"+ valor);
    }
}
