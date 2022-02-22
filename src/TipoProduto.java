public class TipoProduto
{
    private String tipoProd;
    private Produto first;
    private TipoProduto nextTypeProd;
    private TipoProduto antTypeProd;


    public TipoProduto(String tipoProd) {
        this.tipoProd = tipoProd;
    }


    /**
     * metodo para adicionar um produto no tipo de produto
     * @param novoProduto
     */
    public void adicionaProduto(Produto novoProduto)
    {
        if(first ==null)
        {
            first=novoProduto;
        }else
        {
            novoProduto.setNext(first);
            first=novoProduto;
        }
    }

    /**
     * metodo para imprimir os dados de um produto
     */
    public void printProduto()
    {
        Produto temp = first;
        while (temp!=null)
        {
            System.out.println("Produto - " + temp.getNome() + " da marca - " + temp.getMarca() + " custa - " + temp.getPreco() + "€ e tem a rteferencia - " + temp.getReferenciaProduto() );
            temp=temp.getNext();
        }
    }

//getters e setters
    public String getTipoProd() {
        return tipoProd;
    }
    public TipoProduto getNextTypeProd() {
        return nextTypeProd;
    }
    public void setAntTypeProd(TipoProduto antTypeProd) {
        this.antTypeProd = antTypeProd;
    }
    public void setNextTypeProd(TipoProduto nextTypeProd) {
        this.nextTypeProd = nextTypeProd;
    }




    @Override
    public String toString() {
        return "TipoProduto{" +
                "tipoProd='" + tipoProd + '\'' +
                '}';
    }
}
