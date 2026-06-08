package ast;

public class IgualNode extends ExpressaoNode {

    public ExpressaoNode esquerda;
    public ExpressaoNode direita;

    public IgualNode(
        ExpressaoNode esquerda,
        ExpressaoNode direita
    ) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public String toString() {
        return "(" + esquerda + " == " + direita + ")";
    }
}