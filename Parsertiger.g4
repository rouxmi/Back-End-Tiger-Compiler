parser grammar Parsertiger;

@header{
package parser;
}

options {tokenVocab = Lexertiger;}

// déclaration du programme
program : (expr)
    ;

//prioriré de calcul

expr0: expr1 (OU expr1 )*
    ;

expr1 : expr2  (ET expr2 )*
    ;


expr2: expr3  ((EGAL | DIF | INF | SUP | INFEG | SUPEG ) expr3 )*
    ;


expr3: expr4 ((PLUS|NEG) expr4 )*
    ;


expr4: expr5 ((MUL|DIV) expr5)*
    ;

//Expressions

expr5: idcall
    | STRINGCONSTANT 
    | INTEGERCONSTANT 
    | NIL
    | exprnegation
    | typedeclaration
    | PAOUV exprseq? PAFER
    ;

expr :expr0 
    |IF expr THEN expr rulelse
    |WHILE expr DO expr
    |FOR ID DPTEG expr TO expr DO expr
    |BREAK
    |LET declaration+ IN (exprseq)? END
    |print
    |types
    ;

//Définitions règles

print: PRINT PAOUV expr PAFER ;

rulelse : ELSE expr 
    |
    ;

declaration : typedeclaration
    |variabledeclaration
    |functiondeclaration
    ;

//fonctions

function: PAOUV exprlist? PAFER ;

functiondeclaration : FCT ID PAOUV (typefields)? PAFER functiondeclarationbis
    ;

functiondeclarationbis : EGAL expr 
    | DPT typeid EGAL expr
    ; 

// Expressions sequence/Liste

exprnegation: NEG expr5
    ;

exprseq : expr exprseqbis
    ;

exprseqbis : POINTV exprseq 
    |
    ;

exprlist : expr exprlistbis
    ;

exprlistbis: VIRG expr exprlistbis 
    |
    ;

//fields    

fieldlist : field fieldlistbis
    ;

field: ID EGAL expr
    ;

fieldlistbis: VIRG field fieldlistbis 
    |
    ;

//Id/Value

idcall: ID (lvaluebis|function)
    ;

lvaluebis :POINT ID lvaluebis
    |CAOUV expr CAFER lvaluebis
    |
    ;



//Types    

types: typeid (CAOUV expr CAFER OF expr 
    |CROUV fieldlist? CRFER)
    ;

typedeclaration : TYPE typeid EGAL type;

type : typeid 
    |CROUV (typefields)? CRFER
    |ARROF typeid
    ;

typefields : typefield typefieldsbis
    ;

typefieldsbis: VIRG typefields 
    |
    ;

typefield : ID DPT typeid;

typepredefined: (INT|STR)
    ;

typeid : ID
    |typepredefined
    ;

//Variables

variabledeclaration : VAR ID variabledeclarationbis
    ;

variabledeclarationbis: DPT typeid DPTEG expr 
    | DPTEG expr
    ;



