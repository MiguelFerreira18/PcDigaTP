public class ProdutoReferencia
{
    private Produto refProd;
    private ProdutoReferencia nextRefProd;
    private ProdutoReferencia antRefProd;

    public ProdutoReferencia(Produto refProd) {
        this.refProd = refProd;
    }



    //getters e setters
    public Produto getRefProd() {
        return refProd;
    }

    public ProdutoReferencia getNextRefProd() {
        return nextRefProd;
    }

    public void setNextRefProd(ProdutoReferencia nextRefProd) {
        this.nextRefProd = nextRefProd;
    }

    @Override
    public String toString() {
        return "ProdutoReferencia{" +
                "refProd=" + refProd +
                '}';
    }
}
