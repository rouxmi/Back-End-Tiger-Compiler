grammar expr;

@header{
package parser;
}


program : (expr);

expr :expr0 
    |'if' expr 'then' expr rulelse
    |'while' expr 'do' expr
    |'for' ID ':=' expr 'to' expr 'do' expr
    |'break'
    |'let' declaration+ 'in' (exprseq)? 'end'
    |commentaire
    |print
    |types
    ;

rulelse : 'else' expr 
    |
    ;

expr0: expr1 expr0bis
    ;

expr0bis: '|' expr0 
    |
    ;

expr1 : expr2  expr1bis
    ;

expr1bis : '&' expr1 
    |
    ;

expr2: expr3  expr2bis
    ;

expr2bis : ('=' | '<>' | '>' | '<' | '>=' | '<=') expr2 
    |
    ;

expr3: expr4 expr3bis
    ;

expr3bis : ('+'|'-') expr3 
    |
    ;

expr4: expr5 expr4bis
    ;

expr4bis: ('*'|'/') expr4 
    |
    ;

expr5: idcall
    | STRINGCONSTANT 
    | INTEGERCONSTANT 
    | 'nil' 
    | exprnegation
    | typedeclaration
    | '(' exprseq? ')'
    ;

function:'(' exprlist? ')' ;

types:typeid ('[' expr ']' 'of' expr 
    |'{' fieldlist? '}')
    ;

exprnegation: '-' expr5
    ;

commentaire : '/*' (STRINGCONSTANT)* '*/' ;

exprseq : expr exprseqbis;

exprseqbis : ';' exprseq 
    |
    ;

exprlist : expr exprlistbis
    ;

exprlistbis: ',' expr exprlistbis 
    |
    ;

fieldlist : field fieldlistbis
    ;

field: ID '=' expr
    ;

fieldlistbis: ',' field fieldlistbis 
    |
    ;

idcall: ID (lvaluebis|function)
    ;

lvaluebis :'.' ID lvaluebis
    |'[' expr ']' lvaluebis
    |
    ;

//lvalue: ID lvaluebis
    //;

declaration : typedeclaration
    |variabledeclaration
    |functiondeclaration;

typedeclaration : 'type' typeid '=' type;

type : typeid 
    |'{' (typefields)? '}'
    |'array of' typeid
    ;

typefields : typefield typefieldsbis
    ;

typefieldsbis: ',' typefields 
    |
    ;

typefield : ID ':' typeid;

typepredefined: ('int'|'string')
    ;

typeid : ID
    |typepredefined
    ;

variabledeclaration : 'var' ID variabledeclarationbis
    ;

variabledeclarationbis: ':'typeid ':=' expr 
    | ':=' expr
    ;

functiondeclaration : 'function' ID '(' (typefields)? ')' functiondeclarationbis
    ;

functiondeclarationbis : '=' expr 
    | ':' typeid '=' expr
    ; 

print: 'print' '(' INTEGERCONSTANT ')' ;

//token

INTEGERCONSTANT : ('0'..'9')+;
STRINGCONSTANT : ('"')('a'..'z'|'A'..'Z'|' '|'!'|'?'|'-'|'_'|'.'|':'|';'|',')*('"');
ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

BOPERATOR : ( '+'  | '-' | '*' | '/' | '=' | '<>' | '>' | '<' | '>=' | '<=' | '&' | '|');

WS : ('\n'|' '|'    ')+ ->skip;
