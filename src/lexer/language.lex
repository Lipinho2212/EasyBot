package lexer;

import java_cup.runtime.Symbol;

%%

%public
%class LexicalAnalyzer
%unicode
%cup
%line
%column

%{

private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
}

private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
}

%}

/* ===== REGEX AUXILIARES ===== */

LETRA = [a-zA-Z]
DIGITO = [0-9]

ID = {LETRA}({LETRA}|{DIGITO}|_)*
NUMERO = {DIGITO}+("."{DIGITO}+)?
ESPACO = [ \t\r\n]+

%%

/* ===== PALAVRAS RESERVADAS ===== */

"PROGRAMA"      { 
    //System.out.println("Palavra Reservada: PROGRAMA");
    return symbol(sym.PROGRAMA); 
}

"INICIO"        {
    //System.out.println("Palavra Reservada: INICIO");
    return symbol(sym.INICIO);
}

"FIM"           {
    //System.out.println("Palavra Reservada: FIM");
    return symbol(sym.FIM); 
}

"VAR"           {
    //System.out.println("Palavra Reservada: VAR");
    return symbol(sym.VAR);
}

"NUMERO"           {
    //System.out.println("Palavra Reservada: NUMERO_TIPO");
    return symbol(sym.NUMERO_TIPO); 
}


"LOGICO"           {
    //System.out.println("Palavra Reservada: LOGICO_TIPO");
    return symbol(sym.LOGICO_TIPO); 
}

"MOVER"           {
    //System.out.println("Palavra Reservada: MOVER");
    return symbol(sym.MOVER); 
}

"VEL"           {
    //System.out.println("Palavra Reservada: VEL");
    return symbol(sym.VEL); 
}

"ENQUANTO"           {
    //System.out.println("Palavra Reservada: ENQUANTO");
    return symbol(sym.ENQUANTO); 
}

"FACA"           {
    //System.out.println("Palavra Reservada: FACA");
    return symbol(sym.FACA); 
}

"FIM_ENQUANTO"           {
    //System.out.println("Palavra Reservada: FIM_ENQUANTO");
    return symbol(sym.FIM_ENQUANTO); 
}

"SE"           {
    //System.out.println("Palavra Reservada: SE");
    return symbol(sym.SE); 
}

"ENTAO"           {
    //System.out.println("Palavra Reservada: ENTAO");
    return symbol(sym.ENTAO); 
}

"SENAO"           {
    //System.out.println("Palavra Reservada: SENAO");
    return symbol(sym.SENAO); 
}

"FIM_SE"           {
    //System.out.println("Palavra Reservada: FIM_SE");
    return symbol(sym.FIM_SE); 
}

/* ===== OPERADORES ===== */

"==" { 
    //System.out.println("OPERADOR : IGUAL_IGUAL");
    return symbol(sym.IGUAL_IGUAL); 
}

"!=" { 
    //System.out.println("OPERADOR : DIFERENTE");
    return symbol(sym.DIFERENTE); 
}

">" { 
    //System.out.println("OPERADOR : MAIOR QUE");
    return symbol(sym.MAIOR); 
}

"<" { 
    //System.out.println("OPERADOR : MENOR QUE");
    return symbol(sym.MENOR); 
}


"=" { 
    //System.out.println("OPERADOR : ATRIBUICAO");
    return symbol(sym.ATRIBUICAO);
}


"+" { 
    //System.out.println("OPERADOR : SOMA");
    return symbol(sym.SOMA); 
}


"-" { 
    //System.out.println("OPERADOR : SUBTRACAO");
    return symbol(sym.SUBTRACAO); 
}


"*" {
    //System.out.println("OPERADOR : MULTIPLICACAO");
    return symbol(sym.MULTIPLICACAO); 
}


"/" { 
    //System.out.println("OPERADOR : DIVISAO");
    return symbol(sym.DIVISAO); 
}

/* ===== SIMBOLOS ===== */

"(" { 
    //System.out.println("SIMBOLO : ABRE_PAR");
    return symbol(sym.ABRE_PAR); 
}

")" { 
    //System.out.println("SIMBOLO : FECHA_PAR");
    return symbol(sym.FECHA_PAR); 
}

":" { 
    //System.out.println("SIMBOLO : DOIS_PONTOS");
    return symbol(sym.DOIS_PONTOS); 
}

";" { 
    //System.out.println("SIMBOLO : PONTO_VIRGULA");
    return symbol(sym.PONTO_VIRGULA); 
}

"," { 
    //System.out.println("SIMBOLO : VIRGULA");
    return symbol(sym.VIRGULA); 
}


/* ===== LITERAIS ===== */


{ID}            {
                    //System.out.println("LITERAL: ID " + yytext());
                    return symbol(sym.ID, yytext());
                }

{NUMERO}        { 
    //System.out.println("LITERAL: NUMERO " + yytext());
    return symbol(sym.NUMERO_LITERAL, Double.parseDouble(yytext())); 
                }

{ESPACO}        { }

/* ===== ERRO LÉXICO ===== */

. {
    throw new RuntimeException(
        "Caractere invalido: " + yytext() +
        " na linha " + yyline +
        " coluna " + yycolumn
    );
}

