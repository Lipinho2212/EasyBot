package generator;

import ast.*;

/**
 * Gerador de código Python a partir da AST EasyBot.
 * Converte comandos e expressões para sintaxe Python válida.
 */
public class CodeGenerator {
    private StringBuilder code;
    private int indent = 0;

    /**
     * Gera código Python a partir do programa (AST).
     * TODO: Adicionar suporte a importações de bibliotecas
     * TODO: Melhorar tratamento de erros de geração
     */
    public String generate(ProgramaNode prog) {
        code = new StringBuilder();
        indent = 0;
        
        code.append("# ").append(prog.nome).append("\n\n");
        
        // Variáveis
        if (prog.declaracoes != null) {
            for (DeclaracaoNode d : prog.declaracoes) {
                code.append(d.nome).append(" = ").append(valorPadrao(d.tipo)).append("\n");
            }
            code.append("\n");
        }
        
        // Comandos
        if (prog.comandos != null) {
            for (ComandoNode c : prog.comandos) {
                processar(c);
            }
        }
        
        return code.toString();
    }

    /**
     * Processa um comando e adiciona seu equivalente Python ao código.
     * TODO: Adicionar tratamento para novos tipos de comando
     */
    private void processar(ComandoNode cmd) {
        String ind = "    ".repeat(indent);
        
        if (cmd instanceof AtribuicaoNode) {
            AtribuicaoNode a = (AtribuicaoNode) cmd;
            code.append(ind).append(a.variavel).append(" = ").append(expr(a.expressao)).append("\n");
            
        } else if (cmd instanceof CondicionalNode) {
            CondicionalNode c = (CondicionalNode) cmd;
            code.append(ind).append("if ").append(expr(c.condicao)).append(":\n");
            indent++;
            for (ComandoNode cmd2 : c.blocoEntao) {
                processar(cmd2);
            }
            indent--;
            
            if (c.blocoSenao != null && !c.blocoSenao.isEmpty()) {
                code.append(ind).append("else:\n");
                indent++;
                for (ComandoNode cmd2 : c.blocoSenao) {
                    processar(cmd2);
                }
                indent--;
            }
            
        } else if (cmd instanceof EnquantoNode) {
            EnquantoNode e = (EnquantoNode) cmd;
            code.append(ind).append("while ").append(expr(e.condicao)).append(":\n");
            indent++;
            for (ComandoNode cmd2 : e.comandos) {
                processar(cmd2);
            }
            indent--;
            
        } else if (cmd instanceof MovimentoNode) {
            MovimentoNode m = (MovimentoNode) cmd;
            code.append(ind).append("mover(").append(expr(m.x)).append(", ")
                .append(expr(m.y)).append(", ").append(expr(m.velocidade)).append(")\n");
        }
    }

    /**
     * Converte uma expressão da AST para código Python.
     * TODO: Adicionar operadores bit a bit (AND, OR, NOT)
     * TODO: Melhorar parentetização inteligente para evitar redundâncias
     */
    private String expr(ExpressaoNode e) {
        if (e instanceof NumeroNode) {
            return String.valueOf(((NumeroNode) e).valor);
        } else if (e instanceof VariavelNode) {
            return ((VariavelNode) e).nome;
        } else if (e instanceof SomaNode) {
            SomaNode s = (SomaNode) e;
            return "(" + expr(s.esquerda) + " + " + expr(s.direita) + ")";
        } else if (e instanceof SubtracaoNode) {
            SubtracaoNode s = (SubtracaoNode) e;
            return "(" + expr(s.esquerda) + " - " + expr(s.direita) + ")";
        } else if (e instanceof MultiplicacaoNode) {
            MultiplicacaoNode m = (MultiplicacaoNode) e;
            return "(" + expr(m.esquerda) + " * " + expr(m.direita) + ")";
        } else if (e instanceof DivisaoNode) {
            DivisaoNode d = (DivisaoNode) e;
            return "(" + expr(d.esquerda) + " / " + expr(d.direita) + ")";
        } else if (e instanceof IgualNode) {
            IgualNode i = (IgualNode) e;
            return expr(i.esquerda) + " == " + expr(i.direita);
        } else if (e instanceof DiferenteNode) {
            DiferenteNode d = (DiferenteNode) e;
            return expr(d.esquerda) + " != " + expr(d.direita);
        } else if (e instanceof MaiorNode) {
            MaiorNode m = (MaiorNode) e;
            return expr(m.esquerda) + " > " + expr(m.direita);
        } else if (e instanceof MenorNode) {
            MenorNode m = (MenorNode) e;
            return expr(m.esquerda) + " < " + expr(m.direita);
        }
        return "None";
    }

    /**
     * Retorna o valor padrão em Python para cada tipo.
     * TODO: Adicionar tratamento para tipos customizados
     * TODO: Implementar inicialização com valores do usuário
     */
    private String valorPadrao(String tipo) {
        if (tipo == null || tipo.equals("NUMERO")) return "0";
        if (tipo.equals("LOGICO")) return "False";
        if (tipo.equals("TEXTO")) return "''";
        return "0";
    }

    /**
     * TODO: Implementar método para salvar código em arquivo .py
     * Sugestão: adicionar parâmetro String caminhoSaida ao método generate()
     * e chamar este método ao final
     */
    // public void salvarArquivo(String caminho, String conteudo) { }
}
