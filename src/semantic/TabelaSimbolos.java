package semantic;

import java.util.HashMap;
import java.util.Map;

public class TabelaSimbolos {

    private Map<String, Simbolo> simbolos =
        new HashMap<>();

    public void adicionar(
        String nome,
        String tipo
    ) {

        if (simbolos.containsKey(nome)) {

            throw new RuntimeException(
                "Variável já declarada: " +
                nome
            );
        }

        simbolos.put(
            nome,
            new Simbolo(nome, tipo)
        );
    }

    public boolean existe(String nome) {

        return simbolos.containsKey(nome);
    }

    public Simbolo buscar(String nome) {

        return simbolos.get(nome);
    }
}