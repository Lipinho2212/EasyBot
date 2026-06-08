# EasyBot - Compilador para Robótica

Um compilador que traduz código escrito em linguagem EasyBot para Python. Projeto feito na disciplina de Compiladores.

## Como Compilar

Primeiro, precisa gerar o lexer e o parser:

```bash
java -jar tools/jflex.jar -d src/lexer/ src/lexer/language.lex
java -jar tools/cup.jar -destdir src/parser/ src/parser/Parser.cup
```

Depois compila tudo:

```bash
javac -d bin -cp "tools/cup-runtime.jar;." \
    src/lexer/sym.java \
    src/lexer/LexicalAnalyzer.java \
    src/ast/*.java \
    src/semantic/*.java \
    src/generator/*.java \
    src/parser/sym.java \
    src/parser/Parser.java \
    src/main/Main.java
```

## Como Rodar

```bash
java -cp "bin;tools/cup-runtime.jar" main.Main arquivo.eb
```

Exemplo com um arquivo de teste:

```bash
java -cp "bin;tools/cup-runtime.jar" main.Main teste3.eb
```

Saída:
```
COMPILADO COM SUCESSO

=== CÓDIGO PYTHON GERADO ===
# Robo

x = 0
y = 0

x = 10.0
y = 20.0
if x > 0.0:
    y = (x + 5.0)
while x < 100.0:
    x = (x + 10.0)
```

## Linguagem EasyBot

Estrutura básica:

```
PROGRAMA NomeDoProgramaSemEspacos
INICIO
  VAR x : NUMERO;
  VAR ativo : LOGICO;
  
  x = 10;
  
  SE x > 0 ENTAO
    x = x + 5;
  FIM_SE
  
  ENQUANTO x < 100 FACA
    x = x + 10;
  FIM_ENQUANTO

FIM
```

### Tipos suportados
- `NUMERO` - números inteiros ou com vírgula
- `LOGICO` - booleano (verdadeiro/falso)

### Operadores
- Aritméticos: `+`, `-`, `*`, `/`
- Comparação: `>`, `<`, `==`, `!=`

## Arquitetura

1. **Lexer** (JFlex) - lê o arquivo e transforma em tokens
2. **Parser** (CUP) - tokens viram uma árvore (AST)
3. **Análise Semântica** - valida tipos e variáveis
4. **Geração de Código** - transforma a árvore em Python

## Testes

- `teste.eb` - teste de erro semântico (variável tipo LOGICO em operação matemática)
- `teste2.eb` - teste com sintaxe inválida
- `teste3.eb` - programa que compila corretamente

## Erros que o compilador detecta

**Erro Semântico:**
```
Erro semantico: sensor LOGICO nao pode ser usado em operacao matematica '+'
```

**Erro Sintático:**
```
Syntax error at character...
```

## Arquivos principais

- `src/main/Main.java` - entrada do programa
- `src/lexer/language.lex` - especificação do léxico
- `src/parser/Parser.cup` - especificação da gramática
- `src/ast/` - nós da árvore de sintaxe
- `src/semantic/` - análise semântica
- `src/generator/CodeGenerator.java` - geração de código Python

## Dependências

- JDK 8+
- JFlex (incluído em `tools/`)
- CUP (incluído em `tools/`)

Ver `DOCUMENTACAO.md` para mais detalhes.

