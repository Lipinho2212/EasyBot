package ast;

public class DeclaracaoNode extends ASTNode {

    public String nome;
    public String tipo;

    public DeclaracaoNode(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
}