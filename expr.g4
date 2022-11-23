grammar expr;

@header{
package parser;
}


program : (expr)
    ;

expr :expr0 
    |'if' expr 'then' expr rulelse
    |'while' expr 'do' expr
    |'for' ID ':=' expr 'to' expr 'do' expr
    |'break'
    |'let' declaration+ 'in' (exprseq)? 'end'
    |print
    |types
    ;

rulelse : 'else' expr 
    |
    ;

expr0: expr1 ('|' expr1 )*
    ;

expr1 : expr2  ('&' expr2 )*
    ;


expr2: expr3  (('=' | '<>' | '>' | '<' | '>=' | '<=') expr3 )*
    ;


expr3: expr4 (('+'|'-') expr4 )*
    ;


expr4: expr5 (('*'|'/') expr5)*
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

types: typeid ('[' expr ']' 'of' expr 
    |'{' fieldlist? '}')
    ;

exprnegation: '-' expr5
    ;

exprseq : expr exprseqbis
    ;

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


declaration : typedeclaration
    |variabledeclaration
    |functiondeclaration
    ;

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

print: 'print' '(' expr ')' ;

//token

INTEGERCONSTANT : ('0'..'9')+
    ;
STRINGCONSTANT : ('"')('a'..'z'|'A'..'Z'|' '|'!'|'?'|'-'|'_'|'.'|':'|';'|',')*('"')
    ;
ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

BOPERATOR : ( '+'  | '-' | '*' | '/' | '=' | '<>' | '>' | '<' | '>=' | '<=' | '&' | '|')
    ;

WS : ('\n'|' '|'    '|'\t')+ ->skip
    ;
COMMENT:    '/*' .*? '*/' -> skip
    ;
