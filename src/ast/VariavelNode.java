package ast;

public class VariavelNode extends ExpressaoNode {

    public String nome;

    public VariavelNode(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "VariavelNode(" + nome + ")";
    }
}