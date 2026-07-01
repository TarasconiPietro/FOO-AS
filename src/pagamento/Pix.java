package pagamento;

public class Pix implements Pagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamendo pix no valor de: R$"+ valor);
    }
}
