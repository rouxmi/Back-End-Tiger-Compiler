grammar expr;

@header{
package parser;
}


program : (expr);

expr : STRINGCONSTANT exprim
    |INTEGERCONSTANT exprim
    |'nil' exprim
    |lvalue exprim
    |'-'expr exprim
    |lvalue ':=' expr exprim
    |ID '(' exprlist? ')' exprim
    |'(' exprseq? ')' exprim
    |typeid '{' fieldlist? '}' exprim
    |typeid '[' expr ']' 'of' expr exprim
    |'if' expr 'then' expr exprim
    |'if' expr 'then' expr 'else' expr exprim
    |'while' expr 'do' expr exprim
    |'for' ID ':=' expr 'to' expr 'do' expr exprim
    |'break' exprim
    |'let' declarationlist 'in' (exprseq)? 'end' exprim
    |commentaire exprim
    ;

exprim: '|' expr1 exprim | expr1 exprim |
    ;

expr1 : expr2 '&' expr1 | expr2
    ;

expr2: expr3 ('=' | '<>' | '>' | '<' | '>=' | '<=') expr2 | expr3
    ;

expr3: expr4 ('+'|'-') expr3 | expr4
    ;

expr4: expr ('*'|'/') expr4 | expr
    ;


commentaire : '/*' (STRINGCONSTANT)* '*/' ;

exprseq : expr
    |expr ';' exprseq;

exprlist : expr
    |expr ',' exprlist;

fieldlist : ID '=' expr
    |ID '=' expr ',' fieldlist;

lvalue : ID
    |lvalue '.' ID
    |lvalue '[' expr ']'
    ;

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

typefield : ID ':' typeid;

typepredefined: ('int'|'string')
    ;

typeid : (ID|typepredefined);

variabledeclaration : 'var' ID ':=' expr
    | 'var' ID ':' typeid ':=' expr
    ;


functiondeclaration : 'function' ID ('(' typefields ')')? '=' expr
    |'function' ID ('(' typefields ')')? ':' typeid '=' expr
    ;

print: 'print' '(' INTEGERCONSTANT ')' ;

//token

INTEGERCONSTANT : ('0'..'9')+;
STRINGCONSTANT : ('"')('a'..'z'|'A'..'Z'|' '|'!'|'?'|'-'|'_'|'.'|':'|';'|',')*('"');
ID : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

BOPERATOR : ( '+'  | '-' | '*' | '/' | '=' | '<>' | '>' | '<' | '>=' | '<=' | '&' | '|');

WS : ('\n'|' '|'    ')+ ->skip;
