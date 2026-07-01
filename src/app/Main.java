package app;

import command.AdicionarItem;
import command.CancelarPedido;
import command.Command;
import command.CriarPedido;
import facade.PedidoFacade;
import model.*;
import observer.ClienteObserver;
import strategy.ComDesconto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    Scanner entrada = new Scanner(System.in);


    public static void main(String[] args) {

        Main app = new Main();
        app.menu();

    }

    void menu() {

        PedidoFacade facade = new PedidoFacade();

        ArrayList<Pedido> pedidos = new ArrayList<>();

        int proximoIdProduto = 1;
        int proximoIdPedido = 1;

        int opcao;

        do {
            System.out.println("----------Menu----------");
            System.out.println("1 - Criar pedido");
            System.out.println("2 - Adicionar item ao pedido");
            System.out.println("3 - Aplicar promoção");
            System.out.println("4 - Finalizar pagamento");
            System.out.println("5 - Atualizar status");
            System.out.println("6 - Mostrar pedidos em aberto");
            System.out.println("7 - Cancelar ou deletar pedido");
            System.out.println("0 - Sair");

            opcao = Integer.parseInt(ler("Escolha: "));

            switch(opcao){
                case 1:

                    String nome = ler("Nome do cliente: ");
                    String cpf = ler("Cpf: ");

                    String logradouro = ler("Digite o logradouro: ");
                    int numero = Integer.parseInt(ler("Digite o numero: "));
                    String bairro = ler("Digite o bairro: ");
                    String cidade = ler("Digite a cidade: ");
                    String descricao = ler("Digite a descrição: ");

                    Cliente cliente = new Cliente(nome, cpf);
                    Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, descricao);
                    cliente.getEnderecos().add(endereco);

                    String nomeProduto = ler("Nome do produto: ");
                    double preco = Double.parseDouble(ler("Preço: "));
                    Produto produto = new Produto(proximoIdProduto++, nomeProduto, "Produto sushi", preco);

                    CriarPedido criarPedido =
                            new CriarPedido(facade, cliente, proximoIdPedido++);
                    criarPedido.executar();
                    Pedido pedido = criarPedido.getPedido();

                    pedido.adicionarObserver(new ClienteObserver(cliente));

                    ItemPedido item =
                            new ItemPedido(produto, 1);
                    Command adicionarItem =
                            new AdicionarItem(facade, pedido, item);
                    adicionarItem.executar();

                    pedidos.add(pedido);

                    System.out.println("Pedido criado com sucesso!");
                    break;

                case 2:

                    Pedido pedidoAdicionar = selecionarPedidoEmAberto(pedidos);

                    if(pedidoAdicionar == null){
                        break;
                    }

                    String nomeProdutoItem = ler("Nome do produto: ");
                    double precoItem = Double.parseDouble(ler("Preço: "));
                    Produto produtoItem = new Produto(proximoIdProduto++, nomeProdutoItem, "Produto sushi", precoItem);

                    ItemPedido novoItem =
                            new ItemPedido(produtoItem, 1);

                    Command commandAdicionar =
                            new AdicionarItem(facade, pedidoAdicionar, novoItem);
                    commandAdicionar.executar();

                    System.out.println("Item adicionado!");
                    break;

                case 3:

                    Pedido pedidoPromocao = selecionarPedidoEmAberto(pedidos);

                    if(pedidoPromocao == null){
                        break;
                    }

                    pedidoPromocao.aplicarDesconto(new ComDesconto());
                    System.out.println("Promoção aplicada!");
                    break;

                case 4:

                    Pedido pedidoPagamento = selecionarPedidoEmAberto(pedidos);

                    if(pedidoPagamento == null){
                        break;
                    }

                    String pagamento =
                            ler("Pagamento (pix/cartao/dinheiro): ");
                    facade.finalizarPedido(pedidoPagamento, pagamento);
                    break;

                case 5:

                    Pedido pedidoStatus = selecionarPedidoEmAberto(pedidos);

                    if(pedidoStatus == null){
                        break;
                    }

                    String status = ler("Novo status: ");
                    pedidoStatus.atualizarStatus(status);
                    break;

                case 6:

                    mostrarPedidosEmAberto(pedidos);
                    break;

                case 7:

                    Pedido pedidoCancelar = selecionarPedidoEmAberto(pedidos);

                    if(pedidoCancelar == null){
                        break;
                    }

                    System.out.println("1 - Cancelar pedido");
                    System.out.println("2 - Deletar pedido");
                    int escolhaCancelar = Integer.parseInt(ler("Escolha: "));

                    if(escolhaCancelar == 1){

                        Command cancelarPedido =
                                new CancelarPedido(facade, pedidoCancelar);
                        cancelarPedido.executar();

                        System.out.println("Pedido cancelado!");

                    } else if(escolhaCancelar == 2){

                        pedidos.remove(pedidoCancelar);
                        System.out.println("Pedido deletado!");

                    } else {

                        System.out.println("Opção inválida!");

                    }

                    break;

                case 0:

                    System.out.println("Sistema encerrado!");
                    break;

                default:

                    System.out.println("Opção inválida!");

            }


        } while(opcao != 0);


    }

    String ler(String mensagem){

        System.out.print(mensagem);
        return entrada.nextLine();

    }

    boolean pedidoEmAberto(Pedido pedido){

        String status = pedido.getStatus().getNome();

        return !status.equalsIgnoreCase("Pago")
                && !status.equalsIgnoreCase("Cancelado");

    }

    ArrayList<Pedido> filtrarPedidosEmAberto(ArrayList<Pedido> pedidos){

        ArrayList<Pedido> emAberto = new ArrayList<>();

        for(Pedido pedido : pedidos){

            if(pedidoEmAberto(pedido)){
                emAberto.add(pedido);
            }
        }

        return emAberto;

    }

    Pedido selecionarPedidoEmAberto(ArrayList<Pedido> pedidos){

        ArrayList<Pedido> emAberto = filtrarPedidosEmAberto(pedidos);

        if(emAberto.isEmpty()){
            System.out.println("Nenhum pedido em aberto!");
            return null;
        }

        System.out.println("----------Pedidos em Aberto----------");

        for(int i = 0; i < emAberto.size(); i++){

            Pedido pedido = emAberto.get(i);
            System.out.println((i + 1) + " - Pedido #" + pedido.getId()
                    + " | Cliente: " + pedido.getCliente().getNome()
                    + " | Status: " + pedido.getStatus().getNome()
                    + " | Total: R$ " + pedido.getValorTotal());

        }

        int indice = Integer.parseInt(ler("Selecione o pedido: ")) - 1;

        if(indice < 0 || indice >= emAberto.size()){
            System.out.println("Pedido inválido!");
            return null;
        }

        return emAberto.get(indice);

    }

    void mostrarPedidosEmAberto(ArrayList<Pedido> pedidos){

        ArrayList<Pedido> emAberto = filtrarPedidosEmAberto(pedidos);

        if(emAberto.isEmpty()){
            System.out.println("Nenhum pedido em aberto!");
            return;
        }

        System.out.println("========== Pedidos em Aberto ==========");

        for(Pedido pedido : emAberto){

            exibirPedidoOrganizado(pedido);

        }

    }

    void exibirPedidoOrganizado(Pedido pedido){

        System.out.println("---------------------------------------");
        System.out.println("Pedido #" + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("CPF: " + pedido.getCliente().getCpf());
        System.out.println("Status: " + pedido.getStatus().getNome());
        System.out.println("Itens:");

        for(ItemPedido item : pedido.getItens()){

            System.out.println("  - " + item.getProduto().getNome()
                    + " (x" + item.getQuantidade() + ") R$ "
                    + item.calcularSubtotal());

        }

        System.out.println("Total: R$ " + pedido.getValorTotal());
        System.out.println("---------------------------------------");

    }

}
