package ast;

public class MovimentoNode extends ComandoNode {

    public ExpressaoNode x;
    public ExpressaoNode y;
    public ExpressaoNode velocidade;

    public MovimentoNode(
        ExpressaoNode x,
        ExpressaoNode y,
        ExpressaoNode velocidade
    ) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
    }

    @Override
    public String toString() {
        return "MOVER(" +
                x + "," +
                y + ") VEL " +
                velocidade;
    }
}