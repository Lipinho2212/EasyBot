package semantic;

import ast.*;

public class AnalisadorSemantico {

    private TabelaSimbolos tabela =
        new TabelaSimbolos();

    public void analisar(
        ProgramaNode programa
    ) {

        verificarDeclaracoes(programa);

        verificarComandos(programa);
    }

private void verificarDeclaracoes(
    ProgramaNode programa
) {

    for (
        DeclaracaoNode declaracao :
        programa.declaracoes
    ) {

        tabela.adicionar(
            declaracao.nome,
            declaracao.tipo
        );
    }
}

private void verificarComandos(
    ProgramaNode programa
) {

    for (
        ComandoNode comando :
        programa.comandos
    ) {

        verificarComando(comando);
    }
}

private void verificarComando(
    ComandoNode comando
) {

    if (
        comando instanceof AtribuicaoNode
    ) {

        verificarAtribuicao(
            (AtribuicaoNode) comando
        );
    }
}

private void verificarAtribuicao(AtribuicaoNode atribuicao) {

    if (!tabela.existe(atribuicao.variavel)) {
        throw new RuntimeException(
            "Variável não declarada: " + atribuicao.variavel
        );
    }

    String tipoVar = tabela.buscar(atribuicao.variavel).tipo;
    String tipoExpr = tipoExpressao(atribuicao.expressao);

    if (!tipoVar.equals(tipoExpr)) {
        throw new RuntimeException(
            "Erro semântico: incompatibilidade de tipos (" +
            tipoVar + " <- " + tipoExpr + ")"
        );
    }
}

private String tipoExpressao(ExpressaoNode exp) {

    if (exp instanceof NumeroNode) {
        return "NUMERO";
    }

    if (exp instanceof VariavelNode) {

        VariavelNode v = (VariavelNode) exp;

        if (!tabela.existe(v.nome)) {
            throw new RuntimeException("Variável não declarada: " + v.nome);
        }

        return tabela.buscar(v.nome).tipo;
    }

    if (exp instanceof SomaNode) {
        SomaNode n = (SomaNode) exp;

        String esq = tipoExpressao(n.esquerda);
        String dir = tipoExpressao(n.direita);

        validarOperacaoAritmetica(esq, dir, "+");

        return "NUMERO";
    }

    if (exp instanceof SubtracaoNode) {
        SubtracaoNode n = (SubtracaoNode) exp;

        String esq = tipoExpressao(n.esquerda);
        String dir = tipoExpressao(n.direita);

        validarOperacaoAritmetica(esq, dir, "-");

        return "NUMERO";
    }

    if (exp instanceof MultiplicacaoNode) {
        MultiplicacaoNode n = (MultiplicacaoNode) exp;

        String esq = tipoExpressao(n.esquerda);
        String dir = tipoExpressao(n.direita);

        validarOperacaoAritmetica(esq, dir, "*");

        return "NUMERO";
    }

    if (exp instanceof MaiorNode
     || exp instanceof MenorNode
     || exp instanceof IgualNode
     || exp instanceof DiferenteNode) {

        if (exp instanceof MaiorNode n) {
            validarComparacao(n.esquerda, n.direita);
        } else if (exp instanceof MenorNode n) {
            validarComparacao(n.esquerda, n.direita);
        } else if (exp instanceof IgualNode n) {
            validarComparacao(n.esquerda, n.direita);
        } else {
            DiferenteNode n = (DiferenteNode) exp;
            validarComparacao(n.esquerda, n.direita);
        }

        return "LOGICO";
    }

    throw new RuntimeException(
        "Expressao desconhecida: " + exp.getClass()
    );
}

private void validarOperacaoAritmetica(String esq, String dir, String op) {

    if (esq.equals("LOGICO") || dir.equals("LOGICO")) {
        throw new RuntimeException(
            "Erro semantico: sensor LOGICO nao pode ser usado em operacao matematica '" + op + "'"
        );
    }

    if (!esq.equals("NUMERO") || !dir.equals("NUMERO")) {
        throw new RuntimeException(
            "Erro semantico: operacao '" + op + "' requer NUMERO"
        );
    }
}

private void validarComparacao(ExpressaoNode esqExp, ExpressaoNode dirExp) {

    String esq = tipoExpressao(esqExp);
    String dir = tipoExpressao(dirExp);

    if (!esq.equals(dir)) {
        throw new RuntimeException(
            "Erro semantico: comparacao entre tipos incompatíveis (" + esq + " e " + dir + ")"
        );
    }
}

}