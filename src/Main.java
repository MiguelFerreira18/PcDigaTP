public class Main
{
    static GestaoPcDiga gp = new GestaoPcDiga();
    public static void main(String[] args)
    {
        gp.adicionaLojaPcDiga("porto");
        gp.adicionaLigacaoPcDiga("porto","senegal",500);
        gp.adicionaLigacaoPcDiga("lisboa","funchal",512312300);
        gp.adicionaLigacaoPcDiga("porto","lisboa",501240);
        gp.adicionaLigacaoPcDiga("lisboa","porto",51400);
        gp.adicionaLigacaoPcDiga("braga","beja",50450);
        System.out.println("____________");

        if(gp.checaLigacao("porto","senegal"))
            System.out.println("existe");
        System.out.println("____________");
        gp.printListaDeLojas();
        System.out.println("____________");
        gp.adicionaTipoProduto("coisas","porto");
        gp.printTiposDeProdutos("porto");

        System.out.println("__________NOVA SECÇÃO____________");

        gp.adicionaTipoProduto("rato","porto");
        gp.adicionaTipoProduto("monitor","porto");
        gp.adicionaTipoProduto("computador","porto");
        gp.adicionaTipoProduto("portatil","porto");
        gp.adicionaTipoProduto("HeadSets","porto");
        gp.adicionaProduto("porto","rato","lightSynch","Logitech",12345,23);
        gp.adicionaProduto("portao","rato","lightSynch","Logitech",12345,23);
        gp.adicionaProduto("porto","HeadSets","kyle","Krom",1234512,23);

        gp.printTiposDeProdutos("porto");
        gp.printTiposDeProdutos("lisboa");
        gp.printTiposDeProdutos("qualquer sitio");

        System.out.println("__________________");

        gp.printPelaReferencia("porto",1234512);
    }

}
