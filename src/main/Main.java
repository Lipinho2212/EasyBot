package main;

import lexer.LexicalAnalyzer;
import parser.Parser;
import semantic.AnalisadorSemantico;

import java.io.FileReader;
import ast.ProgramaNode;
import java_cup.runtime.Symbol;

public class Main {

    public static void main(String[] args) {

        try {

            String file = args.length > 0 ? args[0] : "teste.eb";

            Parser parser =
                new Parser(
                    new LexicalAnalyzer(
                        new FileReader(file)
                    )
                );

            Symbol resultado = parser.parse();

            ProgramaNode programa =
                (ProgramaNode) resultado.value;

            AnalisadorSemantico semantico =
                new AnalisadorSemantico();

            semantico.analisar(programa);

            // TODO: Gerar código Python e salvar em arquivo
            generator.CodeGenerator gerador = new generator.CodeGenerator();
            String codigoPython = gerador.generate(programa);
            System.out.println("COMPILADO COM SUCESSO");
            System.out.println("\n=== CÓDIGO PYTHON GERADO ===");
            System.out.println(codigoPython);

        } catch (Exception e) {
            System.err.println("ERRO AO COMPILAR");
            System.err.println(e.getMessage());
        }
    }
}