package ast;

import java.util.List;

public class CondicionalNode extends ComandoNode {

    public ExpressaoNode condicao;

    public List<ComandoNode> blocoEntao;

    public List<ComandoNode> blocoSenao;

    public CondicionalNode(
        ExpressaoNode condicao,
        List<ComandoNode> blocoEntao,
        List<ComandoNode> blocoSenao
    ) {
        this.condicao = condicao;
        this.blocoEntao = blocoEntao;
        this.blocoSenao = blocoSenao;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("SE ")
        .append(condicao)
        .append("\nENTAO\n");

        for (ComandoNode comando : blocoEntao) {
            sb.append("  ")
            .append(comando)
            .append("\n");
        }

        if (!blocoSenao.isEmpty()) {

            sb.append("SENAO\n");

            for (ComandoNode comando : blocoSenao) {
                sb.append("  ")
                .append(comando)
                .append("\n");
            }
        }

        sb.append("FIM_SE");

        return sb.toString();
    }
}