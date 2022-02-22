public class LigacaoPcDiga
{
    private LojaPcDiga ref;
    private LigacaoPcDiga nextLigacao;
    private final int custoDeslocamento;


    public LigacaoPcDiga(int custoDeslocamento,LojaPcDiga ref)
    {
        this.custoDeslocamento = custoDeslocamento;
        this.ref = ref;
    }



    //getters e setters
    public LojaPcDiga getRef() {
        return ref;
    }


    public LigacaoPcDiga getNextLigacao() {
        return nextLigacao;
    }

    public void setNextLigacao(LigacaoPcDiga nextLigacao) {
        this.nextLigacao = nextLigacao;
    }

    public int getCustoDeslocamento() {
        return custoDeslocamento;
    }


}
