grammar expr;

@header{
package parser;
}


program : (expr);

expr :expr0
    |lvalue ':=' expr
    |'if' expr 'then' expr rulelse
    |'while' expr 'do' expr
    |'for' ID ':=' expr 'to' expr 'do' expr
    |'break'
    |'let' declaration+ 'in' (exprseq)? 'end'
    |commentaire
    |print
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

expr5: lvalue 
    | STRINGCONSTANT 
    | INTEGERCONSTANT 
    | 'nil' 
    | '-' expr5 
    | ID '(' exprlist? ')' 
    | typeid '[' expr ']' 'of' expr 
    | typeid '{' fieldlist? '}' 
    | '(' exprseq? ')'
    ;


commentaire : '/*' (STRINGCONSTANT)* '*/' ;

exprseq : expr exprseqbis;

exprseqbis : ';' exprseq 
    |
    ;

exprlist : expr exprlistbis
    ;

exprlistbis: ',' exprlist 
    |
    ;

fieldlist : ID '=' expr fieldlistbis
    ;

fieldlistbis: ',' fieldlist 
    |
    ;

lvalue : ID
    |lvalue '.' ID
    |lvalue '[' expr ']'
    ;

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

typefield : ID ':' (typeid|typepredefined);

typepredefined: ('int'|'string')
    ;

typeid : ID
    ;

variabledeclaration : 'var' ID variabledeclarationbis
    ;

variabledeclarationbis: ':'typeid ':=' expr 
    | ':=' expr
    ;

functiondeclaration : 'function' ID ('(' typefields ')')? functiondeclarationbis
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
