package ast;

public class MaiorNode extends ExpressaoNode {

    public ExpressaoNode esquerda;
    public ExpressaoNode direita;

    public MaiorNode(
        ExpressaoNode esquerda,
        ExpressaoNode direita
    ) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public String toString() {
        return "(" + esquerda + " > " + direita + ")";
    }
}