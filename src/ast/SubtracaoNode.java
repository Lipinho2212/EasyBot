package ast;

public class SubtracaoNode extends ExpressaoNode {

    public ExpressaoNode esquerda;
    public ExpressaoNode direita;

    public SubtracaoNode(
        ExpressaoNode esquerda,
        ExpressaoNode direita
    ) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public String toString() {
        return "(" + esquerda + " - " + direita + ")";
    }
}