package ast;

import java.util.List;

public class EnquantoNode extends ComandoNode {

    public ExpressaoNode condicao;

    public List<ComandoNode> comandos;

    public EnquantoNode(
        ExpressaoNode condicao,
        List<ComandoNode> comandos
    ) {
        this.condicao = condicao;
        this.comandos = comandos;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("ENQUANTO ")
          .append(condicao)
          .append("\n");

        for (ComandoNode comando : comandos) {
            sb.append("  ")
              .append(comando)
              .append("\n");
        }

        sb.append("FIM_ENQUANTO");

        return sb.toString();
    }
}