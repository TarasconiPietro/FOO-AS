package strategy;

public class ComDesconto implements DescontoStrategy{
    @Override
    public double aplicarDesconto(double valor) {

        return valor * 0.90;

    }
}
