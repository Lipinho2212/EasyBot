package ast;

public class NumeroNode extends ExpressaoNode {

    public double valor;

    public NumeroNode(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "NumeroNode(" + valor + ")";
    }
}