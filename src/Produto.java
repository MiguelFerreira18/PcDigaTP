public class Produto
{
    private int referenciaProduto;
    private String nome;
    private String marca;
    private int preco;
    private Produto next;

    public Produto(int referenciaProduto, String nome, String marca, int preco)
    {
        this.referenciaProduto = referenciaProduto;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
    }


    //getters e setters
    public int getReferenciaProduto() {
        return referenciaProduto;
    }


    public String getNome() {
        return nome;
    }


    public String getMarca() {
        return marca;
    }


    public int getPreco() {
        return preco;
    }


    public Produto getNext() {
        return next;
    }

    public void setNext(Produto next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Produto - " + getNome() + " da marca - " + getMarca() + " custa - " + getPreco() + "€ e tem a referência - " + getReferenciaProduto();
    }
}
