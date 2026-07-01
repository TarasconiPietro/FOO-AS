package model;

public class Endereco {
    private String logradouro;
    private String bairro;
    private String cidade;
    private int numero;
    private String descricao;

    public Endereco(String logradouro, int numero, String bairro, String cidade, String descricao) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.descricao = descricao;
    }

    public Endereco() {
    }

    public Endereco(String logradouro, String descricao, int numero, String cidade, String bairro) {
        this.logradouro = logradouro;
        this.descricao = descricao;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", numero=" + numero +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
