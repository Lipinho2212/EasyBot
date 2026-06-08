# EasyBot - Compilador para Robótica

Um compilador de linguagem de domínio específico (DSL) que converte código EasyBot em Python.

## 🚀 Início Rápido

### Compilação
```bash
java -jar tools/jflex.jar -d src/lexer/ src/lexer/language.lex
java -jar tools/cup.jar -destdir src/parser/ src/parser/Parser.cup
javac -d bin -cp "tools/cup-runtime.jar;." src/lexer/sym.java src/lexer/LexicalAnalyzer.java src/ast/*.java src/semantic/*.java src/generator/*.java src/parser/sym.java src/parser/Parser.java src/main/Main.java
```

### Execução
```bash
java -cp "bin;tools/cup-runtime.jar" main.Main teste.eb
```

## 📚 Documentação

Veja [DOCUMENTACAO.md](DOCUMENTACAO.md) para documentação completa.

## ✅ Testes

- `teste.eb` - Teste de validação semântica
- `teste2.eb` - Teste com sintaxe avançada
- `teste3.eb` - Teste completo funcionando ✅

## 📝 Licença

Projeto acadêmico
