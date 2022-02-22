import java.util.ArrayList;

public class LojaPcDiga
{
    private String cidade;

    private LojaPcDiga nextLoja;
    private LojaPcDiga antLoja;
    private LigacaoPcDiga firstLigacao;
    private TipoProduto firstTypeProd;
    private TipoProduto lastTypeProd;
    private ProdutoReferencia firstRef;
    private ProdutoReferencia lastRef;
    private ArrayList<LigacaoPcDiga> custoTransporte;
    private int heapTam;


    public LojaPcDiga(String cidade)
    {
        this.cidade = cidade;
        this.heapTam =0;
        custoTransporte = new ArrayList<LigacaoPcDiga>();
    }

    /**
     * metodo para adicionar uma nova ligação
     * @param novaLigacao
     */
    public void adicionaLigacaoLoja(LigacaoPcDiga novaLigacao)
    {
        if (firstLigacao != null)
        {
            novaLigacao.setNextLigacao(firstLigacao);
        }
        firstLigacao = novaLigacao;
        //HÁ UM PROBLEMA AQUI                       |
        //adicionaNaHeap(novaLigacao);              |
        //NÃO CONSIGO RESOLVER O PROVLEMA DA HEAP   |
    }//fim do metodo

    /**
     * metodo para verificar se uma ligação existe com a loja
     * @param cidadeFinal
     * @return
     */
    public Boolean ligacao(String cidadeFinal)
    {//fim do metodo
        if(firstLigacao==null)
            return false;
        LigacaoPcDiga temp = firstLigacao;
        while(temp!=null && !temp.getRef().getCidade().equalsIgnoreCase(cidadeFinal))
        {
            temp=temp.getNextLigacao();
        }

        if(temp==null)
            return true;
        return false;

    }//fim do metodo

    /**
     * metodo para ver o menor elemento da heap
     */
    public void peekMenorCusto()
    {
        System.out.println(custoTransporte.get(0));
    }//fim do metodo

    /**
     * metodo para dar print a todas as ligações da loja
     */
    public void printLigacoes()
    {
        if(firstLigacao==null)
        {
            System.out.println("não existem ligações");
        }else
        {
            LigacaoPcDiga temp = firstLigacao;
            while (temp!=null)
            {
                System.out.println("ligacao -" + temp.getRef().getCidade());
                temp=temp.getNextLigacao();
            }
        }
    }//fim do metodo

    /**
     * metodo para adidcionar um novo tipo de produto na loja
     * @param tipo
     */
    public void adicionaTipoDeProduto(String tipo)
    {
        TipoProduto novoTipo = new TipoProduto(tipo);
        if(firstTypeProd == null && lastTypeProd==null)
        {
            firstTypeProd = novoTipo;
            lastTypeProd = novoTipo;

        }else
        {
            if (novoTipo.getTipoProd().compareToIgnoreCase(firstTypeProd.getTipoProd())<0)
            {
                novoTipo.setNextTypeProd(firstTypeProd);
                firstTypeProd.setAntTypeProd(novoTipo);
                firstTypeProd=novoTipo;
            }
            else if (lastTypeProd.getTipoProd().compareToIgnoreCase(novoTipo.getTipoProd())<0)
            {
                lastTypeProd.setNextTypeProd(novoTipo);
                novoTipo.setAntTypeProd(lastTypeProd);
                lastTypeProd = novoTipo;
            }
            else {

                TipoProduto temp = firstTypeProd;
                while (temp.getNextTypeProd() != null && temp.getNextTypeProd().getTipoProd().compareToIgnoreCase(novoTipo.getTipoProd()) < 0)
                {
                    temp = temp.getNextTypeProd();
                }
                if (temp.getNextTypeProd() != null) {
                    novoTipo.setNextTypeProd(temp.getNextTypeProd());
                    temp.getNextTypeProd().setAntTypeProd(novoTipo);
                    temp.setNextTypeProd(novoTipo);
                    novoTipo.setAntTypeProd(temp);
                }
            }//fim do else
        }//fim do else
    }//fim do metodo

    /**
     * metodo para imprimir todos tipos de produtos da loja
     */
    public void printTiposLista()
    {
        TipoProduto temp = firstTypeProd;
        while (temp!=null)
        {
            System.out.println(temp.getTipoProd());
            System.out.println("__________________");
            temp=temp.getNextTypeProd();
        }
    }//fim do metodo

    /**
     * metodo para adicionar um produto no seu tipo
     * @param tipo
     * @param novoProduto
     */
    public void adiconaProduto(String tipo,Produto novoProduto)
    {
        if(!checaSeExisteAMesmaRef(novoProduto.getReferenciaProduto()))
        {
            System.out.println("não pode haver produtos iguais");
            return;
        }
       TipoProduto existeProduto = existeTipoProduto(tipo);
       ProdutoReferencia novaRef = new ProdutoReferencia(novoProduto);
       if(existeProduto == null)
       {
           System.out.println("não existe esse tipo de produto na loja");
       }else
       {
           //adiciona na lista especifica de um tipo de produtos
            existeProduto.adicionaProduto(novoProduto);
            //adicionar na lsita ordenada de referencias com todos os produtos
            if (firstRef == null)
            {
                firstRef = novaRef;
                lastRef = novaRef;
            }else
            {
                if(firstRef.getRefProd().getReferenciaProduto()>novoProduto.getReferenciaProduto())
                {
                    novaRef.setNextRefProd(firstRef);
                    firstRef=novaRef;
                }else if (lastRef.getRefProd().getReferenciaProduto()<novoProduto.getReferenciaProduto())
                {
                    lastRef.setNextRefProd(novaRef);
                    lastRef = novaRef;
                }
                else
                {
                    adicionaEntreRefs(novaRef);
                }
            }//fim do else
       }//fim do else
    }//fim do metodo

