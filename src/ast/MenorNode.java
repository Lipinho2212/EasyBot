package ast;

public class MenorNode extends ExpressaoNode {

    public ExpressaoNode esquerda;
    public ExpressaoNode direita;

    public MenorNode(
        ExpressaoNode esquerda,
        ExpressaoNode direita
    ) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public String toString() {
        return "(" + esquerda + " < " + direita + ")";
    }
}