package ast;

import java.util.List;

public class ProgramaNode {

    public String nome;

    public List<DeclaracaoNode> declaracoes;

    public List<ComandoNode> comandos;

    public ProgramaNode(
        String nome,
        List<DeclaracaoNode> declaracoes,
        List<ComandoNode> comandos
    ) {
        this.nome = nome;
        this.declaracoes = declaracoes;
        this.comandos = comandos;
    }
}