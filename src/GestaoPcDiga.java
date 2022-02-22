public class GestaoPcDiga
{
    private LojaPcDiga firstLoja;

    public void adicionaLojaPcDiga(String cidade)
    {
        adicionaLoja(new LojaPcDiga(cidade));
    }


    /**
     * metodo que adiciona uma ligação entre duas lojas da pcDiga
     * @param lojaInicial
     * @param lojaFinal
     * @param custo
     */
    public void adicionaLigacaoPcDiga(String lojaInicial,String lojaFinal,int custo)
    {
        LojaPcDiga lojaInicialExistente = existeLoja(lojaInicial);
        LojaPcDiga lojaFinalExistente = existeLoja(lojaFinal);
        //caso não exista nenhuma das lojas
        if(lojaInicialExistente == null && lojaFinalExistente == null)
        {
            LojaPcDiga novaLoja1 = new LojaPcDiga(lojaInicial);
            LigacaoPcDiga novaLigacao1 = new LigacaoPcDiga(custo,novaLoja1);
            LojaPcDiga novaLoja2 = new LojaPcDiga(lojaFinal);
            LigacaoPcDiga novaLigacao2 = new LigacaoPcDiga(custo,novaLoja2);

            adicionaLoja(novaLoja1);
            adicionaLoja(novaLoja2);
            novaLoja1.adicionaLigacaoLoja(novaLigacao2);
            novaLoja2.adicionaLigacaoLoja(novaLigacao1);
            //caso uma delas não exista
        }else if (lojaInicialExistente == null || lojaFinalExistente == null)
        {

            if (lojaInicialExistente == null)
            {
                LojaPcDiga novaLoja = new LojaPcDiga(lojaInicial);
                LigacaoPcDiga novaLigacao1 = new LigacaoPcDiga(custo,novaLoja);
                LigacaoPcDiga novaLigacao2 = new LigacaoPcDiga(custo,lojaFinalExistente);

                adicionaLoja(novaLoja);
                novaLoja.adicionaLigacaoLoja(novaLigacao2);
                lojaFinalExistente.adicionaLigacaoLoja(novaLigacao1);
            }else
            {
                System.out.println(lojaFinal);
                LojaPcDiga novaLoja = new LojaPcDiga(lojaFinal);
                LigacaoPcDiga novaLigacao1 = new LigacaoPcDiga(custo,novaLoja);
                LigacaoPcDiga novaLigacao2 = new LigacaoPcDiga(custo,lojaInicialExistente);

                adicionaLoja(novaLoja);
                novaLoja.adicionaLigacaoLoja(novaLigacao2);
                lojaInicialExistente.adicionaLigacaoLoja(novaLigacao1);
            }
            //Caso as duas existam
        }else
        {
            if(checaLigacao(lojaInicial,lojaFinal))
            {
                LigacaoPcDiga novaLigacao1 = new LigacaoPcDiga(custo,lojaInicialExistente);
                LigacaoPcDiga novaLigacao2 = new LigacaoPcDiga(custo,lojaFinalExistente);

                System.out.println("loja inicial - " +lojaInicialExistente.getCidade());
                System.out.println("loja inicial - " + lojaFinalExistente.getCidade());

                lojaInicialExistente.adicionaLigacaoLoja(novaLigacao2);
                lojaFinalExistente.adicionaLigacaoLoja(novaLigacao1);
            }else
            {
                System.out.println("já existe essa ligacao");
            }
        }
    }//fim do metodo

    /**
     * metodo para verificar se existe uma conexão entre lojas de duas cidades
     * @param cidadeComeco
     * @param cidadeFinal
     * @return
     */
    public Boolean checaLigacao(String cidadeComeco,String cidadeFinal)
    {
        LojaPcDiga lojaComeco= existeLoja(cidadeComeco);
        LojaPcDiga lojaFim = existeLoja(cidadeFinal);
        if(lojaComeco !=null && lojaFim!=null)
        {
               return lojaComeco.ligacao(cidadeFinal);
        }
        return false;
    }//fim do metodo

    /**
     * metodo para adicionar um novo tipo de produto na loja
     * @param tipoProd
     * @param cidade
     */
    public void adicionaTipoProduto(String tipoProd,String cidade)
    {
        if(firstLoja==null)
            return;
        LojaPcDiga temp = firstLoja;
        while (temp!=null && temp.getCidade().compareToIgnoreCase(cidade)!=0)
        {
            temp=temp.getNextLoja();
        }
        if(temp!=null)
        {
            temp.adicionaTipoDeProduto(tipoProd);
        }else{
            System.out.println("não existe essa loja");
        }
    }//fim do metodo

    /**
     * metodo para imprimir o tipo de produtos de uma determinada loja da pcDiga
     * @param cidade
     */
    public void printTiposDeProdutos(String cidade)
    {
        LojaPcDiga temp = firstLoja;
        while (temp!=null && !temp.getCidade().equalsIgnoreCase(cidade))
        {
        temp=temp.getNextLoja();
        }
        if(temp !=null )
        {
            System.out.println("loja - " + temp.getCidade()+":");
            temp.printTiposLista();

            return;
        }
        System.out.println("não existe essa loja");
    }//fim do metodo

    /**
     * metodo para imprimir lista das lojas
     */
    public void printListaDeLojas()
    {
        if (firstLoja==null)
        {
            System.out.println("não exitem lojas");
        }else
        {
            LojaPcDiga temp = firstLoja;
            while (temp!=null)
            {
                System.out.println("loja - " + temp.getCidade());
                temp.printLigacoes();
                System.out.println("____________________");
                temp=temp.getNextLoja();
            }
        }
    }//fim do metodo

    /**
     * metodo para adicionar um novo produto numa loja
     * @param loja
     * @param tipoProduto   tipo do produto onde o produto vai ser adicionado
     * @param nomeProduto
     * @param marca
     * @param referencia
     * @param preco
     */
    public void adicionaProduto(String loja,String tipoProduto,String nomeProduto,String marca,int referencia, int preco)
    {
        Produto novoProduto = new Produto(referencia,nomeProduto,marca,preco);
        LojaPcDiga encontraLoja = existeLoja(loja);
        if (encontraLoja==null)
        {
            System.out.println("não existe essa loja");
        }else
        {
            encontraLoja.adiconaProduto(tipoProduto,novoProduto);
        }
    }//fim do metodo

    /**
     * metodo para imprimir todos os produtos de todas as lojas
     */
    public void printDeTodosOsProdutos()
    {
        if(firstLoja==null)
        {
            System.out.println("não existe nenhuma loja");
        }
        else
        {
            LojaPcDiga temp = firstLoja;
            while(temp!=null )
            {
                System.out.println("Loja - "+ temp.getCidade());
                temp.printProdutosDeCadaTipo();
                temp=temp.getNextLoja();
            }
        }
    }//fim do metodo

    /**
     * metodo para imprimir um produto com uma determinada referencia
     * @param loja
     * @param ref
     */
    public void printPelaReferencia(String loja,int ref)
    {
        LojaPcDiga encontraLoja = existeLoja(loja);
        if (encontraLoja==null)
        {
            System.out.println("não existe essa loja");
        }else
        {
            encontraLoja.printPorReferencia(ref);
        }
    }//fim do metodo

    /**
     * metodo auxiliar para ver se uma determinada loja existe na lista
     * @param cidade
     * @return
     */
    private LojaPcDiga existeLoja(String cidade)
    {
        if(firstLoja==null)
            return null;
        LojaPcDiga temp = firstLoja;
        while(temp!=null && temp.getCidade().compareToIgnoreCase(cidade)!=0)
        {
            temp=temp.getNextLoja();
        }
        if(temp==null)
            return null;
        else
            return temp;

    }//fim do metodo

    /**
     * metodo auxiliar para adicionar lojas na lista bidirecional
     * @param novaLoja
     */
    private void adicionaLoja(LojaPcDiga novaLoja)
    {
        if (firstLoja != null)
        {
            firstLoja.setAntLoja(novaLoja);
            novaLoja.setNextLoja(firstLoja);
        }
        firstLoja = novaLoja;
        System.out.println("loja adicionada");
    }//fim do metodo


}//fim da classe
