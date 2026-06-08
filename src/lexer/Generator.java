package lexer;

import java.io.File;
import java.nio.file.Paths;

public class Generator {

    public static void main(String[] args)
            throws Exception {

        String rootPath =
                Paths.get("")
                     .toAbsolutePath()
                     .toString();

        String subPath = "/src/lexer";

        String file =
                rootPath +
                subPath +
                "/language.lex";

        File sourceCode = new File(file);

        jflex.Main.generate(
                new String[]{
                        sourceCode.getPath()
                }
        );

        System.out.println(
                "Lexer gerado com sucesso"
        );
    }
}



