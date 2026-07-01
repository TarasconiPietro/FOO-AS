package model;

import observer.Subject;
import strategy.DescontoStrategy;
import strategy.SemDesconto;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends Subject {

    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status;
    private double valorTotal;
    private DescontoStrategy desconto;

    public void adicionarItem(ItemPedido item){
        itens.add(item);
        calcularTotal();
    }

    public void removerItem(ItemPedido item){

        itens.remove(item);
        calcularTotal();

    }

    public void calcularTotal(){
        double total = 0;
        for(ItemPedido item : itens){
            total += item.calcularSubtotal();
        }
        valorTotal = desconto.aplicarDesconto(total);
    }

    public void aplicarDesconto(DescontoStrategy desconto){
        this.desconto = desconto;
        calcularTotal();
    }

    

    public void atualizarStatus(String status){

        this.status.setNome(status);
        notificar("Status atualizado para: " + status);

    }

    public Pedido(int id, Cliente cliente) {

        this.id = id;
        this.cliente = cliente;
        this.status = new StatusPedido("Recebido");
        this.valorTotal = 0;
        this.desconto = new SemDesconto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return (ArrayList<ItemPedido>) itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", itens=" + itens +
                ", status=" + status +
                ", valorTotal=" + valorTotal +
                '}';
    }

}