    /**
     * metodo para dar print aos produtos de cada tipo
     */
    public void printProdutosDeCadaTipo()
    {
        if(firstTypeProd == null)
        {
            System.out.println("não existe nenhum tipo nesta loja");
            return;
        }
        TipoProduto temp = firstTypeProd;
        while (temp!=null)
        {
            System.out.println("tipo - " +temp.getTipoProd()+":");
            temp.printProduto();
            System.out.println("_______________________________");
            temp=temp.getNextTypeProd();
        }
    }//fim do metodo

    /**
     * metodo para imprimir um produto pela referencia
     * @param ref
     */
    public void printPorReferencia(int ref)
    {
        if (firstRef==null)
        {
            System.out.println("não existe nenhuma referencia na loja");
            return;
        }
        else {
            if (firstRef.getRefProd().getReferenciaProduto() == ref)
            {
                System.out.println(firstRef.getRefProd());
                return;
            }else if(lastRef.getRefProd().getReferenciaProduto() == ref)
            {
                System.out.println(lastRef.getRefProd());
                return;
            }else
            {
                ProdutoReferencia temp = firstRef;
                while (temp != null && temp.getRefProd().getReferenciaProduto() != ref) {
                    temp = temp.getNextRefProd();
                }
                if (temp != null) {
                    System.out.println(temp);
                }
                System.out.println("não existe essa referência");

            }
        }
    }//fim do metodo

    /**
     * metodo auxiliar que checa se existe uma referencia
     * @param ref
     * @return
     */
    private boolean checaSeExisteAMesmaRef(int ref)
    {
        ProdutoReferencia temp = firstRef;
        while (temp != null && temp.getRefProd().getReferenciaProduto() != ref)
        {
            temp=temp.getNextRefProd();
        }
        if(temp==null)
            return true;
        return false;

    }//fim do metodo

    /**
     * metodo auxiliar para adicionar um dado produto na lista de referencias
     * @param novoProdRef
     */
    private void adicionaEntreRefs(ProdutoReferencia novoProdRef)
    {
        ProdutoReferencia temp = firstRef;
        while (temp.getNextRefProd() != null && temp.getNextRefProd().getRefProd().getReferenciaProduto() < novoProdRef.getRefProd().getReferenciaProduto())
        {

            temp=temp.getNextRefProd();
        }
        novoProdRef.setNextRefProd(temp.getNextRefProd());
        temp.setNextRefProd(novoProdRef);
    }//fim do metodo


    /**
     * metodo auxiliar para verificar se um tipo de produto existe
     * @param produto
     * @return
     */
    private TipoProduto existeTipoProduto(String produto)
    {
        if(firstTypeProd==null)
            return null;
        TipoProduto temp = firstTypeProd;
        while(temp!=null && temp.getTipoProd().compareToIgnoreCase(produto)!=0)
        {
            temp=temp.getNextTypeProd();
        }
        if(temp==null)
            return null;
        else
            return temp;
    }//fim do metodo

    /**
     * metodo auxiliar para adicionar uma ligação na heap
     * @param novaLigacao
     */
    private void adicionaNaHeap(LigacaoPcDiga novaLigacao)
    {
        if(heapTam==0)
        {
            heapTam++;
            custoTransporte.add(novaLigacao);
        }else if(checaRepetidos(novaLigacao))
        {
            heapTam++;
            custoTransporte.add(novaLigacao);
            sobeEmBolha();
            System.out.println("ligação adicionada");
        }
    }//fim do metodo

    /**
     * metodo auxiliar que checa se existe uma ligação repetida
     * @param novaLigacao
     * @return
     */
    private boolean checaRepetidos(LigacaoPcDiga novaLigacao)
    {
        for(LigacaoPcDiga lp: custoTransporte)
        {
            if(lp==null)
            {
                continue;
            }
            else if (novaLigacao.getRef().getCidade().equalsIgnoreCase(lp.getRef().getCidade()))
            {
                System.out.println("não se pode por referencias repetidas");
                return false;
            }
        }
        return true;
    }//fim do metodo

    /**
     * metodo para a heap
     */
    private void sobeEmBolha()
    {
        int currente = heapTam;
        while(currente>0 && custoTransporte.get(getParent(currente)).getCustoDeslocamento()>custoTransporte.get(currente).getCustoDeslocamento())
        {
            swap(getParent(currente),currente);
            currente=getParent(currente);
        }
    }//fim do metodo

    /**
     * metodo para a heap
     * @param pai
     * @param filho
     */
    private void swap(int pai,int filho)
    {
        LigacaoPcDiga temp = custoTransporte.get(pai);
        custoTransporte.set(pai,custoTransporte.get(filho));
        custoTransporte.set(filho,temp);
    }//fim do metodo


    //getters e setters
    public String getCidade() {
        return cidade;
    }

    public LojaPcDiga getNextLoja() {
        return nextLoja;
    }

    public void setNextLoja(LojaPcDiga nextLoja) {
        this.nextLoja = nextLoja;
    }


    public void setAntLoja(LojaPcDiga antLoja) {
        this.antLoja = antLoja;
    }


    //metodos auxiliares para a heap
    private int getParent(int pos)
    {
        return (int) Math.floor(pos/2);
    }
    private int getLeftChild(int pos)
    {
        return pos * 2;
    }
    private int getRightChild(int pos)
    {
        return (pos * 2) + 1;
    }

    @Override
    public String toString() {
        return "LojaPcDiga{" +
                "cidade='" + cidade;
    }
}//fim da classe
