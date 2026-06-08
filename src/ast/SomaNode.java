package ast;

public class SomaNode extends ExpressaoNode {

    public ExpressaoNode esquerda;
    public ExpressaoNode direita;

    public SomaNode(
        ExpressaoNode esquerda,
        ExpressaoNode direita
    ) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public String toString() {
        return "(" + esquerda + " + " + direita + ")";
    }
}