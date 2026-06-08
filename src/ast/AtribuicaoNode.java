package ast;

public class AtribuicaoNode extends ComandoNode {

    public String variavel;

    public ExpressaoNode expressao;

    public AtribuicaoNode(
            String variavel,
            ExpressaoNode expressao) {

        this.variavel = variavel;
        this.expressao = expressao;
    }

    @Override
    public String toString() {
        return variavel + " = " + expressao;
    }
}