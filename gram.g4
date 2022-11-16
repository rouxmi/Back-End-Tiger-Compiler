grammar gram;

program : expr;

expr : string
    |integer
    |'nil'
    |lvalue
    |'-'expr
    |expr OPERATOR expr
    |lvalue ':=' expr
    |id '(' exprlist? ')'
    |'(' exprseq? ')'
    |typeid '{' fieldlist? '}'
    |typeid '[' expr ']' 'of' expr
    |'if' expr 'then' expr
    |'if' expr 'then' expr 'else' expr
    |'while' expr 'do' expr
    |'for' id ':=' expr 'to' expr 'do' expr
    |'break'
    |'let' declarationlist 'in' (exprseq)? 'end'
    |commentaire;

commentaire : '/*' (LETTER|ESPACE)* '*/';

exprseq : expr
    |expr ';' exprseq;

exprlist : expr
    |expr ',' exprlist;

fieldlist : id '=' expr
    |id '=' expr ',' fieldlist;

lvalue : id
    |lvalue '.' id
    |lvalue '[' expr ']';

declarationlist : declaration
    |declaration declarationlist;

declaration : typedeclaration
    |variabledeclaration
    |functiondeclaration;

typedeclaration : 'type' typeid '=' type;

type : typeid 
    |'{' (typefields)? '}'
    |'array of' typeid;

typefields : typefield
    |typefield ',' typefields;

typefield : id ':' typeid;

typeid : id;

variabledeclaration : 'var' id ':=' expr
    | 'var' id ':' typeid ':=' expr;


functiondeclaration : 'function' id '(' (typefields)? ')' '=' expr
    |'function' id '(' (typefields)? ')' ':' typeid '=' expr;



integer : DIGIT+;

string : LETTER+;

id : LETTER ( LETTER | DIGIT | '_' )*;

//token

LETTER :('a'..'z')|('A'..'Z');

DIGIT : ('0'..'9');

ESPACE : ' '|'  ';

OPERATOR : '+' | '-' | '*' | '/' | '=' | '<>' | '>' | '<' | '>=' | '<=' | '&' | '|';




