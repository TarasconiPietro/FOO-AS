package model;

public class StatusPedido {
    private String nome;

    public StatusPedido(String nome) {
        this.nome = nome;
    }

    public StatusPedido() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "StatusPedido{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
